package vec;

public class Vector2D {

	private double x;
	private double y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point2D applyTo(Point2D p) {
		return new Point2D(x + p.getX(), y + p.getY());
	}
	
	public Vector2D getOpposite() {
		return scalarMulti(-1);
	}
	
	public double getLength() {
		return Math.sqrt(x*x + y*y);
	}
	
	public Vector2D scalarMulti(double s) {
		return new Vector2D(x*s, y*s);
	}
	
	public Vector2D toUnitVector() {
		return scalarMulti(1 / getLength());
	}
	
	public String print() {
		return "<html>(  " + x + "   )<br>" + "(  " + y + "   )</html>";
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
