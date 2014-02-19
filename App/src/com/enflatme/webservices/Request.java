package com.enflatme.webservices;


public enum Request {
	TEST(RequestMethod.GET);
	
	public final RequestMethod method;
	
	Request(RequestMethod method) {
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
