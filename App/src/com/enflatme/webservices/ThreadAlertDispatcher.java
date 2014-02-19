package com.enflatme.webservices;

public interface ThreadAlertDispatcher {

	public void addListener(ThreadAlertListener listener);
	public void notifyListeners();
	public void clearListeners();
}
