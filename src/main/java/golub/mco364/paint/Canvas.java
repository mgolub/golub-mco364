package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage buffer;
	private Tool tool;
	private Color color;

	public Canvas(Color color) {

		this.tool = new PencilTool(color);
		this.buffer = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
		
		this.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent event) {

			}

			public void mouseEntered(MouseEvent event) {

			}

			public void mouseExited(MouseEvent event) {

			}

			public void mousePressed(MouseEvent event) {
				tool.mousePressed(buffer.getGraphics(), event.getX(), event.getY());
				repaint();

			}

			public void mouseReleased(MouseEvent event) {
				tool.mouseReleased(buffer.getGraphics(), event.getX(), event.getY());
				repaint();

			}

		});

		this.addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent event) {

				tool.mouseDragged(buffer.getGraphics(), event.getX(), event.getY());
				repaint();
			}

			public void mouseMoved(MouseEvent event) {

			}

		});
	}


	public void setBuffer(BufferedImage buffer) {
		this.buffer = buffer;
	}

	public BufferedImage getBuffer() {
		return buffer;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(buffer, 0, 0, null);
		tool.drawPreview(g);

	}

}
