package vec;

import vec.Point2D;

public class Point3D extends Point2D {

	private double z;
	
	public Point3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}
	
	public boolean isSame(Point3D other) {
		return getX() == other.getX() && getY() == other.getY() &&
				z == other.z;
	}
	
	@Override
	public String print() {
		return "(" + getX() + ", " + getY() + ", " + z + ")";
	}
	
	@Override
	public double getX() {
		return super.getX();
	}
	
	@Override
	public double getY() {
		return super.getY();
	}
	
	public double getZ() {
		return z;
	}
}
