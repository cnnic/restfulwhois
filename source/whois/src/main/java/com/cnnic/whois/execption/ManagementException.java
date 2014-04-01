package com.cnnic.whois.execption;

public class ManagementException extends Exception {
	/**
	 * constructed a object
	 */
	public ManagementException() {
	}

	/**
	 * Use msg constructed a new object
	 * 
	 * @param msg
	 */
	public ManagementException(String msg) {
		super(msg);
	}

	/**
	 * Use msg constructed a new object
	 * 
	 * @param msg
	 */
	public ManagementException(Exception msg) {
		super(msg);
	}
}