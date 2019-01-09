package siteParis;

import java.util.Collection;
import java.util.LinkedList;

public class Competition {

	private String nom;
	/** 
	 * @uml.property name="date_debut"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="competition:siteParis.DateFrancaise"
	 */
	private DateFrancaise dateCloture;
	/**
	 * @uml.property  name="competiteur"
	 * @uml.associationEnd  multiplicity="(2 -1)" inverse="competition:siteParis.Competiteur"
	 */
	private String [] competiteurs ;

	/**
	 * @uml.property  name="gagnant"
	 */
	private String vainqueur;

   private LinkedList<Paris> paris ;


   public Competition(String competition, DateFrancaise dateCloture, String [] competiteurs){
      this.nom = competition;
      this.dateCloture = dateCloture;
      this.competiteurs = competiteurs;
      this.paris = new LinkedList<Paris>();
   
   }
	public String getNom() {
		return this.nom;
	} 
   
	public String getVainqueur() {
		return this.vainqueur;
	}
   public void setVainqueur(String vainqueur){
		this.vainqueur = vainqueur;
   }
	public DateFrancaise getDateCloture() {
		return this.dateCloture;
	}     
	public String [] getCompetiteurs() {
		return this.competiteurs;
	}          		

	public LinkedList<Paris> getParis() {
		return this.paris;
	}    

	public void addPari(Paris unPari) {
		this.paris.add(unPari);
	}       
   
   
	/** 
	 * Getter of the property <tt>date_debut</tt>
	 * @return  Returns the dateFrancaise.
	 * @uml.property  name="date_debut"
	 */
	public DateFrancaise getDate_debut() {
		return dateCloture;
	}

	/** 
	 * Setter of the property <tt>date_debut</tt>
	 * @param date_debut  The dateFrancaise to set.
	 * @uml.property  name="date_debut"
	 */
	public void setDate_debut(DateFrancaise date_debut) {
		this.dateCloture = date_debut;
	}





}
