package com.cnnic.whois.util;

import java.util.HashMap;
import java.util.Map;

public class TimeCache {
	private static final Map<String, String> queryRemoteIPMap = new HashMap<String, String>();

	/**
	 * Get queryRemoteIPMap
	 * 
	 * @return Map
	 */
	public static Map<String, String> getQueryremoteipmap() {
		return queryRemoteIPMap;
	}

	/**
	 * According to the type of the role to control the user's query rate
	 * 
	 * @param key
	 * @param currentTime
	 * @param accessTime
	 * @param role
	 * @return true
	 */
	public boolean queryControl(String key, long currentTime, long accessTime,
			String role) {
		String userIp = queryRemoteIPMap.get(key);
		long time = currentTime - accessTime;

		if (userIp == null) {
			queryRemoteIPMap.put(userIp, userIp);
			return true;
		} else {
			if (role.equals(WhoisUtil.ANONYMOUS)) {
				if (time >= Long.parseLong(WhoisProperties.getAnonymousExpireTime()))
					return false;
			} else if (role.equals(WhoisUtil.AUTHENTICATED)) {
				if (time >= Long.parseLong(WhoisProperties.getAuthenticatedExpireTime()))
					return false;
			} else {
				if (time >= Long.parseLong(WhoisProperties.getRootExpireTime()))
					return false;
			}
			return true;
		}
	}
}
