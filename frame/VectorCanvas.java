package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

import vec.Line2D;
import vec.Point2D;
import vec.Vector2D;

public class VectorCanvas extends JComponent {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 750;
	private static final int HEIGHT = 500;
	private Vector2D vector;
	private static ArrayList<Line2D> lines = new ArrayList<>();
	
	public VectorCanvas(Vector2D vector, Point2D start) {
		this.setSize(WIDTH, HEIGHT);
		this.vector = vector;
		lines.add(new Line2D(start, vector.applyTo(start)));
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, WIDTH - 1, HEIGHT - 1);
		
		g.setColor(randomColor());
		for (int i = 0; i < lines.size(); i++) {
			Point2D start = lines.get(i).getStart();
			Point2D end = lines.get(i).getEnd();
			g.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
		}
	}
	
	public Color randomColor() {
		Random rand = new Random(System.currentTimeMillis());
		switch (rand.nextInt(12)) {
		case 0:
			return Color.BLACK;
		case 1:
			return Color.BLUE;
		case 2:
			return Color.CYAN;
		case 3:
			return Color.DARK_GRAY;
		case 4:
			return Color.GRAY;
		case 5:
			return Color.GREEN;
		case 6:
			return Color.LIGHT_GRAY;
		case 7:
			return Color.MAGENTA;
		case 8:
			return Color.ORANGE;
		case 9:
			return Color.PINK;
		case 10:
			return Color.RED;
		default:
			return Color.YELLOW;
		}
	}
	
	public Vector2D getVector() {
		return vector;
	}
}
