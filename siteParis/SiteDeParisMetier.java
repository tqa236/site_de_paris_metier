package siteParis;


import java.util.LinkedList;
import java.util.Collection;


/**
 * 
 * @author Bernard Prou et Julien Mallet
 * <br><br>
 * La classe qui contient toutes les méthodes "Métier" de la gestion du site de paris. 
 * <br><br>
 * Dans toutes les méthodes :
 * <ul>
 * <li>un paramètre de type <code>String</code> est invalide si il n'est pas instancié.</li>
 *  <li>pour la validité d'un password de gestionnaire et d'un password de joueur :
 * <ul>
 * <li>       lettres et chiffres sont les seuls caractères autorisés </li>
 * <li>       il doit avoir une longueur d'au moins 8 caractères </li>
 * </ul></li>       
 * <li>pour la validité d'un pseudo de joueur  :
 * <ul>
 * <li>        lettres et chiffres sont les seuls caractères autorisés  </li>
 * <li>       il doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>       
 * <li>pour la validité d'un prénom de joueur et d'un nom de joueur :
 *  <ul>
 *  <li>       lettres et tiret sont les seuls caractères autorisés  </li>
 *  <li>      il  doit avoir une longueur d'au moins 1 caractère </li>
 * </ul></li>
 * <li>pour la validité d'une compétition  :       
 *  <ul>
 *  <li>       lettres, chiffres, point, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      elle  doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>       
 * <li>pour la validité d'un compétiteur  :       
 *  <ul>
 *  <li>       lettres, chiffres, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      il doit avoir une longueur d'au moins 4 caractères.</li>
 * </ul></li></ul>
 */

public class SiteDeParisMetier {
   private String passwordGestionnaire;
   private LinkedList<Joueur> joueurs;
   private LinkedList<Competition> competitions = new LinkedList<Competition>();

	/**
	 * constructeur de <code>SiteDeParisMetier</code>. 
	 * 
	 * @param passwordGestionnaire   le password du gestionnaire.   
	 * 
	 * @throws MetierException  levée 
	 * si le <code>passwordGestionnaire</code>  est invalide 
	 */
	public SiteDeParisMetier(String passwordGestionnaire) throws MetierException {
      if (passwordGestionnaire==null) throw new MetierException();
	   if (!passwordGestionnaire.matches("[0-9A-Za-z]{8,}")) throw new MetierException();
      
      this.passwordGestionnaire = passwordGestionnaire;
      this.joueurs= new LinkedList<Joueur>();

	}
   
   public String getPasswordGestionnaire() {
		return this.passwordGestionnaire;
	}   

	/**
	 * Cette fonction vérifie la validité des nom, prénom et pseudo 
	 * 
	 * @param nom  soit le nom du joueur soit son prénom soit son pseudo 
	 * 
	 * @taille   nombre de charactère minimal que doit contenir soit le nom soit le prénom soit le pseudo
	 * return true si le nom est valide 
    * return false si le nom est invalide
	 */
   public boolean verifier(String nom, int taille){
      if (nom==null) return false;
	   if (nom.length() < taille) return false;
      if (!nom.matches("[A-Za-z]{" + Integer.toString(taille)+",}" ))  
         for ( int i=0;i<nom.length();i++){
            char c = nom.charAt(i);
            if (!Character.isLetter(c)&&c!='-') return false ;
         }
      return true;
   }
   public boolean verifierPseudo(String nom, int taille){
      if (nom==null) return false;
	   if (!nom.matches("[0-9A-Za-z]{" + Integer.toString(taille)+",}")) return false;   
      return true;
   }

   public boolean verifierNomDeCompetition(String nom, int taille){
      if (nom==null) return false;
	   if (nom.length() < taille) return false;
      if (!nom.matches("[A-Za-z0-9]{" + Integer.toString(taille)+",}" ))  
         for ( int i=0;i<nom.length();i++){
            char c = nom.charAt(i);
            if (!Character.isLetter(c)&&c!='-'&&c!='_'&&c!='.') return false ;
         }
      return true;
   }

   public boolean verifierNomDeCompetiteur(String nom, int taille){
      if (nom==null) return false;
	   if (nom.length() < taille) return false;
      if (!nom.matches("[A-Za-z0-9]{" + Integer.toString(taille)+",}" ))  
         for ( int i=0;i<nom.length();i++){
            char c = nom.charAt(i);
            if (!Character.isLetter(c)&&c!='-'&&c!='_') return false ;
         }
      return true;
   }
	
   // Les méthodes du gestionnaire (avec mot de passe gestionnaire)

	/**
	 * inscrire un joueur. 
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée  
	 * si le <code>passwordGestionnaire</code> proposé est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurExistantException   levée si un joueur existe avec les mêmes noms et prénoms ou le même pseudo.
	 * @throws JoueurException levée si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 * 
	 * @return le mot de passe (déterminé par le site) du nouveau joueur inscrit.
	 */
	
   public String inscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurExistantException, JoueurException {
		if (passwordGestionnaire==null) throw new MetierException();
      
      if (!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
      if (!this.verifier(nom, 1))  throw new JoueurException();
      if (!this.verifier(prenom, 4))  throw new JoueurException();
      if (!this.verifierPseudo(pseudo, 4))  throw new JoueurException();
      for ( Joueur j:joueurs){
         if (nom.equals(j.getNom()) && prenom.equals(j.getPrenom())) throw new JoueurExistantException(); 
         if (pseudo.equals(j.getPseudo())) throw new JoueurExistantException();
      }
      Joueur j = new Joueur(nom,prenom,pseudo);
      joueurs.add(j);
      j.setPassword("unPasswordUnique");
      return "unPasswordUnique";
	}

	/**
	 * supprimer un joueur. 
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec le même <code>nom</code>, <code>prenom</code>  et <code>pseudo</code>.
	 * @throws JoueurException levée 
	 * si le joueur a des paris en cours,
	 * si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 * 
	 * @return le nombre de jetons à rembourser  au joueur qui vient d'être désinscrit.
	 * 
	 */
	public long desinscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurInexistantException, JoueurException {
		if (passwordGestionnaire==null) throw new MetierException();      
      if (!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();


      if (!this.verifier(nom, 1))  throw new JoueurException();
      if (!this.verifier(prenom, 4))  throw new JoueurException();
      if (!this.verifierPseudo(pseudo, 4))  throw new JoueurException();
      int flag=0;
      for (Joueur j:joueurs){
         if (nom.equals(j.getNom()) && prenom.equals(j.getPrenom())   ){
            
            flag=1;
   //On vérifie que les mises en cours sont nulles
   
            if (j.getMiseEnCours()==0){
               joueurs.remove(j);
               break;
            }
            else throw new JoueurException();        
         }
      }
      if (flag == 0) throw new JoueurInexistantException();
      
		return 0;
	}



	/**
	 * ajouter une compétition.  
	 * 
	 * @param competition le nom de la compétition
	 * @param dateCloture   la date à partir de laquelle il ne sera plus possible de miser  
	 * @param competiteurs   les noms des différents compétiteurs de la compétition 
	 * @param passwordGestionnaire  le password du gestionnaire du site 
	 * 
	 * @throws MetierException levée si le tableau des
	 * compétiteurs n'est pas instancié, si le
	 * <code>passwordGestionnaire</code> est invalide, si le
	 * <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionExistanteException levée si une compétition existe avec le même nom. 
	 * @throws CompetitionException levée si le nom de la
	 * compétition ou des compétiteurs sont invalides, si il y a
	 * moins de 2 compétiteurs, si un des competiteurs n'est pas instancié,
	 * si deux compétiteurs ont le même nom, si la date de clôture 
	 * n'est pas instanciée ou est dépassée.
	 */
	public void ajouterCompetition(String competition, DateFrancaise dateCloture, String [] competiteurs, String passwordGestionnaire) throws MetierException, CompetitionExistanteException, CompetitionException  {
      this.validitePasswordGestionnaire(passwordGestionnaire);
      if (!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
      if (competiteurs == null)  throw new MetierException();
      if (!this.verifierNomDeCompetition(competition, 4))  throw new CompetitionException();
      if (dateCloture == null) throw new CompetitionException();
      if (competiteurs.length < 2) throw new CompetitionException();
      for (int i=0; i < competiteurs.length;i++){
         if (!this.verifierNomDeCompetiteur(competiteurs[i], 4))  throw new CompetitionException();
      }
      for (int i=0; i < competiteurs.length;i++)  
         for (int j=0; j < competiteurs.length;j++)
   // On vérifie qu'il n'existe pas de compétiteur avec le meme nom
            
            if (competiteurs[i].equals(competiteurs[j]) && i != j) throw new CompetitionException();
      
      if (dateCloture.estDansLePasse()) throw new CompetitionException();
  // On vérifie que la date de cloture n'a pas été dépassé
      for (Competition c:competitions){
  // On vérifie que la compétition existe pas déja       
         if (competition.equals(c.getNom())) throw new CompetitionExistanteException();
      }
	   Competition c = new Competition(competition, dateCloture, competiteurs);
      competitions.add(c);
   }


	/**
	 * solder une compétition vainqueur (compétition avec vainqueur).  
	 * 
	 * Chaque joueur ayant misé sur cette compétition
	 * en choisissant ce compétiteur est crédité d'un nombre de
	 * jetons égal à :
	 * 
	 * (le montant de sa mise * la somme des
	 * jetons misés pour cette compétition) / la somme des jetons
	 * misés sur ce compétiteur.
	 *
	 * Si aucun joueur n'a trouvé le
	 * bon compétiteur, des jetons sont crédités aux joueurs ayant
	 * misé sur cette compétition (conformément au montant de
	 * leurs mises). La compétition est "supprimée" si il ne reste
	 * plus de mises suite à ce solde.
	 * 
	 * @param competition   le nom de la compétition  
	 * @param vainqueur   le nom du vainqueur de la compétition 
	 * @param passwordGestionnaire  le password du gestionnaire du site 
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide, (1)
	 * si le <code>passwordGestionnaire</code> est incorrect. (2)
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom. (3)
	 * @throws CompetitionException levée 
	 * si le nom de la compétition (4) ou du vainqueur est invalide, (5) 
	 * si il n'existe pas de compétiteur du nom du vainqueur dans la compétition, (6)
	 * si la date de clôture de la compétition est dans le futur. (7)
	 * 
	 */	
	public void solderVainqueur(String competition, String vainqueur, String passwordGestionnaire) throws MetierException,  CompetitionInexistanteException, CompetitionException  {
      // 1
      this.validitePasswordGestionnaire(passwordGestionnaire);
      // 2
      if (!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
      // 4
      if (!this.verifierNomDeCompetition(competition, 4))  throw new CompetitionException();
      // 5
      if (!this.verifierNomDeCompetiteur(vainqueur, 4))  throw new CompetitionException();    
      // 3
      int flag1 = 0;
      for (Competition c:competitions){
         if (competition.equals(c.getNom())){
            //7
            if (!c.getDate_debut().estDansLePasse()) throw new CompetitionException();
            flag1=1;
            // 6
            int flag2 = 0;
            String [] list = c.getCompetiteurs();
            for (String com:list){
               if (vainqueur.equals(com)){
                  flag2 = 1;  
               }
            }
            if (flag2 == 0) throw new CompetitionException();
                        
            // solder
            int flag3 = 0; // pour connaitre si il y a des joueurs qui ont trouvé le bon competiteur
            long somme = 0;
            long sommegagnant = 0;
            c.setVainqueur(vainqueur);
            for (Paris p:c.getParis()){
               somme = somme + p.getValeurMise();
               p.getJoueur().setMiseEnCours(0-p.getValeurMise());
               if (p.getCompetiteur().equals(c.getVainqueur())){
                  flag3 = 1;
                  sommegagnant = sommegagnant + p.getValeurMise();
               }
               
            }
            if (flag3 == 1) {
               for (Paris p:c.getParis()){
                  if (p.getCompetiteur().equals(c.getVainqueur())){
                     p.getJoueur().setCompte(p.getValeurMise()*somme/sommegagnant);
                     
                  }
               }
            }            
            // Si aucun joueur n'a trouvé le bon competiteur
            if (flag3 == 0) {
               for (Paris p:c.getParis()){
                  p.getJoueur().setCompte(p.getValeurMise());
               }
            }
            competitions.remove(c);
            break;
         }
      }
      if (flag1 == 0) throw new CompetitionInexistanteException();
	}



	/**
	 * créditer le compte en jetons d'un joueur du site de paris.
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param sommeEnJetons   la somme en jetons à créditer  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée  
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 */
	public void crediterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws MetierException, JoueurException, JoueurInexistantException {
      this.validitePasswordGestionnaire(passwordGestionnaire);
      if (!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
      if (!this.verifier(nom, 1))  throw new JoueurException();
      if (!this.verifier(prenom, 4))  throw new JoueurException();
      if (!this.verifierPseudo(pseudo, 4))  throw new JoueurException();
      
      if (sommeEnJetons < 0)throw new MetierException();
      
      
      int flag = 0;
      for (Joueur j:joueurs){
         if (nom.equals(j.getNom()) && prenom.equals(j.getPrenom()) && pseudo.equals(j.getPseudo())  ){
            
            flag=1;
            j.setCompte(sommeEnJetons);    
         }
      }
      if (flag == 0) throw new JoueurInexistantException();


	}


	/**
	 * débiter le compte en jetons d'un joueur du site de paris.
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param sommeEnJetons   la somme en jetons à débiter  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée  
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides 
	 * si le compte en jetons du joueur est insuffisant (il deviendrait négatif).
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 * 
	 */

	public void debiterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws  MetierException, JoueurInexistantException, JoueurException {
      this.validitePasswordGestionnaire(passwordGestionnaire);
      if (!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
      if (!this.verifier(nom, 1))  throw new JoueurException();
      if (!this.verifier(prenom, 4))  throw new JoueurException();
      if (!this.verifierPseudo(pseudo, 4))  throw new JoueurException();
	
      if (sommeEnJetons < 0)throw new MetierException();
      
      
      int flag = 0;
      for (Joueur j:joueurs){
         if (nom.equals(j.getNom()) && prenom.equals(j.getPrenom()) && pseudo.equals(j.getPseudo())  ){
            
            flag=1;
            if (sommeEnJetons > j.getCompte()) throw new JoueurException();
            j.setCompte(0-sommeEnJetons);
                
         }
      }
      if (flag == 0) throw new JoueurInexistantException();   


   
   }



	/** 
	 * consulter les  joueurs.
	 * 
	 * @param passwordGestionnaire  le password du gestionnaire du site de paris 

	 * @throws MetierException   levée  
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * 
	 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent un joueur avec dans l'ordre : 
	 *  <ul>
	 *  <li>       le nom du joueur  </li>
	 *  <li>       le prénom du joueur </li>
	 *  <li>       le pseudo du joueur  </li>
	 *  <li>       son compte en jetons restant disponibles </li>
	 *  <li>       le total de jetons engagés dans ses mises en cours. </li>
	 *  </ul>
	 */
	public LinkedList <LinkedList <String>> consulterJoueurs(String passwordGestionnaire) throws MetierException {
		
      this.validitePasswordGestionnaire(passwordGestionnaire);
      if (!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();  

      LinkedList<String> unJoueur = new LinkedList<String>();
      LinkedList <LinkedList <String>> joueursString = new LinkedList <LinkedList <String>>();

      for (Joueur j:joueurs){
         unJoueur = new LinkedList<String>();
         
         unJoueur.add(j.getNom());
         unJoueur.add(j.getPrenom());
         unJoueur.add(j.getPseudo());
         unJoueur.add(Long.toString(j.getCompte()));
         unJoueur.add(Long.toString(j.getMiseEnCours()));
         joueursString.add(unJoueur);
      }      
      
      
      return joueursString;
	}








	// Les méthodes avec mot de passe utilisateur



	/**
	 * miserVainqueur  (parier sur une compétition, en désignant un vainqueur).
	 * Le compte du joueur est débité du nombre de jetons misés.
	 * 
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordJoueur  le password du joueur  
	 * @param miseEnJetons   la somme en jetons à miser  
	 * @param competition   le nom de la compétition  relative au pari effectué
	 * @param vainqueurEnvisage   le nom du compétiteur  sur lequel le joueur mise comme étant le  vainqueur de la compétition  
	 * 
	 * @throws MetierException levée si la somme en jetons est négative. (1)
	 * @throws JoueurInexistantException levée si il n'y a pas de
	 * joueur avec les mêmes pseudos et password. (2)
	 * @throws CompetitionInexistanteException levée si il n'existe pas de compétition de même nom. (3) 
	 * @throws CompetitionException levée 
	 * si <code>competition</code> (4) ou <code>vainqueurEnvisage</code> sont invalides, (5)
	 * si il n'existe pas un compétiteur de  nom <code>vainqueurEnvisage</code> dans la compétition, (6)
	 * si la compétition n'est plus ouverte (la date de clôture est dans le passé). (7)
	 * @throws JoueurException   levée 
	 * si <code>pseudo</code> (8) ou <code>password</code> sont invalides, (9) 
	 * si le <code>compteEnJetons</code> du joueur est insuffisant (il deviendrait négatif). (10)
	 */
    
    public void miserVainqueur(String pseudo, String passwordJoueur, long miseEnJetons, String competition, String vainqueurEnvisage) throws MetierException, JoueurInexistantException, CompetitionInexistanteException, CompetitionException, JoueurException  {
    
      //1
      if (miseEnJetons < 0)throw new MetierException();   
      // 4
      if (!this.verifierNomDeCompetition(competition, 4))  throw new CompetitionException();
      // 8
      if (!this.verifierPseudo(pseudo, 4))  throw new JoueurException();
      // 9
      if (passwordJoueur==null) throw new JoueurException();
	   if (!passwordJoueur.matches("[0-9A-Za-z]{8,}")) throw new JoueurException();
      // 5
      if (!this.verifierNomDeCompetiteur(vainqueurEnvisage, 4))  throw new CompetitionException();
 
      // 3
      int flag1 = 0;
      for (Competition c:competitions){
         if (competition.equals(c.getNom())){
            flag1=1;
            //7
            if (c.getDate_debut().estDansLePasse()) throw new CompetitionException();
            // 2
            Joueur unJoueur = null;   
            int flag = 0;
            for (Joueur j:joueurs){
               if (passwordJoueur.equals(j.getPassword()) &&  pseudo.equals(j.getPseudo())  ){
               // 10
               flag=1;
               unJoueur = j;
               if (miseEnJetons > j.getCompte()) throw new JoueurException();
                
               }
            }
            if (flag == 0) throw new JoueurInexistantException(); 
            
            
            // 6
            int flag2 = 0;
            String [] list = c.getCompetiteurs();
            Paris unPari = null;
            for (String com:list){
               if (vainqueurEnvisage.equals(com)){
                  flag2 = 1;
                  unPari = new Paris(vainqueurEnvisage,unJoueur,miseEnJetons);
                  unJoueur.setCompte(0-miseEnJetons);
                  unJoueur.setMiseEnCours(miseEnJetons); 
                  c.getParis().add(unPari);
               }
            }
            if (flag2 == 0) throw new CompetitionException();
         }
      }
      if (flag1 == 0) throw new CompetitionInexistanteException();
      
      
	}

   
    

	// Les méthodes sans mot de passe


	/**
	 * connaître les compétitions en cours.
	 * 
	 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent une compétition avec dans l'ordre : 
	 *  <ul>
	 *  <li>       le nom de la compétition,  </li>
	 *  <li>       la date de clôture de la compétition. </li>
	 *  </ul>
	 */
	public LinkedList <LinkedList <String>> consulterCompetitions(){
      LinkedList<String> uneCompetition = new LinkedList<String>();
      LinkedList <LinkedList <String>> competitionsString = new LinkedList <LinkedList <String>>();

      for (Competition c:competitions){
         uneCompetition = new LinkedList<String>();
         uneCompetition.add(c.getNom());
         uneCompetition.add(c.getDateCloture().toString());
         competitionsString.add(uneCompetition);
      }      
      
      return competitionsString;
	} 

	/**
	 * connaître  la liste des noms des compétiteurs d'une compétition.  
	 * 
	 * @param competition   le nom de la compétition  
	 * 
	 * @throws CompetitionException   levée  
	 * si le nom de la compétition est invalide. (1)
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom.  (2)
	 * 
	 * @return la liste des compétiteurs de la  compétition. 
	 */
	public LinkedList <String> consulterCompetiteurs(String competition) throws CompetitionException, CompetitionInexistanteException{
      // 1
      if (!this.verifierNomDeCompetition(competition, 4))  throw new CompetitionException();
      // 2
      int flag1 = 0;
      LinkedList<String> lList = new LinkedList<String>();
      for (Competition c:competitions){
         if (competition.equals(c.getNom())){
            flag1=1;
            String [] list = c.getCompetiteurs();
            
            for (String com:list){
            lList.add(com);
            }
         }
      }
      if (flag1 == 0) throw new CompetitionInexistanteException();
      return lList;
      //return new LinkedList <String> ();
	}

	/**
	 * vérifier la validité du password du gestionnaire.
	 * 
	 * @param passwordGestionnaire   le password du gestionnaire à vérifier
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code> est invalide.  
	 */
	protected void validitePasswordGestionnaire(String passwordGestionnaire) throws MetierException {
	    if (passwordGestionnaire==null) throw new MetierException();
	    if (!passwordGestionnaire.matches("[0-9A-Za-z]{8,}")) throw new MetierException();
	}





}


