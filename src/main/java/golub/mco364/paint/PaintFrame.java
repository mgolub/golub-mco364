package golub.mco364.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class PaintFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton undo;
	private JButton redo;
	private JPanel buttons;
	private ToolButton[] tools;
	private PaintProperties properties;
	private final Canvas canvas;

	@Inject
	public PaintFrame(PaintProperties properties) {
		setTitle("Paint Frame");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		this.properties = properties;
		// creating a paintframe will create the whole application, using guice
		canvas = new Canvas(properties);
		setUndoRedo();
		buttons = new JPanel();

		tools = new ToolButton[] { new ToolButton(new PencilTool(properties), "/pencil.jpg"),
				new ToolButton(new LineTool(properties), "/line.jpg"),
				new ToolButton(new RectangleTool(properties), "/rectangle.jpg"),
				new ToolButton(new OvalTool(properties), "/oval.jpg"),
				new ToolButton(new BucketTool(properties), "/bucket.png") };

		tools[0].addActionListener(this);
		tools[1].addActionListener(this);
		tools[2].addActionListener(this);
		tools[3].addActionListener(this);
		tools[4].addActionListener(this);

		undo.addActionListener(this);
		redo.addActionListener(this);

		buttons.add(tools[0]);
		buttons.add(tools[1]);
		buttons.add(tools[2]);
		buttons.add(tools[3]);
		buttons.add(tools[4]);
		buttons.add(undo);
		buttons.add(redo);

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

		setColor(Color.BLACK);

		container.add(canvas, BorderLayout.CENTER);
		container.add(buttons, BorderLayout.NORTH);
		container.add(cc, BorderLayout.SOUTH);
	}

	public void setColor(Color color) {
		properties.setColor(color);
	}

	private void setUndoRedo() {
		undo = new JButton();
		redo = new JButton();
		undo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/undo.jpg")).getImage().getScaledInstance(70,
				70, Image.SCALE_SMOOTH)));
		undo.setBackground(Color.white);
		redo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/redo.jpg")).getImage().getScaledInstance(70,
				70, Image.SCALE_SMOOTH)));
		redo.setBackground(Color.white);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tools[0]) { // pencil
			canvas.setTool(new PencilTool(properties));
		}
		if (e.getSource() == tools[1]) { // line
			canvas.setTool(new LineTool(properties));
		}
		if (e.getSource() == tools[2]) { // rectangle
			canvas.setTool(new RectangleTool(properties));
		}
		if (e.getSource() == tools[3]) { // oval
			canvas.setTool(new OvalTool(properties));
		}
		if (e.getSource() == tools[4]) { // bucket
			canvas.setTool(new BucketTool(properties));
		}
		if (e.getSource() == undo) {
			canvas.undo();
		}
		if (e.getSource() == redo) {
			canvas.redo();
		}
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new PaintModule());
		PaintProperties properties = injector.getInstance(PaintProperties.class);

		PaintFrame frame = injector.getInstance(PaintFrame.class);
		frame.setVisible(true);
	}
}
