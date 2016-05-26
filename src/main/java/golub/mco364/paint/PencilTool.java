package golub.mco364.paint;

import java.awt.Graphics;

public class PencilTool extends Tool {
	private int x;
	private int y;

	public PencilTool(PaintProperties properties) {
		super(properties);
	}

	@Override
	public void mousePressed(Graphics g, int x, int y) {
		g.setColor(properties.getColor());
		g.fillOval(this.x, this.y, 1, 1);
		this.x = x;
		this.y = y;
	}

	@Override
	public void mouseReleased(Graphics g, int x, int y) {
		g.setColor(properties.getColor());
		g.drawLine(this.x, this.y, x, y);
		this.x = x;
		this.y = y;
	}

	@Override
	public void mouseDragged(Graphics g, int x, int y) {
		g.setColor(properties.getColor());
		g.drawLine(this.x, this.y, x, y);
		this.x = x;
		this.y = y;

	}

	@Override
	public void drawPreview(Graphics g) {
	}
}
