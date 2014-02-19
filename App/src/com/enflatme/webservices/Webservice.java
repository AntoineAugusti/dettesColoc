package com.enflatme.webservices;

import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;


public class Webservice implements URLBuilder, ThreadAlertListener {
	/*
	 *	Variables de classe
	 */
	public static final int defaultConnectTimout = 15000;  /* milliseconds */
	public static final int defaultReadTimout = 10000;
	
	/*
	 * Variable d'instance
	 */
	/**
	 * The WebserviceCaller that created the WebService
	 */
	private WebServiceCaller caller;
	private int connectTimout = defaultConnectTimout;
	private int readTimout = defaultReadTimout;
	/**
	 * The RequestName that need to be thrown
	 */
	private RequestName requestName;
	/**
	 * The Request object that will run in background
	 */
	private Request request;
	/**
	 * The url for the connection
	 */
	private URL url;
	
	/*
	 * Constructeurs
	 */
	public Webservice(WebServiceCaller caller) {
		this.caller = caller;
	}
	
	public Webservice(WebServiceCaller caller, RequestName requestName) {
		this.caller = caller;
		this.requestName = requestName;
	}
	
	public Webservice(WebServiceCaller caller, RequestName requestName, int connectionTimout, int readTimout) {
		this.caller = caller;
		this.requestName = requestName;
		this.connectTimout = connectionTimout;
		this.readTimout = readTimout;
	}

	/*
	 * Méthodes
	 */
	/**
	 * Build a new request a prepare it to be launched
	 * @throws WebserviceBadConfigurationException If the Webservice is not entierly configurated (usually missing RequestName) 
	 * @throws WebserviceNetworkIssueException If the Webservice cannot detect an Internet connection
	 */
	public void launch() throws WebserviceBadConfigurationException, WebserviceNetworkIssueException {
		if (this.requestName == null)
			throw new WebserviceBadConfigurationException("Missing RequestName");
		
		ConnectivityManager connMgr = (ConnectivityManager) caller.getCallerContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connMgr.getActiveNetworkInfo() == null && !connMgr.getActiveNetworkInfo().isConnected())
			throw new WebserviceNetworkIssueException("Device not connected to the INTERNEEEET");
		
		try {
			url = urlBuilder(requestName);
			request = new Request(url, requestName.method.toString(), connectTimout, readTimout);
			request.addListener(this);
			request.execute("");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Accesseurs
	 */
	public void setRequestName(RequestName requestName) {
		this.requestName = requestName;
	}
	public void setConnexionTimout(int timout) {
		this.connectTimout = timout;
	}
	public void setReadTimout(int timout) {
		this.readTimout = timout;
	}
	public void setTimoutsToDefault() {
		this.connectTimout = defaultConnectTimout;
		this.readTimout = defaultReadTimout;
	}

	@Override
	public URL urlBuilder(RequestName requestName) throws MalformedURLException {
		return new URL(URLSOURCE+requestName.toString());
	}

	@Override
	public void triggerRequestReaction() {
		try {
			String result = this.request.getResult();
			this.caller.loadResult(result);
		} catch (NoResultFoundException e) {
			e.printStackTrace();
			this.caller.loadResult(null);
		}
	}
}
