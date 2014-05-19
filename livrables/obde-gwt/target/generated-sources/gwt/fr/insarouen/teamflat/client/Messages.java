package fr.insarouen.teamflat.client;

/**
 * Interface to represent the messages contained in resource bundle:
 * 	/home/aaugusti/.m2/obde/src/main/resources/fr/insarouen/teamflat/client/Messages.properties'.
 */
public interface Messages extends com.google.gwt.i18n.client.Messages {
  
  /**
   * Translated "Login".
   * 
   * @return translated "Login"
   */
  @DefaultMessage("Login")
  @Key("loginField")
  String loginField();

  /**
   * Translated "Mot de passe".
   * 
   * @return translated "Mot de passe"
   */
  @DefaultMessage("Mot de passe")
  @Key("passwordField")
  String passwordField();

  /**
   * Translated "Envoyer".
   * 
   * @return translated "Envoyer"
   */
  @DefaultMessage("Envoyer")
  @Key("sendButton")
  String sendButton();
}
