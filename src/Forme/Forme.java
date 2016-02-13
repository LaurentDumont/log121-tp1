package Forme;

import java.awt.Graphics;

public abstract class Forme {
	private String type;
	private int coordX;
	private int coordY;
	private int info1;
	private int info2;

	public Forme(int coordX, int coordY, int info1, int info2) {
		setCoordX(coordX);
		setCoordY(coordY);
		setInfo1(info1);
		setInfo2(info2);
	}

	/**
	 * Retourne la coordonnée principale en x
	 */
	public int getCoordX() {
		return coordX;
	}

	/**
	 * Spécifie la coordonnée principale en x
	 */
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	/**
	 * Retourne la coordonnée principale en y
	 */
	public int getCoordY() {
		return coordY;
	}

	/**
	 * Spécifie la coordonnée principale en y
	 */
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	/**
	 * Retourne la première information additionnel de la forme
	 */
	public int getInfo1() {
		return info1;
	}

	/**
	 * Spécifie la première information additionnel de la forme
	 */
	public void setInfo1(int info1) {
		this.info1 = info1;
	}

	/**
	 * Retourne la deuxième information additionnel de la forme
	 */
	public int getInfo2() {
		return info2;
	}

	/**
	 * Spécifie la deuxième information additionnel de la forme
	 */
	public void setInfo2(int info2) {
		this.info2 = info2;
	}

	public String getType() {
		return type;
	}

	/**
	 * Spécifie le type de la forme
	 */
	public void setType(String type) {
		this.type = type;
	}

	public abstract double calculAire();
	public abstract void draw(Graphics g);
	public abstract double getDiagonale();
}
