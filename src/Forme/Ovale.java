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
 * Objet Ovale, qui va permettre de dessiner un ovale dans notre Frame.
 * 
 */

public class Ovale extends FormePrincipal{

	private int x;
	private int y;
	private int rayonH;
	private int rayonV;
	
	
		public Ovale(String [] tabForme){
			x = Integer.parseInt(tabForme[2]);
			y = Integer.parseInt(tabForme[3]);
			rayonH = Integer.parseInt(tabForme[4]);
			rayonV = Integer.parseInt(tabForme[5]);
			
		}
		/**
		 * Permet de dessiner l'objet dans le Frame
		 * 
		 * @param g
		 */
		public void dessinerForme(Graphics g){
			g.setColor(Color.RED);
			g.fillOval(x,y,rayonH,rayonV);
			
			
		}
	
}
