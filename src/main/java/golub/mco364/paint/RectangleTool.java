package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RectangleTool implements Tool{
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public void mousePressed(Graphics g, int x, int y, BufferedImage buffer, Color color) {
		g.setColor(color);
		this.x1 = x;
		this.y1 = y;
		this.x2 = x;
		this.y2 = y;
	}

	public void mouseReleased(Graphics g, int x, int y, Color color) {
		
		g.setColor(color);
		if (x1 > x2 && y1 > y2) {
			g.drawRect(x2, y2, x1 - x2, y1 - y2);
		}
		if (x2 > x1 && y1 > y2) {
			g.drawRect(x1, y2, x2 - x1, y1 - y2);
		}
		if (x1 > x2 && y2 > y1) {
			g.drawRect(x2, y1, x1 - x2, y2 - y1);
		}
		if (x2 > x1 && y2 > y1) {
			g.drawRect(x1, y1, x2 - x1, y2 - y1);
		}
	}

	public void mouseDragged(Graphics g, int x, int y, Color color) {
		
		this.x2 = x;
		this.y2 = y;
	}

	public void drawPreview(Graphics g, Color color) {
		
		g.setColor(color);
		if (x1 > x2 && y1 > y2) {
			g.drawRect(x2, y2, x1 - x2, y1 - y2);
		}
		if (x2 > x1 && y1 > y2) {
			g.drawRect(x1, y2, x2 - x1, y1 - y2);
		}
		if (x1 > x2 && y2 > y1) {
			g.drawRect(x2, y1, x1 - x2, y2 - y1);
		}
		if (x2 > x1 && y2 > y1) {
			g.drawRect(x1, y1, x2 - x1, y2 - y1);
		}
		
	}

}
