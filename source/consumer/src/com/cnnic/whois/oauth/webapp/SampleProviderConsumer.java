package com.cnnic.whois.oauth.webapp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnnic.whois.oauth.utils.JSONHelper;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.ParameterStyle;
import net.oauth.server.HttpRequestMessage;
import net.sf.json.JSONObject;

/**
 * Consumer for Sample OAuth Provider
 */
public class SampleProviderConsumer extends HttpServlet {

	private static final String NAME = "sample";
    
    private static final String WHOIS_RESULT = "/WEB-INF/pages/result.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        OAuthConsumer consumer = null;
        try {
            consumer = CookieConsumer.getConsumer(NAME, getServletContext());
            OAuthAccessor accessor = CookieConsumer.getAccessor(request,
                    response, consumer);
            Collection<OAuth.Parameter> parameters = HttpRequestMessage.getParameters(request);
//            if (!OAuth.newMap(parameters).containsKey("echo")) {
//                parameters.add(new OAuth.Parameter("echo", "Hello."));
//            }
            String result = invoke(accessor, parameters);
            Map<String, Object> map = JSONHelper.reflect(JSONObject.fromObject(result));
        	Iterator iter = map.entrySet().iterator();
        	while(iter.hasNext()){
        		Entry en = (Entry) iter.next();
        		System.out.println(en.getKey() + " : " + en.getValue());
        	}
        	
        	if(OAuth.newMap(parameters).containsValue("ip")){
        		request.setAttribute("queryType", "ip");
        	}
        	if(OAuth.newMap(parameters).containsValue("domain")){
        		request.setAttribute("queryType", "domain");
        	}
        	if(OAuth.newMap(parameters).containsValue("entity")){
        		request.setAttribute("queryType", "entity");
        	}
        	
        	request.setAttribute("result", result);
        	getServletContext().getRequestDispatcher(WHOIS_RESULT).forward(request, response);
        	
        } catch (Exception e) {
            CookieConsumer.handleException(e, request, response, consumer);
        }
    }

    private String invoke(OAuthAccessor accessor,
            Collection<? extends Map.Entry> parameters)
    throws OAuthException, IOException, URISyntaxException {
        URL baseURL = (URL) accessor.consumer.getProperty("serviceProvider.baseURL");
//      TODO : if baseURL is null, else default url is ：http://localhost:8080/oauth/
        if (baseURL == null) {
            baseURL = new URL("http://localhost:8080/");
        }
        // TODO : need change this path
        OAuthMessage request = accessor.newRequestMessage("POST", (new URL(
                baseURL, "echo")).toExternalForm(), parameters);
        OAuthMessage response = CookieConsumer.CLIENT.invoke(request,
                ParameterStyle.AUTHORIZATION_HEADER);
        String responseBody = response.readBodyAsString();
        return responseBody;
    }

    private static final long serialVersionUID = 1L;

}
