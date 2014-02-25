package com.enflatme.webservices;

/**
 * Exception throw whe you try to do an operation online whereas the device is not connected to the Internet
 * @author etienne
 *
 */
@SuppressWarnings("serial")
public class WebserviceNetworkIssueException extends Exception {

	public WebserviceNetworkIssueException() {
	}

	public WebserviceNetworkIssueException(String detailMessage) {
		super(detailMessage);
	}
}
