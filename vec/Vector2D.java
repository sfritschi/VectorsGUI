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
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    public double getAngle(Vector2D other) {
        double scalarProduct = x * other.x + y * other.y;
        double lengthProduct = getLength() * other.getLength();
        try {
            return Math.acos(scalarProduct / lengthProduct);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public Vector2D scalarMulti(double s) {
        return new Vector2D(x*s, y*s);
    }
    
    public Vector2D resize(double size) {
        return scalarMulti(size / getLength());
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
