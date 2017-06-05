package frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vec.Point2D;
import vec.Vector2D;

public class VectorFrame extends JComponent implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JFrame frame = new JFrame();
	private JPanel main = new JPanel();
	private JPanel labelPanel = new JPanel();
	private JPanel vectorPanel = new JPanel();
	private TextField xText = new TextField();
	private TextField yText = new TextField();
	private TextField vxText = new TextField();
	private TextField vyText = new TextField();
	private JLabel currentStartP = new JLabel();
	private JLabel currentEndP = new JLabel();
	private JLabel currentVector = new JLabel();
	private JLabel currentVectorLength = new JLabel();
	private static final int WIDTH = 750;
	private static final int HEIGHT = 750;
	
	public VectorFrame() {
		initVectorFrame();
	}
	
	public void initVectorFrame() {
		frame.setTitle("Vector Demo");
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		main.setLayout(new BorderLayout());
		labelPanel.setLayout(new GridLayout(5, 2));
		vectorPanel.setLayout(new GridLayout(4, 2));
		JLabel xCoor = new JLabel("Enter x-coordinate:");
		JLabel yCoor = new JLabel("Enter y-coordinate:");
		JLabel vxCoor = new JLabel("Vector x-component:");
		JLabel vyCoor = new JLabel("Vector y-component:");
		JLabel startP = new JLabel("Coordinates of start point:");
		JLabel endP = new JLabel("Coordinates of end point:");
		JLabel vector = new JLabel("Vector:");
		JLabel vectorLength = new JLabel("Vector length:");
		JButton button = new JButton("apply");
		button.addActionListener(this);
		
		labelPanel.add(xCoor);
		labelPanel.add(xText);
		labelPanel.add(yCoor);
		labelPanel.add(yText);
		labelPanel.add(vxCoor);
		labelPanel.add(vxText);
		labelPanel.add(vyCoor);
		labelPanel.add(vyText);
		labelPanel.add(button);
		
		vectorPanel.add(startP);
		vectorPanel.add(currentStartP);
		vectorPanel.add(endP);
		vectorPanel.add(currentEndP);
		vectorPanel.add(vector);
		vectorPanel.add(currentVector);
		vectorPanel.add(vectorLength);
		vectorPanel.add(currentVectorLength);
		
		main.add(labelPanel, BorderLayout.NORTH);
		main.add(vectorPanel, BorderLayout.SOUTH);
		frame.getContentPane().add(main);
	}
	
	public static void main(String[] args) {
		new VectorFrame();
	}
	
	@Override
	public int getWidth() {
		return WIDTH;
	}
	
	@Override
	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Vector2D v = handleVector(vxText.getText(), vyText.getText());
		Point2D p = handlePoint(xText.getText(), yText.getText());
		if (v != null && p != null) {
			if (main.getComponentCount() >= 3) {
				main.remove(2);
			}
			main.add(new VectorCanvas(v, p));
			frame.getContentPane().add(main);
			
			currentStartP.setText(p.print());
			currentEndP.setText(v.applyTo(p).print());
			currentVector.setText(v.print());
			currentVectorLength.setText(String.valueOf(v.getLength()));
			
			frame.validate();
			frame.repaint();
		}
	}
	
	public Point2D handlePoint(String x, String y) {
		try {
			double xCoor = Double.parseDouble(x);
			double yCoor = Double.parseDouble(y);
			return new Point2D(xCoor, yCoor);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Invalid point!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public Vector2D handleVector(String x, String y) {
		try {
			double xComp = Double.parseDouble(x);
			double yComp = Double.parseDouble(y);
			return new Vector2D(xComp, yComp);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Invalid vector!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}
