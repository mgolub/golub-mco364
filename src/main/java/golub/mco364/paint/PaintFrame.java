package golub.mco364.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintFrame extends JFrame {

	private JButton clear;
	private JButton undo;
	private JButton redo;
	private JColorChooser chooser;
	private PaintProperties properties;
	private BufferedImage buffer;
	private Color color;

	private Tool tool;
	private Canvas canvas;

	public PaintFrame() {
		setTitle("PaintFrame");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel();
		JPanel colorPanel = new JPanel();

		this.buffer = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);

		properties = new PaintProperties(800, 800, buffer);
		
		color = properties.getColor();
		canvas = new Canvas(buffer);
		canvas.setBackground(Color.WHITE);

		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		buttonPanel.setBackground(Color.WHITE);
		
		colorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		colorPanel.setBackground(Color.WHITE);

		container.add(buttonPanel, BorderLayout.NORTH);

		ToolButton buttons[] = new ToolButton[] {
				new ToolButton(new PencilTool(properties), "/pencil.jpg"),
				new ToolButton(new RectangleTool(properties), "/square.png"),
				new ToolButton(new OvalTool(properties), "/oval.jpg"),
				new ToolButton(new LineTool(properties), "/line.png"),
				new ToolButton(new BucketTool(properties), "/bucket.png")};

		Dimension d = new Dimension(40,40);
		undo = new JButton(new ImageIcon(getClass().getResource("/undo.png")));
		undo.setPreferredSize(d);

		redo = new JButton(new ImageIcon(getClass().getResource("/redo.jpg")));
		redo.setPreferredSize(d);

		undo.setBackground(Color.WHITE);
		redo.setBackground(Color.WHITE);

		
		

		chooser = new JColorChooser(Color.BLACK);
		AbstractColorChooserPanel[] panels = chooser.getChooserPanels();

		chooser.setPreviewPanel(new JPanel());
		for (AbstractColorChooserPanel accp : panels) {
			if (!accp.getDisplayName().equals("Swatches")) {
				chooser.removeChooserPanel(accp);
			}
		}

		ColorSelectionModel model = chooser.getSelectionModel();
		ChangeListener changeListener = new ChangeListener() {

			public void stateChanged(ChangeEvent changeEvent) {
				// TODO Auto-generated method stub
				Color color = chooser.getColor();
				properties.setColor(color);

			}
		};

		model.addChangeListener(changeListener);
		
		buttonPanel.add(colorPanel);

		colorPanel.add(chooser);

		colorPanel.add(undo);
		colorPanel.add(redo);

		color = chooser.getSelectionModel().getSelectedColor();

		this.tool = new PencilTool(properties); // default

		clear = new JButton("Clear Canvas!");

		container.add(clear, BorderLayout.SOUTH);

		add(canvas, BorderLayout.CENTER);

		ActionListener listener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ToolButton button = (ToolButton) e.getSource();
				tool = button.getTool();
				canvas.setTool(tool);

			}

		};

		for (ToolButton button : buttons) {
			buttonPanel.add(button);
			button.addActionListener(listener);
		}

		undo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				canvas.undo();
			}

		});

		redo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				canvas.redo();
			}

		});
		
		clear.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				new PaintFrame().setVisible(true);
			}
			
			
			
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		new PaintFrame();
	}
}