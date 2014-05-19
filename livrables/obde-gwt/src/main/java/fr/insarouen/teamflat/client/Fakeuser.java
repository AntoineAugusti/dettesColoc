package fr.insarouen.teamflat.client;

public class Fakeuser {
	private String login;
	private String mdp;
	
	public Fakeuser(String login, String mdp) {
		this.login = login;
		this.mdp = mdp;
	}
	
	public boolean credentialsAreValid() {
		return (login.equals("fred") && mdp.equals("azerty"));
	}
	
	public String returnErrorMessage() {
		if (!login.equals("fred"))
				return "Mauvais login";
		else if (login.equals("fred") && !mdp.equals("azerty"))
			return "Mauvais mot de passe";
		else
			return "Identifiants inconnus";
	}
}
