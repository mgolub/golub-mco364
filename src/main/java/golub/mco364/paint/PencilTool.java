package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PencilTool extends Tool {

	private int x;
	private int y;

	public PencilTool(PaintProperties properties) {
		super(properties);
	}

	public void mousePressed(Graphics g, int x, int y) {
		this.x = x;
		this.y = y;
		g.setColor(properties.getColor());
		g.fillOval(x, y, 1, 1);

	}

	public void mouseReleased(Graphics g, int x, int y) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(Graphics g, int x, int y) {
		g.setColor(properties.getColor());
		g.drawLine(this.x, this.y, x, y);

		this.x = x;
		this.y = y;
	}

	public void drawPreview(Graphics g) {
		// TODO Auto-generated method stub

	}

}