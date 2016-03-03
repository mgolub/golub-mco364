package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Stack;

import javax.swing.JPanel;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private Stack<BufferedImage> undo;
	private Stack<BufferedImage> redo;
	private BufferedImage buffer;
	private Tool tool;
	private Color color;

	public Canvas() {

		this.tool = new PencilTool();
		this.buffer = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
		this.color = Color.BLACK;
		
		undo = new Stack<BufferedImage>();
		redo = new Stack<BufferedImage>();
		undo.push(copyBuffer(buffer));
		
		this.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent event) {

			}

			public void mouseEntered(MouseEvent event) {

			}

			public void mouseExited(MouseEvent event) {

			}

			public void mousePressed(MouseEvent event) {
				undo.push(copyBuffer(buffer));
				tool.mousePressed(buffer.getGraphics(), event.getX(), event.getY(), buffer, color);
				repaint();

			}

			public void mouseReleased(MouseEvent event) {
				tool.mouseReleased(buffer.getGraphics(), event.getX(), event.getY(), color);
				repaint();

			}

		});

		this.addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent event) {

				tool.mouseDragged(buffer.getGraphics(), event.getX(), event.getY(), color);
				repaint();
			}

			public void mouseMoved(MouseEvent event) {

			}

		});
	}


	private BufferedImage copyBuffer(BufferedImage image) {
		BufferedImage b = new BufferedImage(image.getWidth(),
				image.getHeight(), image.getType());
		Graphics g = b.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return b;
	}


	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public BufferedImage getImage() {
		return buffer;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(buffer, 0, 0, null);
		tool.drawPreview(g, color);

	}
	
	public void undo() {
		if (!undo.isEmpty()) {
			BufferedImage buffer = undo.pop();
			this.buffer = buffer;
			redo.add(this.buffer);

		}
		repaint();
	}

	public void redo() {
		if (!redo.isEmpty()) {
			buffer = redo.pop();
		}
		repaint();
	}

}
