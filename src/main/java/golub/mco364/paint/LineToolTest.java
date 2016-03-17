package golub.mco364.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class LineToolTest {
	
	@Test
	public void testMouseReleased(){
		
		PaintProperties properties = Mockito.mock(PaintProperties.class);
		LineTool tool = new LineTool(properties);
		
		Graphics g = Mockito.mock(Graphics.class);
		
		tool.mousePressed(g, 5, 5);
		tool.mouseReleased(g, 10, 10);
		
		Mockito.verify(g).drawLine(5, 5, 10, 10);
	}
	
	@Test
	public void testDrawPreview(){
		
	}

}
