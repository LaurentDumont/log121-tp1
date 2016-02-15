package Forme;

import java.awt.Color;
import java.awt.Graphics;

import Forme.Encadrer;
import main_package.Decoder;

public class Rectangle extends Forme {


	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Encadrer Encadre;
	
	/**
	 * Constructeur de la forme rectangle
	 * @param reponseServeurTraite Classe contenant les caracteristiques de la forme rectangle
	 * @param tabCoord Tableau contenant les positions du rectangle
	 */
	public Rectangle(Decoder reponseServeurTraite, String [] tabCoord){
		numSeq = reponseServeurTraite.getID();
		nomForme = reponseServeurTraite.getTypeForme();
		
			this.x1 = Integer.parseInt(tabCoord[0]);
			this.x2 = Integer.parseInt(tabCoord[2]);
			this.y1 = Integer.parseInt(tabCoord[1]);
			this.y2 = Integer.parseInt(tabCoord[3]);
		
		Encadre = new Encadrer(tabCoord);
	}
	
	/**
	* Permet de dessiner la forme dans la fenêtre principale.
	* @param g
	*/
	public void draw(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillRect(x1,y1,x2-x1,y2-y1);	
	}
	/**
	 * Accesseur de l'encadrer de la forme rectangle
	 */
	public Encadrer getEncadree(){
		return this.Encadre;
	}
	/**
	 * Methode pour calculer l'aire du rectangle
	 */
	public double calculeAire()
	{
		return ((x2-x1)*(y2-y1));
	}

	/**
	 * Mutateur pour deplacer le rectangle selon x y
	 */
	public void setPosition(int x, int y) {
		this.x2 = x + (this.x2 - this.x1);
		this.y2 = y + (this.y2 - this.y1);
		this.x1 = x;
		this.y1 = y;
		this.Encadre.setPosition(x, y);
	}
	/**
	 * Accesseur de la longueur de la diagonale de l'encadrer du rectangle
	 * @return La longueur de la diagonale
	 */
	public double getDiagonale()
	{
		return this.Encadre.getDiagonale();
	}
	/**
	 * Accesseur du numero de sequence
	 */
	public int getNumSeq() {
		return numSeq;
	}
	/**
	 * Retourne un int unique contenant le type de forme et le numero de sequence 2 pour rectangle
	 * @return Numero unique d'identification des formes
	 */
	public int getTypeForme(){
		return 200000 + numSeq;
	}
}
