package com.enflatme.webservices;

import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * This class permits to make our database operations easier and do it in background
 * @author Etienne
 * TODO : Enable to cancel the service
 */
public class Webservice implements URLBuilder, RequestManagerListener {
	/*
	 *	Class variable
	 */
	/**
	 * The conection timout default value
	 */
	public static final int defaultConnectTimout = 15000;  /* milliseconds */
	/**
	 * The read timout default value
	 */
	public static final int defaultReadTimout = 10000;
	
	/*
	 * Variable d'instance
	 */
	// The WebserviceCaller that created the WebService
	private WebServiceObserver observer;
	// The connection timout that can be set
	private int connectTimout = defaultConnectTimout;
	// The read timout that can be set
	private int readTimout = defaultReadTimout;
	// The Request that need to be executed
	private Request request;
	// The Request object that will run in background
	private RequestManager requestManager;
	// The url for the connection
	private URL url;
	
	/*
	 * Constructeurs
	 */
	/**
	 * Creates an new Webservice observed by and {@link WebServiceObserver}
	 * <em>Don't forget to set the request to avoid {@link WebserviceBadConfigurationException}</em>
	 * @param observer the object that stares at the Webservice (generally the object that called the webservice)
	 * 
	 */
	public Webservice(WebServiceObserver observer) {
		this.observer = observer;
	}
	
	/**
	 * @see Webservice#Webservice(WebServiceObserver)
	 * @param request the request that will be executed
	 */
	public Webservice(WebServiceObserver caller, Request request) {
		this.observer = caller;
		this.request = request;
	}
	
	/**
	 * @see Webservice#Webservice(WebServiceObserver, Request)
	 * @param connectionTimout the connection timout that will configure the connection
	 * @param readTimout the read timout that waill configure the connection
	 */
	public Webservice(WebServiceObserver caller, Request requestName, int connectionTimout, int readTimout) {
		this.observer = caller;
		this.request = requestName;
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
		if (this.request == null)
			throw new WebserviceBadConfigurationException("Missing RequestName");
		
		ConnectivityManager connMgr = (ConnectivityManager) observer.getCallerContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connMgr.getActiveNetworkInfo() == null && !connMgr.getActiveNetworkInfo().isConnected())
			throw new WebserviceNetworkIssueException("Device not connected to the INTERNEEEET");
		
		try {
			url = urlBuilder(request);
			requestManager = new RequestManager(url, request.method.toString(), connectTimout, readTimout);
			requestManager.addListener(this);
			requestManager.execute("");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Accesseurs
	 */
	public void setRequestName(Request requestName) {
		this.request = requestName;
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

	/**
	 * Implementation of URLBuilder
	 * Will see be removed
	 */
	@Override
	public URL urlBuilder(Request requestName) throws MalformedURLException {
		return new URL(URLSOURCE+requestName.toString());
	}

	/**
	 * Implementation of WebServiceCaller
	 */
	@Override
	public void triggerRequestReaction() {
		try {
			String result = this.requestManager.getResult();
			this.observer.loadResult(result);
		} catch (NoResultFoundException e) {
			e.printStackTrace();
			this.observer.loadResult(null);
		}
	}
}
