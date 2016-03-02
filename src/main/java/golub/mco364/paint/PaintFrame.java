package golub.mco364.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintFrame extends JFrame implements ActionListener{
	
	private Canvas canvas;
	private Color color;
	
	private JButton pencil;
	private JButton oval;
	private JButton rectangle;
	private JButton bucket;
	private JButton line;
	
	private JPanel buttons;
	private JPanel topPanel;
	private JPanel buttonTop;
	private JPanel buttonBottom;
	

	private static final long serialVersionUID = 1L;
	public PaintFrame(){
		
		setTitle("PaintFrame");
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		color = Color.BLACK;//default color
		
		pencil = new JButton("pencil");
		line = new JButton("line");
		rectangle = new JButton("rectangle");
		oval = new JButton("oval");
		bucket = new JButton ("fill-bucket");
		
		pencil.addActionListener(this);
		line.addActionListener(this);
		rectangle.addActionListener(this);
		oval.addActionListener(this);
		bucket.addActionListener(this);
		
		
		buttons = new JPanel(new BorderLayout());
		buttonTop = new JPanel(new BorderLayout());
		buttonBottom = new JPanel(new BorderLayout());
		buttonTop.add(pencil, BorderLayout.WEST);
		buttonTop.add(line, BorderLayout.CENTER);
		buttonTop.add(rectangle, BorderLayout.EAST);
		buttonBottom.add(oval, BorderLayout.WEST);
		buttonBottom.add(bucket, BorderLayout.EAST);
		
		buttons.add(buttonTop, BorderLayout.NORTH);
		buttons.add(buttonBottom, BorderLayout.SOUTH);
		

		JColorChooser cc = new JColorChooser();

		AbstractColorChooserPanel[] panels = cc.getChooserPanels();

		for (AbstractColorChooserPanel p : panels) {
			if (!p.getDisplayName().equals("Swatches")) {
				cc.removeChooserPanel(p);
			}
		}
		cc.setPreviewPanel(new JPanel());
		final ColorSelectionModel model = cc.getSelectionModel();
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent c) {
				setColor(model.getSelectedColor());
			}
		};
		model.addChangeListener(changeListener);
		
		canvas = new Canvas(Color.BLACK);
		topPanel = new JPanel(new BorderLayout());
		topPanel.add(cc, BorderLayout.EAST);
		topPanel.add(buttons, BorderLayout.CENTER);
		container.add(topPanel, BorderLayout.NORTH);
		container.add(canvas, BorderLayout.CENTER);
	
		
		setVisible(true);	
	}
	public void setColor(Color color) {
		this.color = color;
		if (canvas != null) {
			this.canvas.setColor(color);
		}
	}
	
	public static void main(String[]args){
		new PaintFrame();
		
	}
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == pencil) {
			canvas.setTool(new PencilTool(this.color));
		}
		if (event.getSource() == line) {
			canvas.setTool(new LineTool(this.color));
		}
		if (event.getSource() == rectangle) {
			canvas.setTool(new RectangleTool(this.color));
		}
		if (event.getSource() == oval) {
			canvas.setTool(new OvalTool(this.color));
		}
		if (event.getSource() == bucket) {
			canvas.setTool(new BucketTool(canvas.getBuffer(), this.color));
		}
		/*if (event.getSource() == undo) {
			canvas.undo();
		}
		if (e.getSource() == redo) {
			canvas.redo();
		}*/
		
	}

}
