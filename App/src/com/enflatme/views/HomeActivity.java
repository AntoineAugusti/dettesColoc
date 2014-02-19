package com.enflatme.views;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.enflatme.R;
import com.enflatme.webservices.Request;
import com.enflatme.webservices.RequestName;
import com.enflatme.webservices.WebServiceCaller;
import com.enflatme.webservices.Webservice;
import com.enflatme.webservices.WebserviceNetworkIssueException;

public class HomeActivity extends Activity implements WebServiceCaller {
	TextView text;
	TextView large;
	Request task;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		text = (TextView) findViewById(R.id.home_t);
		large = (TextView) findViewById(R.id.textView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void click(View view) {
		Webservice service = new Webservice(this, RequestName.TEST);
		try {
			service.launch();
		} catch (WebserviceBadConfigurationException e) {
			e.printStackTrace();
		} catch (WebserviceNetworkIssueException e) {
			alertBox();
			e.printStackTrace();
		}
	}

	/**
	 * Build and show a alert box telling there are no network connection
	 */
	public void alertBox() {
		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.dialog_missing_pwd).setTitle(R.string.dialog_missing_pwd_title);
		builder.setPositiveButton(R.string.dialog_missing_pwd_ok_btn, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {}
		});
		builder.create();
		builder.show();
	}

	public void triggerReaction(String temp) {
		try {
			JSONObject jsObject = new JSONObject(temp);
			String message = jsObject.getString("message");
			if (message != null) {
				Log.i("Message", message);
			} else {
				Log.i("Message", "null");
			}
			large.setText(temp);
		} catch (JSONException e) {
			Log.d(HomeActivity.class.getSimpleName(),"Erreur parsing JSON");
			e.printStackTrace();
		}
	}

	@Override
	public Context getCallerContext() {
		return this;
	}

	@Override
	public void loadResult(String result) {
		Log.i("WebService", result);
		triggerReaction(result);
	}
}
