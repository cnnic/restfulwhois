package com.cnnic.whois.oauth.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

public class OAuthTest {

	public static void main(String[] args) throws IOException {

		DefaultHttpClient httpClient_A = new DefaultHttpClient();
		CookieStore cookieStore_A = new BasicCookieStore();
		HttpContext httpContext_A = new BasicHttpContext();
		httpContext_A.setAttribute(ClientContext.COOKIE_STORE, cookieStore_A);

		httpClient_A.getParams().setParameter("http.protocol.content-charset", "UTF-8");

		HttpGet httpGet = new HttpGet("http://localhost:8080/consumer/SampleProvider?queryType=ip");
		HttpResponse response_A = httpClient_A.execute(httpGet, httpContext_A);

		HttpUriRequest realRequest = (HttpUriRequest) httpContext_A.getAttribute(ExecutionContext.HTTP_REQUEST);

		if (response_A.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

			// step 1 : get requestToken and tokenSecret
			String requestToken = "";
			String tokenSecret = "";
			List<Cookie> cookies = cookieStore_A.getCookies();
			if (cookies != null && cookies.size() > 0) {
				for (Cookie c : cookies) {
					if (c.getName().equals("sample.requestToken")) {
						requestToken = c.getValue();
					}
					if (c.getName().equals("sample.tokenSecret")) {
						tokenSecret = c.getValue();
					}
				}
			}

			ArrayList<NameValuePair> pairList = new ArrayList<NameValuePair>();
			pairList.add(new BasicNameValuePair("oauth_callback", realRequest.getURI().toString()));
			pairList.add(new BasicNameValuePair("oauth_token", requestToken));
			pairList.add(new BasicNameValuePair("userId", "root".trim()));
			pairList.add(new BasicNameValuePair("password", "root".trim()));

			DefaultHttpClient httpClient_B = new DefaultHttpClient();

			CookieStore cookieStore_B = new BasicCookieStore();

			HttpContext httpContext_B = new BasicHttpContext();
			httpContext_B.setAttribute(ClientContext.COOKIE_STORE, cookieStore_B);

			HttpPost httpPost = new HttpPost("http://localhost:8080/authorize");

			httpPost.setEntity(new UrlEncodedFormEntity(pairList, "UTF-8"));
			HttpResponse response_B = httpClient_B.execute(httpPost, httpContext_B);

			if (response_B.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				HttpClient httpClient_C = new HttpClient();

				GetMethod getMethod_A = new GetMethod("http://localhost:8080/consumer/OAuth/Callback?consumer=sample&returnTo=/consumer/SampleProvider?queryType=ip&oauth_token="+ requestToken);
				getMethod_A.setRequestHeader("Content-Type", "text/html;charset=UTF-8");
				// the data being returned is gzip
				// getMethod.setRequestHeader("Accept-Encoding","gzip, deflate");
				getMethod_A.setRequestHeader("Accept-Language", "en-US,en;q=0.5");
				getMethod_A.setRequestHeader("Connection", "Keep-Alive");
				getMethod_A.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)");
				getMethod_A.setRequestHeader("Host", "localhost:8080");
				getMethod_A.setRequestHeader("Cookie", "sample.requestToken=" + requestToken + "; sample.tokenSecret=" + tokenSecret);

				int statusCode_A = httpClient_C.executeMethod(getMethod_A);
				if (statusCode_A == HttpStatus.SC_OK) {
					org.apache.commons.httpclient.Cookie[] cookiesf = httpClient_C.getState().getCookies();

					String accessToken = "";
					for (int i = 0; i < cookiesf.length; i++) {
						if (cookiesf[i].getName().equals("sample.accessToken")) {
							accessToken = cookiesf[i].toString();
						}
					}

					HttpClient httpClient_D = new HttpClient();

					GetMethod getMethod_B = new GetMethod("http://localhost:8080/consumer/TestProvider?queryType=ip");
					getMethod_B.setRequestHeader("Content-Type", "text/html;charset=UTF-8");
					// the data being returned is gzip
					// getMethod.setRequestHeader("Accept-Encoding","gzip, deflate");
					getMethod_B.setRequestHeader("Accept-Language", "en-US,en;q=0.5");
					getMethod_B.setRequestHeader("Connection", "Keep-Alive");
					getMethod_B.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)");
					getMethod_B.setRequestHeader("Host", "	localhost:8080");
					getMethod_B.setRequestHeader("Cookie", "sample.tokenSecret=" + tokenSecret + "; sample.accessToken=" + accessToken);

					int statusCode_B = httpClient_D.executeMethod(getMethod_B);
					System.out.println("result==" + statusCode_B);

					byte[] responseBody = getMethod_B.getResponseBody();
					System.out.println(new String(responseBody));

				}
			}
		}
	}

}
