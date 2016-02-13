/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
�tudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Charg� de labo : Simon Robert
Nom du fichier : Circle.java
Date cr�� : 2016-01-12
Date dern. modif. 2016-01-19
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/

package Forme;
import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Forme {
	
	private int radius, centreX, centreY;
	private final String TYPE = "CERCLE";

	public Circle( int centreX, int centreY, int radius ) {
		super(centreX, centreY, radius, radius);
		setType(TYPE);
	}

	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval(centreX, centreY, radius, radius);
		
	}
	
	public double calculAire(){
		return Math.PI * Math.pow(radius/2, 2);
	}

	public double getDiagonale() {
		return radius * 2;
	}

}
