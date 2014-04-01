package com.cnnic.whois.util;

import com.cnnic.whois.bean.Authentication;
/**
 * 
 * a mutex for authentication
 *
 */
public class AuthenticationHolder {
	
	/**
	 * Mutex
	 */
	private static final ThreadLocal<Authentication> thread = new ThreadLocal<Authentication>();

	/**
	 * lock mutex
	 * @param auth
	 */
	public static void setAuthentication(Authentication auth) {
		thread.set(auth);
	}

	/**
	 * unlock mutex
	 */
	public static void remove() {
		thread.remove();
	}

	/**
	 * get mutex
	 * @return
	 */
	public static Authentication getAuthentication() {
		return thread.get();
	}
}