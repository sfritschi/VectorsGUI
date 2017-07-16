package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utils.Colors;
import vec.Line2D;
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
	private JButton undoButton = new JButton("undo");
	private JButton clearButton = new JButton("clear");
	private JComboBox<String> comboBox = new JComboBox<>(getColorNames());
	private JLabel currentStartP = new JLabel();
	private JLabel currentEndP = new JLabel();
	private JLabel currentVector = new JLabel();
	private JLabel currentVectorLength = new JLabel();
	public static final int WIDTH = 750;
	public static final int HEIGHT = 750;
	
	public VectorFrame() {
		init();
	}
	
	private String[] getColorNames() {
		ArrayList<String> colList = new ArrayList<>();
		for (Colors col : Colors.values()) {
			colList.add(col.getText());
		}
		return colList.toArray(new String[0]);
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
		vectorPanel.setLayout(new GridLayout(4, 3));
		JLabel xCoor = new JLabel("Enter x-coordinate:");
		JLabel yCoor = new JLabel("Enter y-coordinate:");
		JLabel vxCoor = new JLabel("Vector x-component:");
		JLabel vyCoor = new JLabel("Vector y-component:");
		JLabel startP = new JLabel("Coordinates of start point:");
		JLabel endP = new JLabel("Coordinates of end point:");
		JLabel vector = new JLabel("Vector:");
		JLabel vectorLength = new JLabel("Vector length:");
		applyButton.addActionListener(this);
		undoButton.addActionListener(this);
		undoButton.setEnabled(true);
		clearButton.addActionListener(this);
		clearButton.setEnabled(true);
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
		vectorPanel.add(new JLabel());
		vectorPanel.add(endP);
		vectorPanel.add(currentEndP);
		vectorPanel.add(new JLabel());
		vectorPanel.add(vector);
		vectorPanel.add(currentVector);
		vectorPanel.add(undoButton);
		vectorPanel.add(vectorLength);
		vectorPanel.add(currentVectorLength);
		vectorPanel.add(clearButton);
		
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
			handleNewVector();
		} else if (ae.getActionCommand().equals(undoButton.getText())) {
			handleUndo();
		} else if (ae.getActionCommand().equals(clearButton.getText())) {
			handleClear();
	    } else {
			handleComboBoxChanged();
		}
	}

	// Action event handlers:
	private void handleNewVector() {
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
			currentVectorLength.setText(String.valueOf(v.getLength()) + " pixels");
			
			frame.validate();
			frame.repaint();
		}
	}

	private void handleUndo() {
		ArrayList<Line2D> lines = VectorCanvas.getLines();
		if (lines.size() > 0) {
			Line2D removedLine = lines.get(lines.size() - 1);
		    lines.remove(lines.size() - 1);
		    
		    Point2D savePoint;
		    if (lines.size() > 0) {
		        savePoint = lines.get(lines.size() - 1).getEnd();
		    } else {
		    	savePoint = removedLine.getStart();
		    }
		    xText.setText(Integer.toString((int) savePoint.getX()));
	        yText.setText(Integer.toString((int) savePoint.getY()));
		    
		    frame.validate();
		    frame.repaint();
		}
	}
	
	private void handleClear() {
		ArrayList<Line2D> lines = VectorCanvas.getLines();
		if (lines.size() > 0) {
		    int reply = JOptionPane.showConfirmDialog
				    (frame, "Are you sure?", "Clear Canvas", JOptionPane.YES_NO_OPTION);
		    if (reply == JOptionPane.YES_OPTION) {
	            VectorCanvas.getLines().clear();
	            xText.setText("");
	            yText.setText("");
	            frame.validate();
	            frame.repaint();
		    }
		}
	}
	
	private void handleComboBoxChanged() {
		String item = (String) comboBox.getSelectedItem();
		Color currentCol = Colors.BLACK.getCol();
		for (Colors col : Colors.values()) {
			if (col.getText().equals(item)) {
				currentCol = col.getCol();
				break;
			}
		}
		VectorCanvas.setCurrentCol(currentCol);
	}

	// User input handlers:
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
