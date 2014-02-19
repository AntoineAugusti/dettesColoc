package com.enflatme.webservices;

public class NoResultFoundException extends Exception {
	
	/**
	 * What the ** is that number ?
	 */
	private static final long serialVersionUID = 1L;

	protected NoResultFoundException(){}

	protected NoResultFoundException(String string) {
		super(string);
	}

}
