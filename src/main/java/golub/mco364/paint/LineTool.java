package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;

public class LineTool implements Tool{
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;
	
	public LineTool(Color color){
		this.color = color;
	}

	public void mousePressed(Graphics g, int x, int y) {
		
		this.x1 = x;
		this.y1 = y;
		this.x2 = x;
		this.y2 = y;
	}

	public void mouseReleased(Graphics g, int x, int y) {
		
		g.setColor(color);
		g.drawLine(this.x1, this.y1, x, y);
	}

	public void mouseDragged(Graphics g, int x, int y) {
		
		this.x2 = x;
		this.y2 = y;
	}

	public void drawPreview(Graphics g) {
		
		g.setColor(color);
		g.drawLine(this.x1, this.y1, this.x2, this.y2);
		
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
