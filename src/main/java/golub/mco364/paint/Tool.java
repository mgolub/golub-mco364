package golub.mco364.paint;

import java.awt.Graphics;

public abstract class Tool {

	protected PaintProperties properties;

	//passing in PaintProperties to classes that depend on it is called dependency injection
	//the tool class depends on paint properties
	public Tool(PaintProperties properties) {
		this.properties = properties;
	}

	abstract void mousePressed(Graphics g, int x, int y);

	abstract void mouseReleased(Graphics g, int x, int y);

	abstract void mouseDragged(Graphics g, int x, int y);

	abstract void drawPreview(Graphics g);

}
