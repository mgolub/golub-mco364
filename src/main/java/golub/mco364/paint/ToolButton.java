package golub.mco364.paint;

import java.awt.Color;
import java.awt.Image;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@Singleton
public class ToolButton extends JButton {

	private static final long serialVersionUID = 1L;
	private Tool tool;
	
	@Inject
	public ToolButton(Tool tool, String iconName) {
		this.tool = tool;
		this.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(iconName)).getImage().getScaledInstance(70, 70,
				Image.SCALE_SMOOTH)));

		this.setBackground(Color.white);
	}

	public Tool getTool() {
		return tool;
	}

	public void setImageI(String fileName) {

		ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
		Image img = icon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		icon.setImage(img);
		setIcon(icon);
	}
}
