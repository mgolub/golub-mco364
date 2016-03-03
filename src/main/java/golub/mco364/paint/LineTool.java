package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class LineTool implements Tool{
	
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
		g.drawLine(this.x1, this.y1, x, y);
	}

	public void mouseDragged(Graphics g, int x, int y, Color color) {
		
		this.x2 = x;
		this.y2 = y;
	}

	public void drawPreview(Graphics g, Color color) {
		
		g.setColor(color);
		g.drawLine(this.x1, this.y1, this.x2, this.y2);
		
	}


}
