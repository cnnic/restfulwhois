package com.cnnic.whois.util;

import com.cnnic.whois.bean.Authentication;

public class AuthenticationHolder {
	private static final ThreadLocal<Authentication> thread = new ThreadLocal<Authentication>();

	public static void setAuthentication(Authentication auth) {
		thread.set(auth);
	}

	public static void remove() {
		thread.remove();
	}

	public static Authentication getAuthentication() {
		return thread.get();
	}
}