package golub.mco364.paint;

import java.awt.Graphics;

public class RectangleTool extends Tool {
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public RectangleTool(PaintProperties properties) {
		super(properties);
	}

	@Override
	public void mousePressed(Graphics g, int x, int y) {
		this.x1 = x;
		this.y1 = y;
		this.x2 = x;
		this.y2 = y;
	}

	@Override
	public void mouseReleased(Graphics g, int x, int y) {
		g.setColor(properties.getColor());
		if (x1 > x2 && y1 > y2) {
			// Q2
			g.drawRect(x2, y2, x1 - x2, y1 - y2);
		}
		if (x2 > x1 && y1 > y2) {
			// Q1
			g.drawRect(x1, y2, x2 - x1, y1 - y2);
		}
		if (x1 > x2 && y2 > y1) {
			// Q3
			g.drawRect(x2, y1, x1 - x2, y2 - y1);
		}
		if (x2 > x1 && y2 > y1) {
			// Q4
			g.drawRect(x1, y1, x2 - x1, y2 - y1);
		}
	}

	@Override
	public void mouseDragged(Graphics g, int x, int y) {
		this.x2 = x;
		this.y2 = y;
	}

	@Override
	public void drawPreview(Graphics g) {
		g.setColor(properties.getColor());
		if (x1 > x2 && y1 > y2) {
			// Q2
			g.drawRect(x2, y2, x1 - x2, y1 - y2);
		}
		if (x2 > x1 && y1 > y2) {
			// Q1
			g.drawRect(x1, y2, x2 - x1, y1 - y2);
		}
		if (x1 > x2 && y2 > y1) {
			// Q3
			g.drawRect(x2, y1, x1 - x2, y2 - y1);
		}
		if (x2 > x1 && y2 > y1) {
			// Q4
			g.drawRect(x1, y1, x2 - x1, y2 - y1);
		}
	}
}
