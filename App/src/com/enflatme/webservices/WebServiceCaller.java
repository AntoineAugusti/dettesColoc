package com.enflatme.webservices;

import android.content.Context;

public interface WebServiceCaller {

	Context getCallerContext();
	
	/**
	 * Return the input on the Webservice as a String
	 * @param result if there is an input, else <strong>null</strong>
	 */
	void loadResult(String result);
}
