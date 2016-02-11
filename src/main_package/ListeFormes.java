package main_package;

import Forme.CreateurFormes;

public class ListeFormes {
	private final int maxTaille = 10;
	private boolean isFull;

	private static ListeFormes uniqueInstance;

	private CreateurFormes cr;
	private ListeChainee listeChainee;

	private ListeFormes() {
		listeChainee = new ListeChainee();
		isFull = false;
		cr = new CreateurFormes();
	}

	/**
	 * Retourne le même instance qui est créé dans le programme 
	 */
	public static ListeFormes getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ListeFormes();
		}
		return uniqueInstance;
	}

	/**
	 * Ajoute une forme dans la liste de Forme
	 * 
	 * @param chaine
	 *            chaine de caractère provenant du serveur de formes
	 */
	public void ajouterForme(String chaine) {

		if (!isFull) {
			// Ajoute à la dernière place vide
			listeChainee.add(cr.creerForme(chaine));
			
		} else {
			// Efface la plus ancienne et ajoute le nouveau en dernier
			listeChainee.delete();
			listeChainee.add(cr.creerForme(chaine));
		}

		if (listeChainee.nombreElements() == maxTaille) {
			isFull = true;
		}
	}

	public void afficherListe(){
		listeChainee.show(); 
	}
	
	/**
	 * Retourne la liste de Forme
	 */
	public ListeChainee getListeForme() {
		return listeChainee;
	}
}

