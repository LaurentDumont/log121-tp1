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
 * Objet Cercle renfermant les attributs pour sa création
 * 
 * @author stongema
 *
 */
public class Cercle extends FormePrincipal{

	private int x;
	private int y;
	private int rayon;
	
	
		public Cercle(String [] tabForme){
			x = Integer.parseInt(tabForme[2]);
			y = Integer.parseInt(tabForme[3]);
			rayon = Integer.parseInt(tabForme[4]);
			
		}

		/**
		 * Permet de dessiner la forme
		 * 
		 * @param g Un graphique
		 */
		public void dessinerForme(Graphics g){ //Dessine la forme dans le Frame
		
			g.setColor(Color.YELLOW);
			g.fillOval(x,y,rayon,rayon);
			
			
		}
	
}
