package com.cnnic.whois.dao.oauth;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.oauth.OAuthAccessorBean;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.OAuthServiceProvider;
import net.oauth.OAuthValidator;
import net.oauth.SimpleOAuthValidator;
import net.oauth.server.OAuthServlet;

/**
 * Utility methods for providers that store consumers, tokens and secrets in
 * local cache (HashSet). Consumer key is used as the name, and its credentials
 * are stored in HashSet.
 */
@Repository
public class OAuthProvider {
	
//	private static UserAppDao userAppDao = new UserAppDaoImpl();
//	private static OAuthAccessorDao oauthAccessorDao = new OAuthAccessorDaoImpl();
	
	@Autowired
	private UserAppDao userAppDao;
	@Autowired
	private OAuthAccessorDao oauthAccessorDao;
	
	public static final OAuthValidator VALIDATOR = new SimpleOAuthValidator();

	private static final Map<String, OAuthConsumer> ALL_CONSUMERS = Collections
			.synchronizedMap(new HashMap<String, OAuthConsumer>(10));

	private static final Collection<OAuthAccessor> ALL_TOKENS = new HashSet<OAuthAccessor>();
	
	private static Properties consumerProperties = null;

	public void loadConsumers()
			throws IOException {
		Properties p = consumerProperties;
		if (p == null) {
			p = new Properties();
		}
		
		Map<String, String> mapValue = userAppDao.getUserApps();
		Set<Map.Entry<String, String>> set = mapValue.entrySet();
		for (Map.Entry<String, String> entry : set) {
			p.put(entry.getKey(), entry.getValue());
		}

		consumerProperties = p;

		// for each entry in the properties file create a OAuthConsumer
		for (Map.Entry prop : p.entrySet()) {
			String consumer_key = (String) prop.getKey();
			// make sure it's key not additional properties
			if (!consumer_key.contains(".")) {
				String consumer_secret = (String) prop.getValue();
				if (consumer_secret != null) {
					String consumer_description = (String) p.getProperty(consumer_key + ".description");
					String consumer_callback_url = (String) p.getProperty(consumer_key + ".callbackURL");
					// Create OAuthConsumer w/ key and secret
					OAuthConsumer consumer = new OAuthConsumer(
							consumer_callback_url, consumer_key, consumer_secret, null);
					consumer.setProperty("name", consumer_key);
					consumer.setProperty("description", consumer_description);
					ALL_CONSUMERS.put(consumer_key, consumer);
				}
			}
		}

	}

	public static OAuthConsumer getConsumer(
			OAuthMessage requestMessage) throws IOException,
			OAuthProblemException {

		OAuthConsumer consumer = null;
		// try to load from local cache if not throw exception
		String consumer_key = requestMessage.getConsumerKey();

		consumer = OAuthProvider.ALL_CONSUMERS.get(consumer_key);

		if (consumer == null) {
			OAuthProblemException problem = new OAuthProblemException("token_rejected");
			throw problem;
		}

		return consumer;
	}

	/**
	 * Get the access token and token secret for the given oauth_token.
	 */
	public OAuthAccessor getAccessor(
			OAuthMessage requestMessage) throws IOException,
			OAuthProblemException {

		// try to load from local cache if not throw exception
		String consumer_token = requestMessage.getToken();
		OAuthAccessor accessor = null;
		
		List<OAuthAccessorBean> oauthAccessorBeanList = oauthAccessorDao.getOAuthAccessorBeans();
		for(OAuthAccessorBean oauthAccessorBean : oauthAccessorBeanList){
			accessor = new OAuthAccessor(
					oauthAccessorBean.getRequest_token(), oauthAccessorBean.getAccess_token(), oauthAccessorBean.getToken_secret(), 
					new OAuthConsumer("/OAuth/Callback", oauthAccessorBean.getApp_key(), oauthAccessorBean.getApp_secret(), 
					new OAuthServiceProvider("request_token", "authorize", "access_token")
			));
			ALL_TOKENS.add(accessor);
		}
		
		for (OAuthAccessor a : OAuthProvider.ALL_TOKENS) {
			if (a.requestToken != null) {
				if (a.requestToken.equals(consumer_token)) {
					accessor = a;
					break;
				}
			} else if (a.accessToken != null) {
				if (a.accessToken.equals(consumer_token)) {
					accessor = a;
					break;
				}
			}
		}

		if (accessor == null) {
			OAuthProblemException problem = new OAuthProblemException(
					"token_expired");
			throw problem;
		}

		return accessor;
	}

	/**
	 * Set the access token
	 */
	public static void markAsAuthorized(OAuthAccessor accessor,
			String userId) throws OAuthException {

		// first remove the accessor from cache
		ALL_TOKENS.remove(accessor);

		accessor.setProperty("user", userId);
		accessor.setProperty("authorized", Boolean.TRUE);

		// update token in local cache
		ALL_TOKENS.add(accessor);
	}

	/**
	 * Generate a fresh request token and secret for a consumer.
	 * 
	 * @throws OAuthException
	 */
	public void generateRequestToken(OAuthAccessor accessor)
			throws OAuthException {

		// generate oauth_token and oauth_secret
		String consumer_key = (String) accessor.consumer.getProperty("name");
		// generate token and secret based on consumer_key

		// for now use md5 of name + current time as token
		String token_data = consumer_key + System.nanoTime();
		String token = DigestUtils.md5Hex(token_data);
		// for now use md5 of name + current time + token as secret
		String secret_data = consumer_key + System.nanoTime() + token;
		String secret = DigestUtils.md5Hex(secret_data);

		accessor.requestToken = token;
		accessor.tokenSecret = secret;
		accessor.accessToken = null;
		
		oauthAccessorDao.save(new OAuthAccessorBean(
				accessor.requestToken, accessor.tokenSecret, accessor.accessToken, 
				accessor.consumer.consumerKey, accessor.consumer.consumerSecret));

		// add to the local cache
//		ALL_TOKENS.add(accessor);

	}

	/**
	 * Generate a fresh request token and secret for a consumer.
	 * 
	 * @throws OAuthException
	 */
	public void generateAccessToken(OAuthAccessor accessor)
			throws OAuthException {

		// generate oauth_token and oauth_secret
		String consumer_key = (String) accessor.consumer.getProperty("name");
		// generate token and secret based on consumer_key

		// for now use md5 of name + current time as token
		String token_data = consumer_key + System.nanoTime();
		String token = DigestUtils.md5Hex(token_data);
		// first remove the accessor from cache
//		ALL_TOKENS.remove(accessor);
		
		OAuthAccessorBean oauthAccessorBean = oauthAccessorDao.getOAuthAccessorBeanByTokenAndSecret(accessor.requestToken, accessor.tokenSecret);
		if(oauthAccessorBean != null){
//			, String.valueOf(accessor.getProperty("user")
			oauthAccessorDao.updateAccessTokenByTokenAndSecret(oauthAccessorBean.getRequest_token(), oauthAccessorBean.getToken_secret(), token);
		}
		
		accessor.requestToken = null;
		accessor.accessToken = token;
		
		// update token in local cache
//		ALL_TOKENS.add(accessor);
	}

	public static void handleException(Exception e, HttpServletRequest request,
			HttpServletResponse response, boolean sendBody) throws IOException,
			ServletException {
		String realm = (request.isSecure()) ? "https://" : "http://";
		realm += request.getLocalName();
		OAuthServlet.handleException(response, e, realm, sendBody);
	}

}
