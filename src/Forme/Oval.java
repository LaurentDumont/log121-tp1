package Forme;
import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Forme {

	private int width, length, centreX, centreY;


	public Oval(int centreX, int centreY, int width, int length) {
		this.centreX = centreX;
		this.centreY = centreY;
		this.width = width;
		this.length = length;
	}

	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillOval(centreX, centreY, width*2, length*2);
		
	}

}
