package Forme;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line extends Forme {

	private int x1, y1, x2, y2;

	public Line(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(x1, y1, x2, y2);
		

	}

}
