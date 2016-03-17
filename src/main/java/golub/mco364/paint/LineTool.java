package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class LineTool extends Tool {

	private int x1, y1;
	private int x2, y2;
	
	
	public LineTool(PaintProperties properties) {
		super(properties);
	}

	public void mousePressed(Graphics g, int x, int y) {
		x1 = x;
		y1 = y;
		x2 = x;
		y2 = y;
	}

	public void mouseReleased(Graphics g, int x, int y) {
		g.setColor(properties.getColor());
		g.drawLine(x1, y1, x, y);
	}

	public void mouseDragged(Graphics g, int x, int y) {
		x2 = x;
		y2 = y;
	}

	public void drawPreview(Graphics g) {
		g.setColor(properties.getColor());
		g.drawLine(x1, y1, x2, y2);

	}

	

}