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
    private static final double[][] COUNTER_ROT = new double[][] {
        { Math.cos(0.25*Math.PI), -Math.sin(0.25*Math.PI) },
        { Math.sin(0.25*Math.PI), Math.cos(0.25*Math.PI) }
    };
    private static final double[][] CLOCK_ROT = new double[][] {
        { Math.cos(0.25*Math.PI), Math.sin(0.25*Math.PI) },
        { -Math.sin(0.25*Math.PI), Math.cos(0.25*Math.PI) }
    };
    public static final int WIDTH = 750;
    public static final int HEIGHT = 500;
    public static Color currentCol = Color.BLACK;
    private Vector2D vector;
    private static ArrayList<Line2D> lines = new ArrayList<>();
    
    public VectorCanvas(Vector2D vector, Point2D start) {
        this.setSize(WIDTH, HEIGHT);
        if (vector != null && start != null) {
            this.vector = vector;
            Line2D line = new Line2D(start, vector.applyTo(start));
            if (!lines.contains(line)) {
                lines.add(line);
                Line2D[] arrowLines = getArrowLines(line.getEnd());
                for (int i = 0; i < arrowLines.length; i++) {
                    lines.add(arrowLines[i]);
                }
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
    
    public Line2D[] getArrowLines(Point2D p) {
        Vector2D v = vector.getOpposite();
        
        Vector2D xVec = matrixMulti(v, COUNTER_ROT).resize(20);
        Vector2D yVec = matrixMulti(v, CLOCK_ROT).resize(20);
        
        Line2D arm1 = new Line2D(p, xVec.applyTo(p));
        Line2D arm2 = new Line2D(p, yVec.applyTo(p));
        
        return new Line2D[]{ arm1, arm2 };
    }
    
    public Vector2D matrixMulti(Vector2D vec, double[][] matrix) {
        double xComp = matrix[0][0] * vec.getX() + matrix[0][1] * vec.getY();
        double yComp = matrix[1][0] * vec.getX() + matrix[1][1] * vec.getY();
        
        return new Vector2D(xComp, yComp);
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
