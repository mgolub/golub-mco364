package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public class BucketTool implements Tool{
	
	private BufferedImage image;

	public BucketTool(BufferedImage image){
		this.image = image;
	}
	


	public void mousePressed(Graphics g, int x, int y, BufferedImage buffer, Color color) {
		int spot = image.getRGB(x, y);
		if (spot != color.getRGB()){
			fillIn(x,y, image.getRGB(x,y), color);
		}
	}

	public void mouseReleased(Graphics g, int x, int y, Color color) {
		
	}

	public void mouseDragged(Graphics g, int x, int y, Color color) {
		
	}

	public void drawPreview(Graphics g, Color color) {
		
	}
	
	public void fillIn(int x, int y, int spot, Color color){
		Queue<Point> points = new LinkedList<Point>();
		points.add(new Point(x,y));
		Point point;
		while (!points.isEmpty()){
			point = points.remove();
			int x1 = (int) point.getX();
			int y1 = (int) point.getY();
			if (x1 > 0 && y1 > 0 && x1 < image.getWidth() && y1 < image.getWidth() && image.getRGB(x1,y1)==spot){
				image.setRGB(x1, y1, color.getRGB());
				addSides(x1, y1, spot, points);
			}
		}
	}
	public void addSides(int x, int y, int spot, Queue<Point>points){
		addSide(x+1, y, spot, points);
		addSide(x-1, y, spot, points);
		addSide(x, y+1, spot, points);
		addSide(x, y-1, spot, points);
	}
	public void addSide(int x, int y, int spot, Queue<Point>points){
		points.add(new Point(x,y));
	}

	

}
