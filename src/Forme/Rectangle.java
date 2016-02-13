package Forme;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Forme {

	private int x1, y1, x2, y2;
	private final String TYPE = "RECTANGLE";

	public Rectangle(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
		setType(TYPE);
	}

	public void draw(Graphics g) {

		g.setColor(Color.RED);
		g.fillRect(x1, y1, findwidth(x1, x2), findheight(y1, y2));

	}

	public int findheight(int y1, int y2) {

		return (y2 - y1);
	}

	public int findwidth(int x1, int x2) {

		return (x2 - x1);
	}

	public double calculAire() {
		return ((x2 - x1) * (y2 - y1));
	}

	public double getDiagonale() {
		return Math.sqrt(((Math.pow((x2 - x1), 2)) + Math.pow((y2 - y1), 2)));
	}
}
