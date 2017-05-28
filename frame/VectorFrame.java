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
import javax.swing.JPanel;

import vec.Point2D;
import vec.Vector2D;

public class VectorFrame extends JComponent implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JFrame frame = new JFrame();
	private JPanel main = new JPanel();
	private JPanel labelPanel = new JPanel();
	private TextField xText = new TextField();
	private TextField yText = new TextField();
	private TextField vxText = new TextField();
	private TextField vyText = new TextField();
	private static final int WIDTH = 750;
	private static final int HEIGHT = 750;
	
	public VectorFrame() {
		initVectorFrame();
	}
	
	public void initVectorFrame() {
		frame.setTitle("Vectors");
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		main.setLayout(new BorderLayout());
		labelPanel.setLayout(new GridLayout(5, 2));
		JLabel xCoor = new JLabel("Enter x-coordinate:");
		JLabel yCoor = new JLabel("Enter y-coordinate:");
		JLabel vxCoor = new JLabel("Vector x-component:");
		JLabel vyCoor = new JLabel("Vector y-component:");
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
		main.add(labelPanel, BorderLayout.NORTH);
		
		frame.getContentPane().add(main);
	}
	
	public static void main(String[] args) {
		new VectorFrame();
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Vector2D v = handleVector(vxText.getText(), vyText.getText());
		Point2D p = handlePoint(xText.getText(), yText.getText());
		if (main.getComponentCount() >= 2) {
			main.remove(1);
		}
		main.add(new VectorCanvas(v, p));
		frame.getContentPane().add(main);
		frame.validate();
		frame.repaint();
	}
	
	public Point2D handlePoint(String x, String y) {
		double xCoor = Double.parseDouble(x);
		double yCoor = Double.parseDouble(y);
		return new Point2D(xCoor, yCoor);
	}
	
	public Vector2D handleVector(String x, String y) {
		double xComp = Double.parseDouble(x);
		double yComp = Double.parseDouble(y);
		return new Vector2D(xComp, yComp);
	}
}
