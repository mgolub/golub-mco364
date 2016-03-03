package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PencilTool implements Tool{
	
	private int x;
	private int y;
	
	
	public void mousePressed(Graphics g, int x, int y, BufferedImage buffer, Color color) {
		g.setColor(color);
		g.fillOval(x,y,1,1);
		this.x = x;
		this.y = y;
		
	}

	public void mouseReleased(Graphics g, int x, int y, Color color) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(Graphics g, int x, int y, Color color) {
	
		g.setColor(color);
		g.drawLine(this.x, this.y, x, y);
		this.x = x;
		this.y = y;
		
	}

	public void drawPreview(Graphics g, Color color) {
		// TODO Auto-generated method stub
		
	}




	
	

}
