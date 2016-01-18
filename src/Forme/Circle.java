package Forme;
import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Forme {
	
	private int radius, centreX, centreY;
	private Graphics2D g;

	public Circle( int centreX, int centreY, int radius ) {
		this.centreX = centreX;
		this.centreY = centreY;
		this.radius = radius;
	}

	public void draw(Graphics2D g) {
		
		g.drawOval(centreX, centreY, radius, radius);
		g.setColor(Color.CYAN);
	}
	
	

}
