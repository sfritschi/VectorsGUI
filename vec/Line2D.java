package vec;

import java.awt.Color;

import frame.VectorCanvas;

public class Line2D {

	private Point2D start;
	private Point2D end;
	private Color currentCol;
	
	public Line2D(Point2D start, Point2D end) {
		this.start = start;
		this.end = end;
		this.currentCol = VectorCanvas.currentCol;
	}
	
	public boolean isSame(Line2D other) {
		return start.isSame(other.getStart()) && end.isSame(other.getEnd());
	}
	
	@Override
	public boolean equals(Object other) {
		 if (other == null) return false;
		 if (other == this) return true;
		 if (!(other instanceof Line2D)) return false;
		 Line2D otherLine = (Line2D) other;
		 return isSame(otherLine);
	}

	@Override
	public int hashCode() {
	    return this.hashCode();
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
	
	public Color getCurrentCol() {
		return currentCol;
	}

	public void setCurrentCol(Color col) {
		this.currentCol = col;
	}
}
