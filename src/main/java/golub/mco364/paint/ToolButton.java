package golub.mco364.paint;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ToolButton extends JButton{
	
	private static final long serialVersionUID = 1L;
	private Tool tool;
	
	public ToolButton(Tool tool, String iconName){
		this.tool = tool;
		this.setIcon(new ImageIcon(getClass().getResource(iconName)));
	}
	
	public Tool getTool(){
		return tool;
	}

}
