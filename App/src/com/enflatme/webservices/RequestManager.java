/**
 * Les classe qui vont étendre de AsyncTask vont
 * pour l'instant permettre d'éxecuter des requêtes
 * sur l'API. Elles vont donc récupérer les String renvoyées par
 * cette API.
 * 
 * TODO Il faut donc trouver un moyen propre d'enregistrer les addresses
 * des scripts. Et donc un moyen facile d'y accéder (interface ?)
 * 
 * TODO Par la suite les String devrons être parsées. Il faut donc les tester
 * puis les exploiter (sinon retourner un erreur).
 * 
 * TODO Une fois que les String au format JSON sont parsés, il faut bien
 * utiliser les objets. (Affichage ou calcul) 
 * 
 */
package com.enflatme.webservices;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import android.os.AsyncTask;

/**
 * Permits to execute asyncTasks on URLS and get the result easyly and notify those who listen to it when the job is done
 * @author Etienne
 */
public class RequestManager extends AsyncTask<String, Void, String> implements RequestDispatcher{
	
	// Pattern Listener
	private ArrayList<RequestManagerListener> listeners = new ArrayList<RequestManagerListener>();
	// The result that will be set if needed
	private String result = null;
	// The buffer that read the stream from the HTTP request
	private BufferedInputStream is;
	// The object that read the buffer
	private Scanner scanner;
	// The URL where the request will be sent
	private URL targetURL;
	// The HTTP méthode that will be used (essentially "POST" or "GET")
	private String requestMethod;
	// The parametres of the connection
	private int connectionTimout;
	private int readTimout;
	
	/**
	 * Create a new Request that will be sent on the target url, using the http method and the timouts connection parameters
	 * @param target the target URL on which the HTTP request will be done
	 * @param method the HTTP method that will be use ("POST" or "GET)
	 * @param connectionTimout
	 * @param readTimout
	 * @see RequestMethod
	 */
	public RequestManager(URL target, String method, int connectionTimout, int readTimout) {
		super();
		this.targetURL = target;
		this.requestMethod = method;
		this.connectionTimout = connectionTimout;
		this.readTimout = readTimout;
		
	}
	
	@Override
	protected String doInBackground(String... params) {
		try {
			executeRequest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Pas très propre
		return null;

	}

	private void executeRequest() throws IOException {
		try {
			HttpURLConnection conn = buildConnection();
			conn.connect();
			
			// int response = conn.getResponseCode();
			is = new BufferedInputStream(conn.getInputStream());
			scanner = new Scanner(is);
			result = scanner.next();
			scanner.close();
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	@Override
	protected void onPostExecute(String result) {
		notifyListeners();
	}
	/**
	 * Build the connection Object and configure it
	 * @return the connection that will be use 
	 * @throws IOException if there is a probleme openning the connection
	 */
	public HttpURLConnection buildConnection() throws IOException {
		HttpURLConnection conn = (HttpURLConnection) targetURL.openConnection();
		conn.setReadTimeout(readTimout);
		// ??
		conn.setUseCaches(false);
		// ??
		conn.setAllowUserInteraction(false);
		conn.setConnectTimeout(connectionTimout);
		// TODO penser à un moyen de configurer ça 
		conn.setRequestMethod(requestMethod);
		// TODO penser à un moyen de configurer ça
		conn.setDoInput(true);
		return conn;
	}

	
	/**
	 * Returns the result obtained by the HTTP request
	 * <em> Be careful to use it only when the method triggerAlertReaction of the {@link RequestManagerListener} is called
	 * @return the result of the http request
	 * @throws NoResultFoundException if there was no result
	 */
	public String getResult() throws NoResultFoundException {
		if (this.result == null)
			throw new NoResultFoundException(
					"No response found during the execution of the task");
		return this.result;
	}

	/*
	 *	Pattern Listeners 
	 */
	@Override
	public void notifyListeners() {
		for (RequestManagerListener listener : this.listeners) {
			listener.triggerRequestReaction();
		}
	}
	@Override
	public void addListener(RequestManagerListener listener) {
		this.listeners.add(listener);
	}
	@Override
	public void clearListeners() {
		this.listeners.clear();
	}
}
