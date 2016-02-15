package Forme;
import java.awt.Color;
import java.awt.Graphics;

import Forme.Encadrer;
import main_package.Decoder;

public class Line extends Forme {

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int oldy1;
	private int oldy2;
	private Encadrer Encadre;
	
	/**
	 * Constructeur de la ligne
	 * @param reponseRecu Classe contenant les caracteristiques de la ligne
	 * @param tabCoord Tableau contenant les positions de la ligne
	 */
	public Line(Decoder reponseRecu, String [] tabCoord){
		numSeq = reponseRecu.getID();
		nomForme = reponseRecu.getTypeForme();
		
			this.x2 = Integer.parseInt(tabCoord[0]);
			this.x1 = Integer.parseInt(tabCoord[2]);
			this.y2 = Integer.parseInt(tabCoord[1]);
			this.y1 = Integer.parseInt(tabCoord[3]);
		
			this.oldy1 = this.y1;
			this.oldy2 = this.y2;
		
		Encadre = new Encadrer(tabCoord);
	}
		
	/**
	* Permet de dessiner la forme dans la fenêtre principale.
	* @param g
	*/
	public void draw(Graphics g){		
		g.setColor(Color.GREEN);
		g.drawLine(x1,y1,x2,y2);			
	}
	/**
	 * Calcule l'aire de la ligne
	 */
	public double calculeAire()
	{
		
		double distance = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
		return 1 * distance;
	}
	
	/**
	 * Mutateur pour deplacer la ligne selon des points x, y
	 */
	public void setPosition(int x, int y) {
		
		this.Encadre.setPosition(x, y);
		
		//Verifie l'orientation de la ligne avant de changer les positions
		if(this.oldy1 < this.oldy2)
		{
			
			this.x1 = this.Encadre.getPosition("x1");
			this.y1 = this.Encadre.getPosition("y1");
			this.x2 = this.Encadre.getPosition("x2");
			this.y2 = this.Encadre.getPosition("y2");
		}
		else
		{
			this.x1 = this.Encadre.getPosition("x1");
			this.y1 = this.Encadre.getPosition("y2");
			this.x2 = this.Encadre.getPosition("x2");
			this.y2 = this.Encadre.getPosition("y1");
		}
	}
	
	/**
	 * Methode pour recevoir la longue diagonale de la ligne
	 * @return La valeur de la diagonale
	 */
	public double getDiagonale()
	{
		return this.Encadre.getDiagonale();
	}
	
	/**
	 * Accesseur de l'encadrer pour les lignes
	 */
	public Encadrer getEncadree() {
		return this.Encadre;
	}
	/**
	 * Accesseur du numero de sequence
	 */
	public int getNumSeq() {
		return numSeq;
	}
	/**
	 * Retourne un int unique contenant le type de forme et le numero de sequence 5 pour ligne
	 * @return Numero unique d'identification des formes
	 */
	public int getTypeForme(){
		return 500000 + numSeq;
	}
}
