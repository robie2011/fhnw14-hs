package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;

public class Oval extends AbstractFigure {

	public Oval(int x, int y, int w, int h) {
		super(new Ellipse2D.Double(x,y,w,h));
	}

	@Override
	public void draw(Graphics g) {
		int x = (int) rshape.getX();
		int y = (int) rshape.getY();
		int w = (int) rshape.getWidth();
		int h = (int) rshape.getHeight();
		
		g.setColor(Color.WHITE);
		g.fillOval(x,y,w,h);
		g.setColor(Color.BLACK);
		g.drawOval(x,y,w,h);
	}

}
