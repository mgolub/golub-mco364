package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public class BucketTool extends Tool {


	public BucketTool(PaintProperties properties) {
		super(properties);
	}

	public void mouseReleased(Graphics g, int x, int y) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(Graphics g, int x, int y) {
		// TODO Auto-generated method stub

	}

	public void drawPreview(Graphics g) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(Graphics g, int x, int y) {
		// TODO Auto-generated method stub

		fill(x, y, properties.getImage().getRGB(x, y), properties.getColor().getRGB());

	}

	private void fill(int x, int y, int oldCol, int newCol) {
		Queue<Point> queue = new LinkedList<Point>();

		queue.add(new Point(x, y));

		Point p;
		while (!queue.isEmpty()) {

			p = queue.remove();

			int x2 = (int) p.getX();
			int y2 = (int) p.getY();

			if (x2 > 0 && y2 > 0 && x2 < properties.getImage().getWidth()
					&& y2 < properties.getImage().getHeight() && properties.getImage().getRGB(x2, y2) == oldCol) {

				properties.getImage().setRGB(x2, y2, newCol);

				queue.add(new Point(x2 - 1, y2));
				queue.add(new Point(x2 + 1, y2));
				queue.add(new Point(x2, y2 - 1));
				queue.add(new Point(x2, y2 + 1));
			}
		}

	}

}