package siteParis;

import java.util.Collection;


public class Paris {


	private Joueur joueur;
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	private String competiteur;
 	public String getCompetiteur() {
		return competiteur;
	}
	public void setCompetiteur(String competiteur) {
		this.competiteur = competiteur;
	}
     
	private long valeurMise;
	public long getValeurMise() {
		return valeurMise;
   }
	public void setValeurMise(long valeurMise) {
		this.valeurMise = valeurMise;
	}

		
	public Paris(String competiteur, Joueur joueur, long valeurMise){
      this.competiteur = competiteur;
      this.joueur = joueur;
      this.valeurMise = valeurMise;
      
		}

}
