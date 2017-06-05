package vec;

public class Line2D {

	private Point2D start;
	private Point2D end;
	
	public Line2D(Point2D start, Point2D end) {
		this.start = start;
		this.end = end;
	}
	
	public boolean isSame(Line2D other) {
		return start.isSame(other.getStart()) && end.isSame(other.getEnd());
	}
	
	public String print() {
		return "(" + start.getX() + ", " + start.getY() + ") -> "
				+ "(" + end.getX() + ", " + end.getY() + ")";
	}
	
	public Point2D getStart() {
		return start;
	}
	
	public Point2D getEnd() {
		return end;
	}
}
