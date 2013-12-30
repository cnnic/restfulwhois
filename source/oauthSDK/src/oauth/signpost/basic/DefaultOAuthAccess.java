package oauth.signpost.basic;

import java.util.ArrayList;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthUser;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class DefaultOAuthAccess {

	public static String getReponse(DefaultOAuthConsumer defaultOAuthConsumer, 
			DefaultOAuthProvider defaultOAuthProvider, OAuthUser oauthUser, String callBackUrl) {
		
		String result = null;
		try {
			defaultOAuthProvider.retrieveRequestToken(defaultOAuthConsumer, OAuth.OUT_OF_BAND);
			ArrayList<NameValuePair> pairList = new ArrayList<NameValuePair>();
			pairList.add(new BasicNameValuePair("oauth_token", defaultOAuthConsumer.getToken()));
			pairList.add(new BasicNameValuePair("userId", oauthUser.getUserId().trim()));
			pairList.add(new BasicNameValuePair("password", oauthUser.getPassword().trim()));

			DefaultHttpClient httpClient_B = new DefaultHttpClient();

			CookieStore cookieStore_B = new BasicCookieStore();

			HttpContext httpContext_B = new BasicHttpContext();
			httpContext_B.setAttribute(ClientContext.COOKIE_STORE, cookieStore_B);

			HttpPost httpPost = new HttpPost(defaultOAuthProvider.getAuthorizationWebsiteUrl());

			httpPost.setEntity(new UrlEncodedFormEntity(pairList, "UTF-8"));
			HttpResponse response_B = httpClient_B.execute(httpPost, httpContext_B);

			if (response_B.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				defaultOAuthProvider.setOAuth10a(true);
				defaultOAuthProvider.retrieveAccessToken(defaultOAuthConsumer, null);
//				System.out.println("Access token: " + consumer.getToken());
//				System.out.println("Token secret: " + consumer.getTokenSecret());

				HttpClient httpClient_D = new HttpClient();

				GetMethod getMethod_B = new GetMethod(callBackUrl);
				getMethod_B.setRequestHeader("Content-Type", "text/html;charset=UTF-8");
				// the data being returned is gzip
				// getMethod.setRequestHeader("Accept-Encoding","gzip, deflate");
				getMethod_B.setRequestHeader("Accept-Language", "en-US,en;q=0.5");
				getMethod_B.setRequestHeader("Connection", "Keep-Alive");
				getMethod_B.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)");
				getMethod_B.setRequestHeader("Host", "	localhost:8080");
				getMethod_B.setRequestHeader("Cookie", "sample.tokenSecret=" + defaultOAuthConsumer.getTokenSecret() + "; sample.accessToken="+ defaultOAuthConsumer.getToken());

				int statusCode_B = httpClient_D.executeMethod(getMethod_B);
				System.out.println("result==" + statusCode_B);

				byte[] responseBody = getMethod_B.getResponseBody();
				result = new String(responseBody);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
