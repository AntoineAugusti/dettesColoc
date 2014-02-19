package com.enflatme.webservices;

import java.net.MalformedURLException;
import java.net.URL;


public interface URLBuilder {
	String URLSOURCE = "http://enflat.me/scripts/";
	
	URL urlBuilder(Request requestName) throws MalformedURLException;
}
