package com.cnnic.whois.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.ax.FetchRequest;

import com.cnnic.whois.util.WhoisProperties;

public class AuthOpenIDServlet extends HttpServlet {
	private ConsumerManager manager = new ConsumerManager();
	private String returnToUrl = WhoisProperties.getServerurl() + "/authOpenIdClientServlet.do";

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
		authRequest(req.getParameter("openIdValue"), req, resp);
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
	 * Build request parameters to the OpenID server
	 * 
	 * @param userSuppliedString
	 * @param httpReq
	 * @param httpResp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void authRequest(String userSuppliedString,
			HttpServletRequest httpReq, HttpServletResponse httpResp)
			throws IOException, ServletException {
		try {
			List discoveries = manager.discover(userSuppliedString);
			DiscoveryInformation discovered = manager.associate(discoveries);
			httpReq.getSession().setAttribute("openid-disc", discovered);
			httpReq.getSession().setAttribute("manager", manager);
			httpReq.getSession().setAttribute("openIdUser", userSuppliedString);

			AuthRequest authReq = manager.authenticate(discovered, returnToUrl);
			//authReq.setRealm("http://www.restfulwhois.com");

			if (!discovered.isVersion2()) {
				httpResp.sendRedirect(authReq.getDestinationUrl(true));
			} else {
				httpReq.setAttribute("authReq", authReq);
				RequestDispatcher dispatcher = httpReq
						.getRequestDispatcher("openId/authOpenId.jsp");
				try {
					dispatcher.forward(httpReq, httpResp);
				} catch (ServletException e) {
					e.printStackTrace();
				}
			}

		} catch (OpenIDException e) {
			httpReq.setAttribute("errorMessage", "you enter OpenId is wrong!");
			RequestDispatcher dispatcher = httpReq
					.getRequestDispatcher("openIdLogin.jsp");
			dispatcher.forward(httpReq, httpResp);
		}

	}
}
