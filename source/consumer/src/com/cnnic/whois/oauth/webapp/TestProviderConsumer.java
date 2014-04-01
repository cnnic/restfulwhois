package com.cnnic.whois.oauth.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.ParameterStyle;
import net.oauth.server.HttpRequestMessage;

/**
 * Consumer for Sample OAuth Provider
 */
public class TestProviderConsumer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String NAME = "sample";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		OAuthConsumer consumer = null;
		try {
			consumer = CookieConsumer.getConsumer(NAME, getServletContext());
			OAuthAccessor accessor = CookieConsumer.getAccessor(request,
					response, consumer);
			Collection<OAuth.Parameter> parameters = HttpRequestMessage.getParameters(request);
			String result = invoke(accessor, parameters);

			if (OAuth.newMap(parameters).containsValue("ip")) {
				request.setAttribute("queryType", "ip");
			}
			if (OAuth.newMap(parameters).containsValue("domain")) {
				request.setAttribute("queryType", "domain");
			}
			if (OAuth.newMap(parameters).containsValue("entity")) {
				request.setAttribute("queryType", "entity");
			}

			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.println(result);
		} catch (Exception e) {
			CookieConsumer.handleException(e, request, response, consumer);
		}
	}

	private String invoke(OAuthAccessor accessor,
			Collection<? extends Map.Entry> parameters) throws OAuthException,
			IOException, URISyntaxException {
		URL baseURL = (URL) accessor.consumer
				.getProperty("serviceProvider.baseURL");
		// TODO : if baseURL is null, else default url is
		// ：http://localhost:8080/oauth/
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

}
