package com.cnnic.whois.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.MessageExtension;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchResponse;

public class AuthOpenIdClientServlet extends HttpServlet {
	/**
	 * Called by the server (via the service method) to allow a servlet to
	 * handle a GET request. Overriding this method to support a GET request
	 * also automatically supports an HTTP HEAD request. A HEAD request is a GET
	 * request that returns no body in the response, only the request header
	 * fields.
	 * 
	 * When overriding this method, read the request data, write the response
	 * headers, get the response's writer or output stream object, and finally,
	 * write the response data. It's best to include content type and encoding.
	 * When using a PrintWriter object to return the response, set the content
	 * type before accessing the PrintWriter object.
	 * 
	 * The servlet container must write the headers before committing the
	 * response, because in HTTP the headers must be sent before the response
	 * body.
	 * 
	 * Where possible, set the Content-Length header (with the
	 * ServletResponse.setContentLength(int) method), to allow the servlet
	 * container to use a persistent connection to return its response to the
	 * client, improving performance. The content length is automatically set if
	 * the entire response fits inside the response buffer.
	 * 
	 * When using HTTP 1.1 chunked encoding (which means that the response has a
	 * Transfer-Encoding header), do not set the Content-Length header.
	 * 
	 * The GET method should be safe, that is, without any side effects for
	 * which users are held responsible. For example, most form queries have no
	 * side effects. If a client request is intended to change stored data, the
	 * request should use some other HTTP method.
	 * 
	 * The GET method should also be idempotent, meaning that it can be safely
	 * repeated. Sometimes making a method safe also makes it idempotent. For
	 * example, repeating queries is both safe and idempotent, but buying a
	 * product online or modifying data is neither safe nor idempotent.
	 * 
	 * If the request is incorrectly formatted, doGet returns an HTTP
	 * "Bad Request" message.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		verifyResponse(req, resp);
	}

	/**
	 * Called by the server (via the service method) to allow a servlet to
	 * handle a POST request. The HTTP POST method allows the client to send
	 * data of unlimited length to the Web server a single time and is useful
	 * when posting information such as credit card numbers. When overriding
	 * this method, read the request data, write the response headers, get the
	 * response's writer or output stream object, and finally, write the
	 * response data. It's best to include content type and encoding. When using
	 * a PrintWriter object to return the response, set the content type before
	 * accessing the PrintWriter object.
	 * 
	 * The servlet container must write the headers before committing the
	 * response, because in HTTP the headers must be sent before the response
	 * body.
	 * 
	 * Where possible, set the Content-Length header (with the
	 * ServletResponse.setContentLength(int) method), to allow the servlet
	 * container to use a persistent connection to return its response to the
	 * client, improving performance. The content length is automatically set if
	 * the entire response fits inside the response buffer.
	 * 
	 * When using HTTP 1.1 chunked encoding (which means that the response has a
	 * Transfer-Encoding header), do not set the Content-Length header.
	 * 
	 * This method does not need to be either safe or idempotent. Operations
	 * requested through POST can have side effects for which the user can be
	 * held accountable, for example, updating stored data or buying items
	 * online.
	 * 
	 * If the HTTP POST request is incorrectly formatted, doPost returns an HTTP
	 * "Bad Request" message.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

	/**
	 * Processing OpenID response and verify whether the authorization by
	 * 
	 * @param httpReq
	 * @param resp
	 * @throws ServletException
	 */
	private void verifyResponse(HttpServletRequest httpReq,
			HttpServletResponse resp) throws ServletException {
		try {
			ConsumerManager manager = (ConsumerManager) httpReq.getSession()
					.getAttribute("manager");
			ParameterList response = new ParameterList(
					httpReq.getParameterMap());
			// retrieve the previously stored discovery information
			DiscoveryInformation discovered = (DiscoveryInformation) httpReq
					.getSession().getAttribute("openid-disc");
			// extract the receiving URL from the HTTP request
			StringBuffer receivingURL = httpReq.getRequestURL();
			String queryString = httpReq.getQueryString();

			if (queryString != null && queryString.length() > 0)
				receivingURL.append("?").append(httpReq.getQueryString());

			// verify the response; ConsumerManager needs to be the same
			// (static) instance used to place the authentication request
			VerificationResult verification = manager.verify(
					receivingURL.toString(), response, discovered);

			// examine the verification result and extract the verified
			// identifier
			Identifier verified = verification.getVerifiedId();
			if (verified != null) {
//				AuthSuccess authSuccess = (AuthSuccess) verification
//						.getAuthResponse();
//                if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)){
//                    FetchResponse fetchResp = (FetchResponse) authSuccess
//                            .getExtension(AxMessage.OPENID_NS_AX);
						RequestDispatcher dispatcher = httpReq
								.getRequestDispatcher("openId/openIdIndex.jsp");
						//httpReq.getSession().setAttribute("openIdUser",
						//		);
							try {
								dispatcher.forward(httpReq, resp);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//                }else{
//                	httpReq.setAttribute("errorMessage", "You entered is incorrect or your account OpenID OpenID service provider does not provide!");
//    				RequestDispatcher dispatcher = httpReq
//    						.getRequestDispatcher("openIdLogin.jsp");
//    				
//    					try {
//    						dispatcher.forward(httpReq, resp);
//    					} catch (IOException e) {
//    						// TODO Auto-generated catch block
//    						e.printStackTrace();
//    					}
//                }
						
			} else {
				httpReq.setAttribute("errorMessage", "You entered is incorrect or your account OpenID OpenID service provider does not provide!");
				RequestDispatcher dispatcher = httpReq
						.getRequestDispatcher("openIdLogin.jsp");
				
					try {
						dispatcher.forward(httpReq, resp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		} catch (OpenIDException e) {
				try {
					resp.sendError(417);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}
}
