package Forme;

import java.awt.Color;
import java.awt.Graphics;

import main_package.Decoder;

public class Square extends Forme {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Encadrer Encadre;

	/**
	 * Constructeur de la forme Carrer qui va prendre les informations du
	 * serveur et d'un tableau de coordonnee pour ces parametres
	 * 
	 * @param reponseRecu
	 *            Classe contenant les informations necessaire a la creation du
	 *            carree
	 * @param tabCoord
	 *            Tableau de int contenant les points du carre
	 */
	public Square(Decoder reponseRecu, String[] tabCoord) {
		numSeq = reponseRecu.getID();
		nomForme = reponseRecu.getTypeForme();

		this.x1 = Integer.parseInt(tabCoord[0]);
		this.x2 = Integer.parseInt(tabCoord[2]);
		this.y1 = Integer.parseInt(tabCoord[1]);
		this.y2 = Integer.parseInt(tabCoord[3]);
		// Cree l'encadrer de la forme
		Encadre = new Encadrer(tabCoord);
	}

	/**
	 * Permet de dessiner la forme dans la fenêtre principale.
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x1, y1, x2 - x1, y2 - y1);
	}

	/**
	 * Methode pour calculer l'aire selon la forme
	 */
	public double calculeAire() {
		return ((x2 - x1) * (y2 - y1));
	}

	/**
	 * Retourne l'encadrer de la forme
	 */
	public Encadrer getEncadree() {
		return this.Encadre;
	}

	/**
	 * Mutateur afin de modifier les positions de la forme carre
	 */
	public void setPosition(int x, int y) {
		this.x2 = (x + (this.x2 - this.x1));
		this.y2 = (y + (this.y2 - this.y1));
		this.x1 = x;
		this.y1 = y;
		this.Encadre.setPosition(x, y);
	}

	/**
	 * Obtient la grande diagonale de l'encadrer
	 * 
	 * @return Retourne la valeur en Double de la longueur de la diagonale
	 */
	public double getDiagonale() {
		return this.Encadre.getDiagonale();
	}

	/**
	 * Accesseur du numero de sequence
	 */
	public int getNumSeq() {
		return numSeq;
	}

	/**
	 * Retourne un int unique qui comprend le type de forme 1 pour carrer et le
	 * numero de sequence
	 * 
	 * @return
	 */
	public int getTypeForme() {
		return 100000 + numSeq;

	}


}
