/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Chargé de labo : Simon Robert
Nom du fichier : ListeChainee.java
Date créé : 2016-01-12
Date dern. modif. 2016-02-15
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/


package main_package;

/**
 * 
 * Classe de listeChainee basée sur les notes de cours de INF111
 *
 */
public class ListeChainee {
	private class Noeud {

		public Object element;
		public Noeud suivant;

		public Noeud(Object element, Noeud lien) {
			this.element = element;
			suivant = lien;
		}
	}

	Noeud debut;
	Noeud fin;
	int nbElement;
	Noeud positioncourant;

	/**
	 * Constructeur par défaut
	 */
	public ListeChainee() {
	}

	/**
	 * Met l'objet à la fin de la liste
	 * 
	 * @param la
	 *            valeur à enfiler
	 */
	public void enfile(Object element) {

		// Si la liste est vide on enfile au début de la liste
		if (debut == null) {
			debut = new Noeud(element, null);
			positioncourant = debut;
			fin = debut;
		}

		else {
			fin.suivant = new Noeud(element, null);
			fin = fin.suivant;
		}

		nbElement++;
	}

	/**
	 * Methode permettant d'inserer la valeur avant la position courante
	 * 
	 * @param donnee
	 *            Object devant etre inserer
	 */
	public void insererAvant(Object donnee) {

		// Cree un nouveau noeud pour copier la positionCourant
		Noeud nouveau = new Noeud(positioncourant.element, positioncourant.suivant);

		// Place les nouvelles donnees dans la positionCourant
		if (positioncourant != fin) {
			positioncourant.element = donnee;
			positioncourant.suivant = nouveau;
		} else {
			fin = new Noeud(positioncourant.element, null);
			positioncourant.element = donnee;
			positioncourant.suivant = fin;
		}

		if (nbElement == 1) {
			fin = positioncourant.suivant;
			debut = positioncourant;
		}
		nbElement++;

	}

	/**
	 * Methode pour inserer la donnee apres la position courante
	 * 
	 * @param donnee
	 *            Object devant etre inserer
	 */
	public void insererApres(Object donnee) {
		if (positioncourant != fin) {
			Noeud nouveau = new Noeud(donnee, positioncourant.suivant);
			positioncourant.suivant = nouveau;
		} else {
			Noeud nouveau = new Noeud(donnee, null);
			positioncourant.suivant = nouveau;
			fin = nouveau;
		}
		nbElement++;
	}

	/**
	 * Avance la position courante de 1. Il s'agit d'une liste circulaire donc
	 * s'il n'y a pas de pochain, la position courante retourne au début
	 */
	public void suivant() {
		if (positioncourant.suivant != null)
			positioncourant = positioncourant.suivant;
		else
			positioncourant = debut;
	}

	/**
	 * Recule la position courante de 1. Il s'agit d'une liste circulaire donc
	 * s'il n'y a pas de precedent, la position courante retourne a la fin
	 */
	public void precedent() {
		if (positioncourant == debut)
			positioncourant = fin;
		else {
			Noeud tempo = positioncourant;
			positioncourant = debut;
			while (positioncourant.suivant != tempo) {
				positioncourant = positioncourant.suivant;
			}
		}
	}

	/**
	 * Deplace la position courante au debut
	 */
	public void PositionDebut() {
		positioncourant = debut;
	}

	/**
	 * Deplace la position courante a la fin
	 */
	public void PositionFin() {
		positioncourant = fin;
	}

	/**
	 * Retourne la valeur du début de la liste, donc l'élément du début n'est
	 * plus dans la liste
	 * 
	 * @return le premier élément de la liste
	 */
	public Object defile() {
		Object element = null;

		element = debut.element;
		debut = debut.suivant;

		// S'il n'y a qu'un élément dans la liste
		if (nbElement == 1)
			fin = null;

		nbElement--;

		return element;
	}

	/**
	 * Retourne si la liste est vide
	 * 
	 * @return vrai si la liste est vide et faux sinon
	 */
	public boolean estVide() {
		return debut == null;
	}

	/**
	 * Permet de récupérer la valeur du début de la liste sans l'enlever de la
	 * liste
	 * 
	 * @return le premier de la liste
	 */
	public Object defileSansEnlever() {

		// S'il n'y a aucun élément dans la liste
		if (nbElement == 0)
			return null;
		else
			return debut.element;
	}

	/**
	 * Retourne le nombre d'éléments dans la liste
	 * 
	 * @return nombre d'éléments dans la liste
	 */
	public int getNbELement() {
		return nbElement;
	}

	/**
	 * Defile sans retirer l'element a la position courante
	 * 
	 * @return Retourne l'objet a la position courante
	 */
	public Object defilePositionCourant() {
		if (nbElement == 0)
			return null;
		else
			return positioncourant.element;
	}

	/**
	 * Verifie si le prochain object dans la liste est null
	 * 
	 * @return Vrai si le prochain objet est null
	 */
	public boolean getSuivantnull() {
		boolean returnValue;
		if (positioncourant.suivant == null)
			returnValue = true;
		else
			returnValue = false;

		return returnValue;
	}

	/**
	 * Verifie si l'objet precedent dans la liste est null
	 * 
	 * @return Vrai si l'objet precedent est null
	 */
	public boolean getPrecedentnull() {
		boolean returnValue;
		if (positioncourant == debut)
			returnValue = true;
		else {
			returnValue = false;
		}

		return returnValue;
	}

	/**
	 * Defile sans enlever le dernier Objet de la liste
	 * 
	 * @return
	 */
	public Object defileFin() {
		if (nbElement == 0)
			return null;
		else
			return fin.element;
	}

	/**
	 * Vide la liste
	 */
	public void vider() {

		debut = fin = null;
		nbElement = 0;
	}
}