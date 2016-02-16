/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Chargé de labo : Simon Robert
Nom du fichier : FenetreFormes.java
Date créé : 2016-01-12
Date dern. modif. 2016-02-15
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/
package main_package;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import Forme.Forme;

/**
 * Cette fenÃªtre gÃ¨re l'affichage des formes
 * 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetreFormes extends JComponent {

	private static final long serialVersionUID = -2262235643903749505L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Dimension dimension = new Dimension(500, 500);
	private Forme formetab[];
	private ListeChainee liste;
	private ListeChainee listeTriee;

	/**
	 * Constructeur
	 */
	public FenetreFormes() {
		liste = new ListeChainee();
		listeTriee = new ListeChainee();
	}

	/*
	 * Affiche la liste de formes
	 */
	@Override
	public void paintComponent(Graphics g) {
		if (listeTriee.getNbELement() == 20) {
			while (listeTriee.defileSansEnlever() != null) {
				Forme forme = (Forme) listeTriee.defile();
				forme.draw(g);
			}
		}

	}

	// }

	public void addForme(Forme forme) {
		if(liste.getNbELement() <10)
			liste.enfile(forme);
			else
			{
				liste.vider();
				liste.enfile(forme);
			}
			this.repaint();
	}

	public void Trier(String methodeDeTri){
		try {
			if(this.liste.nbElement != 0)
				this.listeTriee = TriSelonMenu.selection(methodeDeTri, liste);
			else
			{
				JOptionPane.showOptionDialog(null,
		                   "Veuillez obtenir les formes avant de choisir parmis c'est options","Erreur Formes",
		                   JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.QUESTION_MESSAGE,
		                   null,
		                   null,
		                   null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit réserver l'espace
	 * nécessaire à  son affichage
	 */
	@Override
	public Dimension getPreferredSize() {
		return dimension;
	}

	public Forme[] getFormetab() {
		return formetab;
	}

	public void setFormetab(Forme formetab[]) {
		this.formetab = formetab;
	}
}
