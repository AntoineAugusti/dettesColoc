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
 * <h1> Notice d'utilisation </h1>
 * <p> Il est toujours intéressant de savoir lorsqu'un processus de fond s'est terminé.
 * Pour cela on va implémenter le pattern Listener sur cette classe.
 * </p>
 * <p>
 * Néanmoins elle reste particulère. Si on cherche des données, on reçoit du JSON
 * Si on veut executer des modifications, on ne reçoit rien (un code d'erreur peut être ?)
 * Donc dans le second cas le champ result peut ne pas être utilisé.
 * </p>
 * @author Etienne
 */
public class RequestManager extends AsyncTask<String, Void, String> implements ThreadAlertDispatcher{
	
	// Pattern Listener
	private ArrayList<ThreadAlertListener> listeners = new ArrayList<ThreadAlertListener>();
	private String result = null;
	private BufferedInputStream is;
	private Scanner scanner;
	private URL targetURL;
	private String requestMethod;
	private int connectionTimout;
	private int readTimout;
	
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

	@Override
	protected void onPostExecute(String result) {
		notifyListeners();
	}
	
	public String getResult() throws NoResultFoundException {
		if (this.result == null)
			throw new NoResultFoundException(
					"No response found during the execution of the task");
		return this.result;
	}

	/*
	 *	Listeners 
	 */
	@Override
	public void notifyListeners() {
		for (ThreadAlertListener listener : this.listeners) {
			listener.triggerRequestReaction();
		}
	}

	@Override
	public void addListener(ThreadAlertListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void clearListeners() {
		this.listeners.clear();
	}
}
