package Forme;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Oval extends Forme {

	private int width, length, centreX, centreY;
	private Graphics g;

	public Oval(int centreX, int centreY, int width, int length) {
		this.centreX = centreX;
		this.centreY = centreY;
		this.width = width;
		this.length = length;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(centreX, centreY, width, length);
		
	}

}
