package com.enflatme.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enflatme.R;
import com.enflatme.views.watchers.ConfirmationPasswordWatcher;
import com.enflatme.views.watchers.EmailAddressWatcher;

public class LoginActivity extends Activity {
	private EditText email;
	private EditText pwd;
	private EditText confirmationPwd;
	private TextView indicator;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		// On récupère les ressources correspondant aux vues
		// 3 EditText, 1 Text, 1 Button
		email = (EditText) findViewById(R.id.activity_login_et_email);
		pwd = (EditText) findViewById(R.id.activity_login_et_pwd);
		confirmationPwd = (EditText) findViewById(R.id.activity_login_et_conf_pwd_field);
		button = (Button) findViewById(R.id.activity_login_btn_login);
		// On désactive le bouton
		button.setEnabled(false);
		indicator = (TextView) findViewById(R.id.activity_login_t_label);

		// On fixe les Listener aux vues voulues
		EmailAddressWatcher emailWatcher = new EmailAddressWatcher(email,
				pwd, confirmationPwd, button);
		email.addTextChangedListener(emailWatcher);
		ConfirmationPasswordWatcher confirmationPasswordWatcher = new ConfirmationPasswordWatcher(
				pwd, confirmationPwd, button, indicator,
				getResources().getColor(R.color.blue),
				getResources().getColor(R.color.red));

		confirmationPwd
				.addTextChangedListener(confirmationPasswordWatcher);

		// On gère le resize avec le fullscreen
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void triggerConnexion(View view){
		// Si lors de l'appui sur le bouton le champ de mdp est vide
		if (this.pwd.getText().toString().equals("")){
			Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.dialog_missing_pwd).setTitle(R.string.dialog_missing_pwd_title);
			builder.setPositiveButton(R.string.dialog_missing_pwd_ok_btn, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {}
			});
			builder.create();
			builder.show();
		}
			
	}
}
