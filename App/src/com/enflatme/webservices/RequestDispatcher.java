package com.enflatme.webservices;

/**
 * Interface use to notify RequestListeners
 * Implementation of the pattern Listener
 * @author etienne
 *
 */
public interface RequestDispatcher {

	public void addListener(RequestManagerListener listener);
	public void notifyListeners();
	public void clearListeners();
}
