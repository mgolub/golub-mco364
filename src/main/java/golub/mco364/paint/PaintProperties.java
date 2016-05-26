package golub.mco364.paint;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.google.inject.Inject;

public class PaintProperties {
	private BufferedImage image;
	private Color color;
	private int weight;
	private boolean fill;
	private int width;
	private int height;

	public PaintProperties() {
		this.width = 800;
		this.height = 600;
		this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		this.weight = 1;
		this.fill = false;
	}

	public PaintProperties(int width, int height, BufferedImage image, Color color, int weight, boolean fill) {
		this.width = width;
		this.height = height;
		this.image = image;
		this.color = color;
		this.weight = weight;
		this.fill = fill;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
