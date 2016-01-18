package Forme;
import java.awt.Color;
import java.awt.Graphics2D;

public class Oval extends Forme {

	private int width, length, centreX, centreY;
	private Graphics2D g;

	public Oval(int centreX, int centreY, int width, int length) {
		this.centreX = centreX;
		this.centreY = centreY;
		this.width = width;
		this.length = length;
	}

	public void draw(Graphics2D g) {

		g.drawOval(centreX, centreY, width, length);
		g.setColor(Color.BLUE);
	}

}
