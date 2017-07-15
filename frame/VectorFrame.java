package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JButton applyButton = new JButton("apply");
	private JComboBox<String> comboBox = new JComboBox<>(VectorCanvas.COLORS);
	private JLabel currentStartP = new JLabel();
	private JLabel currentEndP = new JLabel();
	private JLabel currentVector = new JLabel();
	private JLabel currentVectorLength = new JLabel();
	public static final int WIDTH = 750;
	public static final int HEIGHT = 750;
	
	public VectorFrame() {
		init();
	}
	
	public void init() {
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
		applyButton.addActionListener(this);
		comboBox.addActionListener(this);
		comboBox.setBackground(Color.WHITE);
		
		labelPanel.add(xCoor);
		labelPanel.add(xText);
		labelPanel.add(yCoor);
		labelPanel.add(yText);
		labelPanel.add(vxCoor);
		labelPanel.add(vxText);
		labelPanel.add(vyCoor);
		labelPanel.add(vyText);
		labelPanel.add(applyButton);
		labelPanel.add(comboBox);
		
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
		main.add(new VectorCanvas(null, null));
		frame.getContentPane().add(main);
	}
	
	public static void main(String[] args) {
		new VectorFrame();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals(applyButton.getText())) {
			handleVectorCanvasChanged();
		} else {
			handleComboBoxChanged();
		}
	}

	private void handleVectorCanvasChanged() {
		Point2D p = handlePoint(xText.getText(), yText.getText());
		Vector2D v = handleVector(vxText.getText(), vyText.getText(), p);
		if (v != null && p != null) {
			// Remove VectorCanvas component if exists:
			if (main.getComponentCount() >= 3) {
				main.remove(2);
			}
			// Re-add new VectorCanvas component:
			main.add(new VectorCanvas(v, p));
			frame.getContentPane().add(main);
			
			Point2D endPoint = v.applyTo(p);
			xText.setText(Integer.toString((int) endPoint.getX()));
			yText.setText(Integer.toString((int) endPoint.getY()));
			vxText.setText("");
			vyText.setText("");
			
			currentStartP.setText(p.print());
			currentEndP.setText(endPoint.print());
			currentVector.setText(v.print());
			currentVectorLength.setText(String.valueOf(v.getLength()));
			
			frame.validate();
			frame.repaint();
		}
	}

	private void handleComboBoxChanged() {
		VectorCanvas.setCurrentCol(getCurrentColor((String) comboBox.getSelectedItem()));
	}

	private Color getCurrentColor(String col) {
		switch (col) {
		case "black":
			return Color.BLACK;
		case "blue":
			return Color.BLUE;
		case "cyan":
			return Color.CYAN;
		case "dark-gray":
			return Color.DARK_GRAY;
		case "gray":
			return Color.GRAY;
		case "green":
			return Color.GREEN;
		case "light-gray":
			return Color.LIGHT_GRAY;
		case "magenta":
			return Color.MAGENTA;
		case "orange":
			return Color.ORANGE;
		case "pink":
			return Color.PINK;
		case "red":
			return Color.RED;
		default:
			return Color.YELLOW;
		}
	}

	public Point2D handlePoint(String x, String y) {
		try {
			double xCoor = Double.parseDouble(x);
			double yCoor = Double.parseDouble(y);
			boolean inPositiveRange = xCoor >= 0 && yCoor >= 0;
			
			if (xCoor <= VectorCanvas.WIDTH &&
				yCoor <= VectorCanvas.HEIGHT && inPositiveRange) {
				return new Point2D(xCoor, yCoor);
			} else {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Invalid point!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public Vector2D handleVector(String x, String y, Point2D p) {
		try {
			double xComp = Double.parseDouble(x);
			double yComp = Double.parseDouble(y);
			
			if (p != null) {
				boolean isInBounds = xComp + p.getX() >= 0 && yComp + p.getY() >= 0;
				
				if (xComp + p.getX() <= VectorCanvas.WIDTH &&
		            yComp + p.getY() <= VectorCanvas.HEIGHT && isInBounds) {
					return new Vector2D(xComp, yComp);
				} else {
					throw new NumberFormatException();
				}
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Invalid vector!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}
