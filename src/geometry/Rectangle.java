// ID: 337914121
package geometry;
import java.util.List;
import java.util.ArrayList;

/**
 * A Rectangle Object.
 * Our Rectangles will all be aligned with the axes.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Line upperLine;
    private List<Point> pointsRect = new ArrayList();
    private List<Line> linesRect = new ArrayList();

    /**
     * Constructor Rectangle.
     * Create a new rectangle with location and width/height.
     * @param upperLeft Point that define location of the upperLeft point of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.upperLeft = upperLeft;
        this.width = width;
        initializePoints();
        initializeLine();
    }

    /**
     * Constructor Rectangle.
     * Create a new rectangle with location and width/height.
     * @param x coordinate of upperleft that define location of the upperLeft point of the rectangle.
     * @param y coordinate of upperleft that define location of the upperLeft point of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.height = height;
        this.width = width;
        initializePoints();
        initializeLine();
    }

    /**
     * Initialize points.
     * calculate coordinates of all points of the rectangle and fill the list pointsRect.
     */
    private void initializePoints() {
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerRight = new Point(upperRight.getX(), lowerLeft.getY());
        this.pointsRect.add(lowerLeft);
        this.pointsRect.add(upperRight);
        this.pointsRect.add(lowerRight);
        this.pointsRect.add(upperLeft);
    }

    /**
     * Initialize lines.
     * create all the lines of the rectangle and fill the list linesRect.
     */
    private void initializeLine() {
        this.upperLine = new Line(this.upperLeft, this.pointsRect.get(1));
        Line lowerLine =  new Line(this.pointsRect.get(0), this.pointsRect.get(2));
        Line leftLine =  new Line(this.pointsRect.get(0), this.pointsRect.get(3));
        Line rightLine =  new Line(this.pointsRect.get(2), this.pointsRect.get(1));
        this.linesRect.add(this.upperLine);
        this.linesRect.add(leftLine);
        this.linesRect.add(rightLine);
        this.linesRect.add(lowerLine);
    }

    /**
     * get width of the rectangle.
     * @return this.width the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * get lines list of the lines of the rectangle.
     * @return this.linesRect.
     */
    public List<Line> getLines() {
        return this.linesRect;
    }

    /**
     * get point list of the rectangle.
     * @return this.pointsRect.
     */
    public List<Point> getPoints() {
        return this.pointsRect;
    }

    /**
     * get the upperline of the rectangle.
     * @return this.upperLine upper line of the rectangle
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     * get height of the rectangle.
     * @return this.height the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * get upper left point of the rectangle.
     * @return this.upperLeft the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }


    /**
     * intersection Points
     * Check if a given line have an intersection with the lines of the rectangle.
     * @param line a line to check intersection of the rectangle with it.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList();
        for (Line lineOfRect : this.linesRect) {
            if (!lineOfRect.equals(line)) { //compare 2 lines if there not equal they can have intersection
                Point inter = lineOfRect.intersectionWith(line); //find intersection
               if (inter != null) {
                        intersectionPoints.add(inter);
                }
            }
        }
        return intersectionPoints;
    }


    /**
     * move to left rectangle.
     * @return this new rectangle with new coordinates.
     */
    public Rectangle moveLeft() {
        Point destination = new Point((getUpperLeft().getX() - 10), upperLeft.getY());
        if (destination.getX() < 30) {
            return this;
        }
        for (Point points : this.pointsRect) {
            double a = points.getX() - 10;
            points.setX(a);
        }
        initializeLine();
        return this;
    }

    /**
     * move to right rectangle.
     * @return  this new rectangle with new coordinates.
     */
    public Rectangle moveRight() {
        Point destination = new Point((getUpperLeft().getX() + 10), upperLeft.getY());
        if (destination.getX() + this.width > 770) {
            return this;
        }
        for (Point points : this.pointsRect) {
            double a = points.getX() + 10;
            points.setX(a);
        }
        initializeLine();
        return this;
    }
    }