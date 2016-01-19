package main_package;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import Forme.CreateurFormes;
import Forme.Forme;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetreFormes.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/

/**
 * Cette fenêtre gère l'affichage des formes
 * 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetreFormes extends JComponent {

	private static final long serialVersionUID = -2262235643903749505L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Dimension dimension = new Dimension(500, 500);
	public String test1 = "45056 <LIGNE> 234 164 409 331 </LIGNE>";
	public String test2 = "61442 <CARRE> 195 332 210 347 </CARRE>";
	public String test3 = "57347 <RECTANGLE> 397 309 489 364 </RECTANGLE>";
	public Forme forme1;
	public Forme forme2;
	public Forme forme3;

	/**
	 * Constructeur
	 */
	public FenetreFormes() {
		// ...
	}

	/*
	 * Affiche la liste de formes
	 */
	@Override
	public void paintComponent(Graphics g) {
		forme1 = CreateurFormes.creerForme(test1);
		forme1.draw(g);

		forme2 = CreateurFormes.creerForme(test2);
		forme2.draw(g);
		
		forme3 = CreateurFormes.creerForme(test3);
		forme3.draw(g);

	}

	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit réserver l'espace
	 * nécessaire à son affichage
	 */
	@Override
	public Dimension getPreferredSize() {
		return dimension;
	}
}
