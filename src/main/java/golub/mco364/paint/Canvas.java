package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

	private BufferedImage buffer;
	private Tool tool;
	private Color color;
	private Stack<BufferedImage> undo;
	private Stack<BufferedImage> redo;
	private PaintProperties properties;
	
	public Canvas(final BufferedImage buffer) {
		

		this.buffer = buffer;

		color = Color.BLACK; //default
		undo = new Stack<BufferedImage>();
		redo = new Stack<BufferedImage>();
		properties = new PaintProperties(800,800, buffer);
		this.tool = new PencilTool(properties);
		
		

		this.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {}

			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {
				undo.push(copyBuffer(buffer));

				tool.mousePressed(buffer.getGraphics(), e.getX(),
						e.getY());
				repaint();

			}

			public void mouseReleased(MouseEvent e) {
				redo.push(copyBuffer(buffer));
				tool.mouseReleased(buffer.getGraphics(), e.getX(),
						e.getY());
				repaint();

			}

		});

		this.addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseDragged(MouseEvent e) {
			    

				tool.mouseDragged(buffer.getGraphics(), e.getX(),
						e.getY());
				repaint();
				
			}

			public void mouseMoved(MouseEvent e) {
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(buffer, 0, 0, null);
		tool.drawPreview(g);


	}
	
	public void setTool(Tool tool){
		
		this.tool = tool;
	}
	
	public void setColor(Color c){
		this.color = c;
	}
	
	public BufferedImage copyBuffer(BufferedImage buffer){
		
		BufferedImage newBuffer = new BufferedImage(buffer.getWidth(), buffer.getHeight(), buffer.getType());
		
		Graphics2D g2 = newBuffer.createGraphics();
		g2.drawImage(buffer,0,0,null);
		
		return newBuffer;
	}
	
	public void undo(){
		if(!undo.isEmpty()){
			BufferedImage copy = copyBuffer(buffer);
			redo.push(copy);
			buffer = undo.pop();
			repaint();
		}
	}
	
	public void redo(){
		if(!redo.isEmpty()){
			BufferedImage copy = copyBuffer(buffer);
			undo.push(copy);
			buffer = redo.pop();
			repaint();
			
		}
	}
	
	public PaintProperties getProperties(){
		return this.properties;
	}
}