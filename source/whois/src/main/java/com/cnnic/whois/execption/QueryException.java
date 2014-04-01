package com.cnnic.whois.execption;

public class QueryException extends Exception {
	/**
	 * constructed a object
	 */
	public QueryException() {
	}

	/**
	 * Use msg constructed a new object
	 * 
	 * @param msg
	 */

	public QueryException(String msg) {
		super(msg);
	}

	/**
	 * Use msg constructed a new object
	 * 
	 * @param msg
	 */
	public QueryException(Exception msg) {
		super(msg);
	}
}