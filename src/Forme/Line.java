package Forme;
import java.awt.Color;
import java.awt.Graphics;

public class Line extends Forme {

	private int x1, y1, x2, y2;
	private final String TYPE = "LIGNE";

	public Line(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
		setType(TYPE);
	}

	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(x1, y1, x2, y2);
		
	}
	

	public double calculAire() {
		double distance = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
		return 1 * distance;
	}

	public double getDiagonale() {
		
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}

}
