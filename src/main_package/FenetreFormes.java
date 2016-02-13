package main_package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

import Forme.Forme;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetreFormes.java
Date crÃ©Ã©: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/

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
	private int indexForme;

	/**
	 * Constructeur
	 */
	public FenetreFormes() {
		formetab = new Forme[10];
		indexForme = 0;
	}

	/*
	 * Affiche la liste de formes
	 */
	@Override
	public void paintComponent(Graphics g) {
		for (int i = 0; i < formetab.length; i++) {
			if (formetab[i] != null) {
				formetab[i].draw(g);
				/*
				Graphics2D g2 = (Graphics2D) g;
				float dash[] = { 10.0f };
				g2.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
				g2.setPaint(Color.BLACK);
				if (formetab[i].getType() == "CARREE" || formetab[i].getType() == "RECTANGLE" || formetab[i].getType() == "LIGNE") {
					Rectangle r = new Rectangle(formetab[i].getCoordX(), formetab[i].getCoordY(),
							formetab[i].getInfo1() - formetab[i].getCoordX(),
							formetab[i].getInfo2() - formetab[i].getCoordY());
					g2.draw(r);

				} else if (formetab[i].getType() == "OVALE" || formetab[i].getType() == "CERCLE") {
					Rectangle r = new Rectangle(formetab[i].getCoordX() - formetab[i].getInfo1(),
							formetab[i].getCoordY() - formetab[i].getInfo2(), formetab[i].getInfo1(),
							formetab[i].getInfo2());
					g2.draw(r);
					*/
				}
				
			}

		}
	//}

	public void addForme(Forme forme) {
		if (indexForme > 9) {
			indexForme = 0;
		}
		formetab[indexForme] = forme;
		indexForme++;
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
}
