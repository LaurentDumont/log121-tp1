package Forme;
import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Forme {

	private int width, length, centreX, centreY;
	private final String TYPE = "OVALE";


	public Oval(int centreX, int centreY, int width, int length) {
		super(centreX, centreY, width, length);
		setType(TYPE);
	}

	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillOval(centreX, centreY, width, length);
		
	}

	public double calculAire() {
		return Math.PI * width/2 * length/2;
	}


	public double getDiagonale() {
		if (width < length){
			return length *2;
		}
		else 
			return width * 2;
	}

}
