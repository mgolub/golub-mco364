package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage buffer;
	private Point start;
	private Point end;
	
	
	
	
	public Canvas(){
		
		this.buffer = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
		start = new Point();
		end = new Point();
		
		
		this.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent event) {
				
			}

			public void mouseEntered(MouseEvent event) {
				
			}

			public void mouseExited(MouseEvent event) {
				
			}

			public void mousePressed(MouseEvent event) {
				start = event.getPoint();
				
			}

			public void mouseReleased(MouseEvent event) {
				
			}
			
		});
		
		this.addMouseMotionListener(new MouseMotionListener(){

			public void mouseDragged(MouseEvent event) {
				
				Graphics g = buffer.getGraphics();
				g.setColor(Color.RED);
				
				end = event.getPoint();
				int startX = (int) start.getX();
				int startY = (int) start.getY();
				int endX = (int) end.getX();
				int endY = (int) end.getY();
				g.drawLine(startX, startY, endX, endY);
				repaint();
				start = end;
			}

			public void mouseMoved(MouseEvent event) {
				
			}
			
		});
	}
	
	@Override 
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		g.drawImage(buffer, 0, 0, null);
		
		
		
	}

}
