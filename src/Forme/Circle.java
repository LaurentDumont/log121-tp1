/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Chargé de labo : Simon Robert
Nom du fichier : Circle.java
Date créé : 2016-01-12
Date dern. modif. 2016-02-15
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/

package Forme;
import java.awt.Color;
import java.awt.Graphics;

import Forme.Encadrer;
import main_package.Decoder;

public class Circle extends Forme {
	
	private int x;
	private int y;
	private int radius;
	private Encadrer Encadre;
	
	/**
	 * Constructeur de la forme cercle
	 * @param reponseRecu Classe contenant les informations generales du cercle
	 * @param tabCoord Tableau contenant les coordonnees du cercle
	 */
	public Circle(Decoder reponseRecu, String [] tabCoord){
		numSeq = reponseRecu.getID();
		nomForme = reponseRecu.getTypeForme();
		x = Integer.parseInt(tabCoord[0]);
		y = Integer.parseInt(tabCoord[1]);
		radius = Integer.parseInt(tabCoord[2]);
		String[] temp = new String[4];
		temp[0] = String.valueOf((Integer.parseInt(tabCoord[0])) - (this.radius/2));
		temp[1] = String.valueOf((Integer.parseInt(tabCoord[1])) - (this.radius/2));
		temp[2] = String.valueOf((Integer.parseInt(tabCoord[0])) + (this.radius/2));
		temp[3] = String.valueOf((Integer.parseInt(tabCoord[1])) + (this.radius/2));
		this.Encadre = new Encadrer(temp);
	}

	/**
	* Permet de dessiner la forme dans la fenÃªtre principale.
	* @param g 
	*/
	public void draw(Graphics g){ 		
		g.setColor(Color.BLUE);
		g.fillOval(x,y,radius,radius);
	}
	/**
	 * Methode pour calculer l'aire du cercle
	 */
	public double calculeAire()
	{
		return Math.PI * Math.pow(radius/2, 2);
	}
	/**
	 * Accesseur pour changer la position de la forme
	 * @param int x, int y
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		this.Encadre.setPosition(x, y);
	}

	/**
	 * Retourne l'encadrer de la forme
	 */
	public Encadrer getEncadree() {
		return this.Encadre;
	}
	/**
	 * Retourne le numero de sequence
	 * @return Numéro Séquence
	 */
	public int getNumSeq() {
		return numSeq;
	}
	/**
	 * Retourne la grande diagonale de l'encadrer du cercle
	 * @return Diagonale de la forme
	 */
	public double getDiagonale()
	{
		return this.Encadre.getDiagonale();
	}
	/**
	 * Retourne un int unique contenant le type de forme et le numero de sequence 3 pour cercle
	 * @return forme du cercle en int
	 */
	public int getTypeForme(){
		return 300000 + numSeq;
	}
}
