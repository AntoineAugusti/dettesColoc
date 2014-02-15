/**
 * 
 */
package com.enflatme.views.watchers;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enflatme.R;

/**
 * Classe exclusivement utilisé dans le {@link MainActivity}
 * Permet de gérer le label notifiant l'utilisateur si les deux mots de passe
 * demandés pour l'inscription sont les mêmes.
 * @author etienne
 */
public class ConfirmationPasswordWatcher implements TextWatcher {

	protected EditText pwd, confirmationPwd;
	protected Button button;
	protected TextView label;
	protected int goodColor, badColor;

	/**
	 * Permet de spécifier les vues sur lesquelles agit le Watcher
	 * @param pwd L'instance de l'EditText du mot de passe
	 * @param confirmationPwd L'instance de l'EditText de confirmation du mot de passe (celui que l'on va observer)
	 * @param button l'instance du bouton que l'on va modifier
	 * @param label l'instance du label que l'on va modifier
	 * @param goodColor couleur du label si les mdp sont égaux
	 * @param badColor couleur du label si les mdp ne sont pas égaux
	 */
	public ConfirmationPasswordWatcher(EditText pwd, EditText confirmationPwd, Button button, TextView label, int goodColor, int badColor) {
		this.pwd = pwd;
		this.confirmationPwd = confirmationPwd;
		this.button = button;
		this.label = label;
		this.goodColor = goodColor;
		this.badColor = badColor;
	}

	/** 
	 * Gère l'activation ou l'inactivation du bouton ainsi que le label 
	 * en fonction du champ de confirmation du mdp.
	 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
	 */
	@Override
	public void afterTextChanged(Editable s) {
		String confirmation = confirmationPwd.getText()
				.toString();
		String password = pwd.getText().toString();
		// Si le premier champ de mail est rempli
		if (!confirmation.equals("")) {
			// On regarde si le deux mdp sont les mêmes
			// et on agit en conséquence sur le label
			if (!password.equals(confirmation)) {
				button.setEnabled(false);
				label.setTextColor(badColor);
				label.setText(R.string.activity_login_t_unequal_pwd);
				label.setVisibility(View.VISIBLE);
			} else {
				button.setEnabled(true);
				label.setTextColor(goodColor);
				label.setText(R.string.activity_login_t_equal_pwd);
				label.setVisibility(View.VISIBLE);
			}
		}
		// Sinon on rend le label invisble
		else {
			label.setVisibility(View.INVISIBLE);
		}
	}

	/* Non implémentée
	 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	/* Non implémentée
	 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

}
