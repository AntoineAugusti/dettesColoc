package com.enflatme.webservices;

/**
 * Exception lancé lorsque le WebService est mal configuré
 * Exception thrown when the WebService is not well configurated
 * Generally, it misses a Request.
 * @author etienne
 */
@SuppressWarnings("serial")
public class WebserviceBadConfigurationException extends Exception {

	public WebserviceBadConfigurationException() {
		
	}
	
	public WebserviceBadConfigurationException(String message) {
		super(message);
	}
}
