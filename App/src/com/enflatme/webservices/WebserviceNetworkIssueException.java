package com.enflatme.webservices;

/**
 * Exception lancé lorsqu'on tente de faire un opération en ligne alors que le device n'est pas connecté à Internet
 * @author etienne
 *
 */
public class WebserviceNetworkIssueException extends Exception {

	public WebserviceNetworkIssueException() {
	}

	public WebserviceNetworkIssueException(String detailMessage) {
		super(detailMessage);
	}
}
