package vec;

public class Point2D {

    private double x;
    private double y;
    
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean isSame(Point2D other) {
        return x == other.x && y == other.y;
    }
    
    public String print() {
        return "(" + x + ", " + y + ")";
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
}
