package siteParis;

import java.util.Collection;


public class Joueur {

	/**
	 * @uml.property  name="nom"
	 */
	public String nom;
	
	/**
	 * @uml.property  name="prenom"
	 */
	private String prenom;
	/**
	 * @uml.property  name="pseudo"
	 */
	private String pseudo;
	/**
	 * @uml.property  name="compte"
	 */
	// password de joueur
   private String password;
   
   private long compte;
	/**
	 * Getter of the property <tt>compte</tt>
	 * @return  Returns the compte.
	 * @uml.property  name="compte"
	 */
   
   // Mise en cours
   private long miseEnCours;
   
   
    
   // le constructeur pour Joueur
   public Joueur(String nom, String prenom, String pseudo){
      this.nom = nom;
      this.prenom = prenom;
      this.pseudo = pseudo;
      this.password = "";
      this.compte = 0;
      this.miseEnCours = 0;
   }
   
	public String getNom() {
		return this.nom;
	}   

	public String getPrenom() {
		return this.prenom;
	}
   
   public String getPseudo() {
		return this.pseudo;
	}
   public String getPassword() {
		return this.password;
	}
   public void setPassword(String s) {
		this.password = s;
	}  
	public long getCompte() {
		return compte;
	}
	public long getMiseEnCours() {
		return miseEnCours;
	}
	public void setMiseEnCours(long miseEnCours) {
		this.miseEnCours = this.miseEnCours + miseEnCours;
	}
	public void setCompte(long compte) {
		this.compte = this.compte + compte;
	}
		
		/**
		 */
		public Joueur(String nom, String prenom, String pseudo, long compte){
		}

}
