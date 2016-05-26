package golub.mco364.paint;

import java.awt.Graphics;

public class LineTool extends Tool {

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public LineTool(PaintProperties properties) {
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
		g.drawLine(x1, y1, x, y);
	}

	@Override
	public void mouseDragged(Graphics g, int x, int y) {
		this.x2 = x;
		this.y2 = y;
	}

	@Override
	public void drawPreview(Graphics g) {
		g.setColor(properties.getColor());
		g.drawLine(x1, y1, x2, y2);
	}
}
