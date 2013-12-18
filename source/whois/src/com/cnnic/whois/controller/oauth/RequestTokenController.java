package com.cnnic.whois.controller.oauth;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cnnic.whois.bean.oauth.User;
import com.cnnic.whois.dao.oauth.UserDao;
import com.cnnic.whois.dao.oauth.UserDaoImpl;
import com.cnnic.whois.util.OAuthProvider;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.server.OAuthServlet;

/**
 * Request token request handler
 */
@Controller
public class RequestTokenController {

	private static UserDao userDao = new UserDaoImpl();
	
	@PostConstruct
	public void init() throws ServletException {
      try{
          OAuthProvider.loadConsumers();
      }catch(IOException e){
          throw new ServletException(e.getMessage());
      }
	}
    
    @RequestMapping(value = "/request_token")
	public void request_token(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
    	try {
            OAuthMessage requestMessage = OAuthServlet.getMessage(request, null);
            
            OAuthConsumer consumer = OAuthProvider.getConsumer(requestMessage);
            
            OAuthAccessor accessor = new OAuthAccessor(consumer);
            OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
            {
                // Support the 'Variable Accessor Secret' extension
                // described in http://oauth.pbwiki.com/AccessorSecret
                String secret = requestMessage.getParameter("oauth_accessor_secret");
                if (secret != null) {
                    accessor.setProperty(OAuthConsumer.ACCESSOR_SECRET, secret);
                }
            }
            // generate request_token and secret
            OAuthProvider.generateRequestToken(accessor);
            
            response.setContentType("text/plain");
            OutputStream out = response.getOutputStream();
            OAuth.formEncode(OAuth.newList("oauth_token", accessor.requestToken,
                                           "oauth_token_secret", accessor.tokenSecret),
                             out);
            out.close();
            
        } catch (Exception e){
            OAuthProvider.handleException(e, request, response, true);
        }
	}

	@RequestMapping(value = "/authorize" , method = RequestMethod.GET)
	public void authorize_get(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		try{
            OAuthMessage requestMessage = OAuthServlet.getMessage(request, null);
            
            OAuthAccessor accessor = OAuthProvider.getAccessor(requestMessage);
           
            if (Boolean.TRUE.equals(accessor.getProperty("authorized"))) {
                // already authorized send the user back
                returnToConsumer(request, response, accessor);
            } else {
                sendToAuthorizePage(request, response, accessor);
            }
        
        } catch (Exception e){
            OAuthProvider.handleException(e, request, response, true);
        }
	}
	
	@RequestMapping(value = "/authorize" , method = RequestMethod.POST)
	public void authorizePost(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		
		String result = request.getParameter("success");
		
		String userId = request.getParameter("userId");
		 
		OAuthMessage requestMessage = OAuthServlet.getMessage(request, null);
        
        OAuthAccessor accessor = OAuthProvider.getAccessor(requestMessage);
        
		if(result != null && !"".equals(result)){
			// set userId in accessor and mark it as authorized
            OAuthProvider.markAsAuthorized(accessor, userId);
            returnToConsumer(request, response, accessor);
		}
		try{
            String password = request.getParameter("password");
            User user = userDao.findByUserIdAndPassword(userId, password);
            if (user == null || "".equals(user)){
            	request.setAttribute("error_value", "UserName or Password is wrong ! ");
            	sendToAuthorizePage(request, response, accessor);
            }else {
//            	TODO : This parameter need modify
            	request.setAttribute("user", user);
            	request.setAttribute("success", "success");
            	nextToAuthorizePage(request, response, accessor);
            }
            
        } catch (Exception e){
            OAuthProvider.handleException(e, request, response, true);
        }
	}
    
	@RequestMapping(value = "/access_token")
	public void access_token(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		try{
            OAuthMessage requestMessage = OAuthServlet.getMessage(request, null);
            
            OAuthAccessor accessor = OAuthProvider.getAccessor(requestMessage);
            OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
            
//          TODO :  Not sure whether to delete this part
            // make sure token is authorized
//            if (!Boolean.TRUE.equals(accessor.getProperty("authorized"))) {
//                 OAuthProblemException problem = new OAuthProblemException("permission_denied");
//                throw problem;
//            }
            // generate access token and secret
            OAuthProvider.generateAccessToken(accessor);
            
            response.setContentType("text/plain");
            OutputStream out = response.getOutputStream();
            OAuth.formEncode(OAuth.newList("oauth_token", accessor.accessToken,
                                           "oauth_token_secret", accessor.tokenSecret),
                             out);
            out.close();
            
        } catch (Exception e){
            OAuthProvider.handleException(e, request, response, true);
        }
	}
	
	
    private void sendToAuthorizePage(HttpServletRequest request, 
            HttpServletResponse response, OAuthAccessor accessor)
    throws IOException, ServletException{
        String callback = request.getParameter("oauth_callback");
        if(callback == null || callback.length() <=0) {
            callback = "none";
        }
        String consumer_description = (String)accessor.consumer.getProperty("description");
        request.setAttribute("CONS_DESC", consumer_description);
        request.setAttribute("CALLBACK", callback);
        request.setAttribute("TOKEN", accessor.requestToken);
        request.getRequestDispatcher //
                    ("/WEB-INF/pages/oauth/authorize.jsp").forward(request, response);
        
    }
    
    private void nextToAuthorizePage(HttpServletRequest request, 
            HttpServletResponse response, OAuthAccessor accessor)
    throws IOException, ServletException{
        String callback = request.getParameter("oauth_callback");
        if(callback == null || callback.length() <=0) {
            callback = "none";
        }
        String consumer_description = (String)accessor.consumer.getProperty("description");
        request.setAttribute("CONS_DESC", consumer_description);
        request.setAttribute("CALLBACK", callback);
        request.setAttribute("TOKEN", accessor.requestToken);
        request.getRequestDispatcher //
                    ("/WEB-INF/pages/oauth/nextAuthorize.jsp").forward(request, response);
        
    }
    
    private void returnToConsumer(HttpServletRequest request, 
            HttpServletResponse response, OAuthAccessor accessor)
    throws IOException, ServletException{
        // send the user back to site's callBackUrl
        String callback = request.getParameter("oauth_callback");
        if("none".equals(callback) 
            && accessor.consumer.callbackURL != null 
                && accessor.consumer.callbackURL.length() > 0){
            // first check if we have something in our properties file
            callback = accessor.consumer.callbackURL;
        }
        
        if( "none".equals(callback) ) {
            // no call back it must be a client
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.println("You have successfully authorized '" 
                    + accessor.consumer.getProperty("description") 
                    + "'. Please close this browser window and click continue"
                    + " in the client.");
            out.close();
        } else {
            // if callback is not passed in, use the callback from config
            if(callback == null || callback.length() <=0 )
                callback = accessor.consumer.callbackURL;
            String token = accessor.requestToken;
            if (token != null) {
                callback = OAuth.addParameters(callback, "oauth_token", token);
            }

            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", callback);
        }
    }
    

}
