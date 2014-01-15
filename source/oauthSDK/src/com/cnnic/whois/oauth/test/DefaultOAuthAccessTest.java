package com.cnnic.whois.oauth.test;

import oauth.signpost.OAuthUser;
import oauth.signpost.basic.DefaultOAuthAccess;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

public class DefaultOAuthAccessTest {

	private static final String consumerKey = "key1385973838215";
	
	private static final String consumerSecret = "secret1385973838215";
	
	private static final String requestTokenEndpointUrl = "http://localhost:8080/request_token";
	
	private static final String accessTokenEndpointUrl = "http://localhost:8080/access_token";
	
	private static final String authorizationWebsiteUrl = "http://localhost:8080/authorize";
	
	private static final String userId = "root";
	
	private static final String password = "root";
	
	private static final String callBackUrl = "http://localhost:8080/consumer/TestProvider?queryType=ip";
	
	
	public static void main(String[] args)  {
		System.out.println(DefaultOAuthAccess.getReponse(new DefaultOAuthConsumer(consumerKey, consumerSecret),
				new DefaultOAuthProvider(requestTokenEndpointUrl,accessTokenEndpointUrl,authorizationWebsiteUrl), 
						new OAuthUser(userId, password), 
						callBackUrl));
	}
	
}
