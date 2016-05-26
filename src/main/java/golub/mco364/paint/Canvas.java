package golub.mco364.paint;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Stack;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JPanel;

@Singleton
public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;

	private BufferedImage image;
	private Tool tool;
	private Stack<BufferedImage> undo;
	private Stack<BufferedImage> redo;
	private PaintProperties properties;

	@Inject
	public Canvas(PaintProperties properties) {
		this.properties = properties;
		this.tool = new PencilTool(properties);
		this.image = properties.getImage();
		this.undo = new Stack<BufferedImage>();
		this.undo.push(deepCopyImage());
		redo = new Stack<BufferedImage>();

		this.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

		
			public void mouseExited(MouseEvent e) {
			}

	
			public void mousePressed(MouseEvent e) {
				undo.push(deepCopyImage());
				tool.mousePressed(image.getGraphics(), e.getX(), e.getY());
				repaint();
			}


			public void mouseReleased(MouseEvent e) {
				tool.mouseReleased(image.getGraphics(), e.getX(), e.getY());
				repaint();
			}
		});

		addMouseMotionListener(new MouseMotionListener() {

		
			public void mouseDragged(MouseEvent e) {
				tool.mouseDragged(image.getGraphics(), e.getX(), e.getY());
				repaint();
			}

	
			public void mouseMoved(MouseEvent e) {
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		tool.drawPreview(g);
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}

	public BufferedImage getImage() {
		return image;
	}

	public void undo() {
		if (!undo.isEmpty()) {
			if (!undo.isEmpty() && undo.size() != 1) {
				redo.push(deepCopyImage());
			}
			BufferedImage undoImage = undo.pop();
			this.setImage(undoImage);
		}
	}

	public void redo() {
		if (!redo.isEmpty()) {
			undo.push(deepCopyImage());
			BufferedImage redoImage = redo.pop();
			this.setImage(redoImage);
		}
	}

	public BufferedImage deepCopyImage() {
		ColorModel model = this.image.getColorModel();
		boolean isAlphaPremultiplied = model.isAlphaPremultiplied();
		WritableRaster raster = this.image.copyData(null);
		BufferedImage copyImage = new BufferedImage(model, raster, isAlphaPremultiplied, null);
		return copyImage;
	}
}
