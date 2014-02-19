/**
 * 
 */
package com.enflatme.views.watchers;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.enflatme.R;
import com.enflatme.views.LoginActivity;

/**
 * Classe exclusivement utilisé dans {@link LoginActivity}
 * Permet de manipuler les vues du formulaire de connexion en fonction
 * de l'Input de l'utilisateur dans le champ mail.
 * @author Etienne 
 */
public class EmailAddressWatcher implements TextWatcher {
	protected EditText emailEditor, passwordEditor, confirmationPasswordEditor;
	protected Button button;

	/**
	 * Permet de spécifier sur quelles vue agit le Watcher
	 */
	public EmailAddressWatcher(EditText email, EditText pswd,
			EditText confirmationpswd, Button button) {
		emailEditor = email;
		passwordEditor = pswd;
		confirmationPasswordEditor = confirmationpswd;
		this.button = button;
	}

	private boolean emailAdressIsKnow(String address) {
		return address.equals("etienne.batise@gmail.com");
	}


	/**
	 * Gere l'affichage progressif des éléments. Si ce n'est pas une adresse
	 * mail, on ne laisse affiché que l'éditeur pour le mail.
	 * 
	 * Si c'est un adresse mail, mais qu'elle n'est pas reconnue on propose
	 * à l'utilisateur de s'inscrire (mdp et confirmation mdp)
	 * 
	 * Sinon c'est est utilisateur reconnu, on lui propose de rentrer son
	 * mot de passe et son login
	 */
	@Override
	public void afterTextChanged(Editable s) {
		// Si l'EditText du mail n'est pas vide
		if (emailEditor != null) {
			// On récupère son contenu
			String emailAddress = emailEditor.getText().toString();
			// Si le contenu est une adresse mail
			if (android.util.Patterns.EMAIL_ADDRESS.matcher(
					emailEditor.getText()).matches()) {
				// Si c'est une addresse qui est reconnu (déjà inscrite)
				// alors on affiche le formulaire de connexion (LOG IN)
				if (emailAdressIsKnow(emailAddress)) {
					// On affiche le premier champ de mot de passe
					// On fixe la bonne chaine
					// On le rend visible
					// On s'assure que le champ de confirmation de mdp (SIGN IN) n'est pas visible
					// On rend le bouton visible
					// On rend le bouton actif
					passwordEditor.setHint(R.string.activity_login_et_pwd_hint);
					button.setText(R.string.activity_login_btn_login);
					passwordEditor.setVisibility(View.VISIBLE);
					confirmationPasswordEditor.setVisibility(View.INVISIBLE);
					button.setVisibility(View.VISIBLE);
					button.setEnabled(true);
				} else {
					// Sinon on affiche le formulaire d'inscritpion (SIGN IN)
					passwordEditor.setHint(R.string.activity_login_et_new_pwd_hint);
					confirmationPasswordEditor
							.setHint(R.string.activity_login_et_conf_pwd_hint);
					button.setText(R.string.activity_login_btn_signup);

					passwordEditor.setVisibility(View.VISIBLE);
					confirmationPasswordEditor.setVisibility(View.VISIBLE);
					button.setVisibility(View.VISIBLE);
				}
			} else {
				// Sinon on s'assure que rien n'est visible à part le champ de mail
				passwordEditor.setVisibility(View.INVISIBLE);
				confirmationPasswordEditor.setVisibility(View.INVISIBLE);
				button.setVisibility(View.INVISIBLE);
			}
		}

	}

	/**
	 * Non implémentée
	 * 
	 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence,
	 * int, int, int)
	 */
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	/**
	 * Non implémentée
	 * 
	 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int,
	 * int, int)
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

}
