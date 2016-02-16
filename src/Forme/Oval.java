/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Chargé de labo : Simon Robert
Nom du fichier : Oval.java
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

public class Oval extends Forme {

	private int x;
	private int y;
	private int rayonH;
	private int rayonV;
	private Encadrer Encadre;
	
	/**
	 * Constructeur de la forme ovale
	 * @param reponseRecu Classe contenant les caracteristique de la forme
	 * @param tabCoord Tableau contenant les positions de la forme
	 */
	public Oval(Decoder reponseRecu, String [] tabCoord){
		numSeq = reponseRecu.getID();
		nomForme = reponseRecu.getTypeForme();
		x = Integer.parseInt(tabCoord[0]);
		y = Integer.parseInt(tabCoord[1]);
		rayonH = Integer.parseInt(tabCoord[2]);
		rayonV = Integer.parseInt(tabCoord[3]);
		String[] temp = new String[4];
		temp[0] = String.valueOf((Integer.parseInt(tabCoord[0])) - this.rayonH/2);
		temp[1] = String.valueOf((Integer.parseInt(tabCoord[1])) - this.rayonV/2);
		temp[2] = String.valueOf((Integer.parseInt(tabCoord[0])) + this.rayonH/2);
		temp[3] = String.valueOf((Integer.parseInt(tabCoord[1])) + this.rayonV/2);
		this.Encadre = new Encadrer(temp);
	}
		
	/**
	* Permet de dessiner la forme dans la fenêtre principale.
	* @param g
	*/
	public void draw(Graphics g){
		g.setColor(Color.CYAN);
		g.fillOval(x,y,rayonH,rayonV);	
	}
	/**
	 * Calule l'aire de l'ovale et le retourne en double
	 */
	public double calculeAire()
	{
		return Math.PI * rayonH/2 * rayonV/2;
	}

	/**
	 * Mutateur afin de deplacer l'ovale selon des coordonnees x y
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		this.Encadre.setPosition(x , y);
	}
	/**
	 * Accesseur de la diagonale de l'encadrer
	 * @return La valeur en double de la longue diagonale
	 */
	public double getDiagonale()
	{
		return this.Encadre.getDiagonale();
	}
	/**
	 * Accesseur de l'encadrer
	 */
	public Encadrer getEncadree() {
		return this.Encadre;
	}
	/**
	 * Accesseur du numero de sequence de l'ovale
	 */
	public int getNumSeq() {
		return numSeq;
	}
	/**
	 * Retourne un int unique contenant le type de forme et le numero de sequence 4 pour ovale
	 * @return Numero unique d'identification des formes
	 */
	public int getTypeForme(){
		return 400000 + numSeq;
	}
	
}
