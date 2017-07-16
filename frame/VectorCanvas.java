package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

import vec.Line2D;
import vec.Point2D;
import vec.Vector2D;

public class VectorCanvas extends JComponent {

	private static final long serialVersionUID = 1L;
	public static Color currentCol = Color.BLACK;
	public static final int WIDTH = 750;
	public static final int HEIGHT = 500;
	private Vector2D vector;
	private static ArrayList<Line2D> lines = new ArrayList<>();
	
	public VectorCanvas(Vector2D vector, Point2D start) {
		this.setSize(WIDTH, HEIGHT);
		if (vector != null && start != null) {
			this.vector = vector;
			Line2D line = new Line2D(start, vector.applyTo(start));
			if (!lines.contains(line)) {
				lines.add(line);
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, WIDTH - 1, HEIGHT - 1);
		
		g.setColor(Color.BLACK);
		for (int i = 50; i < WIDTH; i+=50) {
			g.drawLine(i, 0, i, 10);
			g.drawString(String.valueOf(i), i, 20);
		}
		for (int j = 50; j < HEIGHT; j+=50) {
			g.drawLine(0, j, 10, j);
			g.drawString(String.valueOf(j), 10, j);
		}
		
		for (Line2D line : lines) {
			g.setColor(line.getCurrentCol());
			Point2D start = line.getStart();
			Point2D end = line.getEnd();
			g.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
		}
	}
	
	public Vector2D getVector() {
		return vector;
	}
	
	public static void setCurrentCol(Color col) {
		currentCol = col;
	}
	
	public static ArrayList<Line2D> getLines() {
		return lines;
	}
}
