/******************************************************
Cours:   LOG121
Session: H2013
Groupe:  
Projet: Laboratoire #1
Étudiant(e)s: Max St-Onge
              
              
Professeur : Francis Cardinal
Nom du fichier: ConnectionServeur.java
Date créé: 2014-01-15
Date dern. modif. 2013-02-04
*******************************************************
Historique des modifications
*******************************************************
2014-01-30 Version initiale
*******************************************************/  
package Forme;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Objet Carre, qui va permettre de dessiner un Carre dans notre Frame.
 * 
 */

public class Carre extends FormePrincipal{

	private int x;
	private int y;
	private int x2;
	private int y2;
	
	
		public Carre(String [] tabForme){
			x = Integer.parseInt(tabForme[2]);
			y = Integer.parseInt(tabForme[3]);
			x2 = Integer.parseInt(tabForme[4]);
			y2 = Integer.parseInt(tabForme[5]);
		}
		/**
		 * Permet de dessiner la forme dans le Frame
		 * 
		 * @param g
		 */
		public void dessinerForme(Graphics g){
			g.setColor(Color.BLACK);
			g.fillRect(x,y,x2-x,y2-y);
			
		}
}
