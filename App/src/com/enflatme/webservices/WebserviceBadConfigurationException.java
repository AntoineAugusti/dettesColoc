package com.enflatme.webservices;

/**
 * Exception lancé lorsque le WebService est mal configuré
 * @author etienne
 */
public class WebserviceBadConfigurationException extends Exception {

	public WebserviceBadConfigurationException() {
		
	}
	
	public WebserviceBadConfigurationException(String message) {
		super(message);
	}
}
