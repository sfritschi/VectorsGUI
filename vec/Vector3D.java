package vec;

import vec.Vector2D;

public class Vector3D extends Vector2D {

	private double z;
	
	public Vector3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}

	public Point3D applyTo(Point3D p) {
		return new Point3D(getX() + p.getX(), getY() + p.getY(), z + p.getZ());
	}
	
	@Override
	public Vector3D getOpposite() {
		return scalarMulti(-1);
	}
	
	public double getLength() {
		return Math.sqrt(getX()*getX() + getY()*getY() + z*z);
	}
	
	@Override
	public Vector3D scalarMulti(double s) {
		return new Vector3D(getX()*s, getY()*s, z*s);
	}
	
	public Vector3D toUnitVector() {
		return scalarMulti(1 / getLength());
	}
	
	@Override
	public String print() {
		return "(  " + getX() + "   )\n" + "(  " + getY() + "   )\n"
				+ "(  " + z + "   )";
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
