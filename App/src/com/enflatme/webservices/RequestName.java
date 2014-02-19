package com.enflatme.webservices;


public enum RequestName {
	TEST(RequestMethod.GET);
	
	public final RequestMethod method;
	
	RequestName(RequestMethod method) {
		this.method = method;
	}
	
	public String toString() {
		switch(this) {
		case TEST :
			return "test.php";
			
		default:
			return "";
		}
	}
}

enum RequestMethod {
	POST,
	GET;
	
	public String toString() {
		switch (this) {
		case POST:
			return "POST";

		case GET:
			return "GET";
					
		default:
			return "";
		}
	}
}
