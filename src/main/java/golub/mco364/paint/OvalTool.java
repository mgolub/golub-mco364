package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class OvalTool extends Tool {

	private int x1, y1;
	private int x2, y2;
	private int width, height;


	public OvalTool(PaintProperties properties) {
		super(properties);
	}

	public void mousePressed(Graphics g, int x, int y) {
		x1 = x;
		y1 = y;
		x2 = x;
		y2 = y;
		width = 0;
		height = 0;
	}

	public void mouseReleased(Graphics g, int x, int y) {
		x2 = x;
		y2 = y;
		width = Math.abs(x2 - x1);
		height = Math.abs(y2 - y1);
		g.setColor(properties.getColor());
		g.drawOval(x1, y1, width, height);

	}

	public void mouseDragged(Graphics g, int x, int y) {
		x2 = x;
		y2 = y;
		width = Math.abs(x2 - x1);
		height = Math.abs(y2 - y1);
	}

	public void drawPreview(Graphics g) {
		g.setColor(properties.getColor());
		g.drawOval(x1, y1, width, height);
	}

}