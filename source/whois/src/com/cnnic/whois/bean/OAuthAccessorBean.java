package com.cnnic.whois.bean;

public class OAuthAccessorBean {

	private String requestToken;

	private String accessToken;

	private String tokenSecret;

	public OAuthAccessorBean() { }

	public OAuthAccessorBean(String requestToken, String accessToken,
			String tokenSecret) {
		this.requestToken = requestToken;
		this.accessToken = accessToken;
		this.tokenSecret = tokenSecret;
	}

	public String getRequestToken() {
		return requestToken;
	}

	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

}
