/******************************************************
Cours:  LOG121
Session: H2016
Projet: laboratoire #2
Étudiant(e)s: Laurent Dumont, Bach Nguyen-Ngoc
Professeur : Dominic St-Jacques
Nom du fichier: Encadrer.java
Date créé: 2016-02-11
*******************************************************
Description de la classe
Classe qui va créer un objet de type Rectangle selon les 
parametres recus et qui va donc permettre de dessiner 
un rectangle dans la fenetre principale.
*******************************************************
*@author Bach Nguyen-Ngoc, Laurent Dumont
2015-06-01 Version initiale
*******************************************************/ 
package Forme;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;


public class Encadrer extends Forme{

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	/**
	 * Constructeur de la forme encadrer
	 * @param tabCoord Tableau des coordonnees de la forme super de l'encadrer
	 */
	public Encadrer(String [] tabCoord){
		if(Integer.parseInt(tabCoord[0]) < Integer.parseInt(tabCoord[2])){
			this.x1 = Integer.parseInt(tabCoord[0]);
			this.x2 = Integer.parseInt(tabCoord[2]);
		}
		else{
			this.x2 = Integer.parseInt(tabCoord[0]);
			this.x1 = Integer.parseInt(tabCoord[2]);
		}
		if(Integer.parseInt(tabCoord[1]) < Integer.parseInt(tabCoord[3])){
			this.y1 = Integer.parseInt(tabCoord[1]);
			this.y2 = Integer.parseInt(tabCoord[3]);
		}
		else{
			this.y2 = Integer.parseInt(tabCoord[1]);
			this.y1 = Integer.parseInt(tabCoord[3]);
		}
		
	}
	
	/**
	* Permet de dessiner la forme dans la fenÃªtre principale.
	* @param g
	*/
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		float dash1[] = {2.0f};
	    BasicStroke dashed =
	    new BasicStroke(1.0f,
	                        BasicStroke.CAP_BUTT,
	                        BasicStroke.JOIN_MITER,
	                        2.0f, dash1, 0.0f);
	    g2.setStroke(dashed);
		g2.setColor(Color.black);
		g2.drawRect(x1,y1,x2-x1,y2-y1);	
	}

	/**
	 * Calcule l'Aire de l'encadrer qui n'a aucune utiliter
	 */
	public double calculeAire() {
		return 0;
	}

	/**
	 * Mutateur pour deplacer l'encadrer
	 */
	public void setPosition(int x, int y) {
		this.x2 = x + (this.x2 - this.x1);
		this.y2 = y + (this.y2 - this.y1);
		this.x1 = x;
		this.y1 = y;
	}

	/**
	 * Methode recu par l'interface
	 */
	@Override
	public Encadrer getEncadree() {
		return null;
	}
	/**
	 * Methode recu par l'interface
	 */
	@Override
	public int getNumSeq() {
		return 0;
	}
	/**
	 * Calcule la diagonale de l'encadrer afin de pouvoir le donner a la forme
	 * @return La longue diagonale
	 */
	public double getDiagonale(){
		return Math.sqrt(((Math.pow((this.x2 - this.x1), 2)) + Math.pow((this.y2 - this.y1), 2)));
	}
	/**
	 * Methode qui retourne la position qui se trouve a un point en parametre
	 * @param coord Le point pour lequel nous voulons la position
	 * @return La position du point
	 */
	public int getPosition(String coord)
	{
		int coordToReturn;
		switch (coord) {
		case "x1":
			coordToReturn = this.x1;
			break;
		case "x2":
			coordToReturn = this.x2;
			break;
		case "y1":
			coordToReturn = this.y1;
			break;
		case "y2":
			coordToReturn =  this.y2;
			break;
		default:
			coordToReturn = 0;
			break;
		}
		
		return coordToReturn;
	}

	
	
}
