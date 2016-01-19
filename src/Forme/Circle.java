package Forme;
import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Forme {
	
	private int radius, centreX, centreY;
	private Graphics g;

	public Circle( int centreX, int centreY, int radius ) {
		this.centreX = centreX;
		this.centreY = centreY;
		this.radius = radius;
	}

	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval(centreX, centreY, radius, radius);
		
	}
	
	

}
