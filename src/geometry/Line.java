// ID: 337914121
package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * A Line Object.
 * A line (actually a line-segment) connects two points (start and end).
 * Lines have lengths, and may intersect with other lines and more.
 */
public class Line {
    private Point start;
    private Point end;
    private double coef; // a in the equation y = ax + b
    private double parameter; // b in the equation of the line y = ax + b

    /**
     * constructor.
     * from 2 points start and end.
     * @param start point that from the line start.
     * @param end   point where the line ends.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        coefficient(); //calculate the value of coef for the line formed by start and end
        findParameter(); //calculate the value of parameter of the line
    }

    /**
     * Constructor.
     * from coordinates of 2 points
     * @param x1 coordinate of start point
     * @param y1 coordinate of start point
     * @param x2 coordinate of end point
     * @param y2 coordinate of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        coefficient();
        findParameter();
    }

    /**
     * Calculate the coefficient of the line segment.
     */
    private void coefficient() {
        if (this.end.getX() != this.start.getX()) {
            this.coef = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        } else {
            this.coef = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * Calculate parameter of the line.
     */
    private void findParameter() {
        if (this.coef == Double.POSITIVE_INFINITY) {
            this.parameter = Double.POSITIVE_INFINITY;
        } else {
            double a = this.start.getY();
            double b = this.coef * this.start.getX();
            this.parameter = a - b;
        }
    }

    /**
     * get coeficient of the line.
     * @return this.coef coef of the line.
     */
    public double getCoef() {
        return this.coef;
    }

    /**
     * Calculate the distance between between the start point and the end point of the line.
     * use function of point class because length of a line is equal to the distance
     * between the two points that define this line.
     * @return result value of the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculate the coordinates of the middle point of the line.
     * Find the middle of the line.
     * @return middlePoint the middle point of the line.
     */
    public Point middle() {
        double xMiddle = (this.start.getX() + this.end.getX()) / 2;
        double yMiddle = (this.start.getY() + this.end.getY()) / 2;
        Point middlePoint = new Point(xMiddle, yMiddle);
        return middlePoint;
    }

    /**
     * @return start is the start point of the line.
     */
     public Point start() {
        return start;
    }

    /**
     * @return end is the end point of the line.
     */
     public Point end() {
        return end;
    }

    /**
     * Check if two lines have an intersection or not.
     * @param other other line.
     * @return 'true' if the lines intersect, false otherwise.
     */
      public boolean isIntersecting(Line other) {
        if (this.equals(other)) { //two equal lines do not have intersection
            return false;
        }
        if (intersectionWith(other) != null) { //if there is a point of intersection
            return true;
        }
        return false;
    }

    /**
     * Find Point in common.
     * @param other other line.
     * @return interPoint point in common with the 2 lines.
     */
      private Point findOneCommonPoint(Line other) {
        Point[] tabPoint = new Point[4];
        //create array with point of the two lines
        tabPoint[0] = this.start;
        tabPoint[1] = this.end;
        tabPoint[2] = other.start;
        tabPoint[3] = other.end;
        // check if one point in common
        for (int i = 0; i < 2; i++) {
            for (int j = 2; j < 4; j++) {
                if (tabPoint[i].equals(tabPoint[j])) { //point equal
                    Point interPoint = new Point(tabPoint[i].getX(), tabPoint[i].getY());
                    return interPoint;
                }
            }
        }
        return null;
    }

    /**
     * Find Intersection in 2 standard line.
     * @param other other line.
     * @return interPoint point of intersection with the 2 lines.
     */
     private Point intersectionStandard(Line other) {
        double interX;
        double interY;
        double subParameter;
        double subCoef;
        subParameter = other.parameter - this.parameter;
        subCoef = this.coef - other.coef;
        if (subCoef == 0) { // parallel or on the same line
            return null;
        }
        interX = subParameter / subCoef;
        interY = (this.coef * interX) + this.parameter;
        Point interPoint = new Point(interX, interY);
        if (other.isInTheLine(interPoint) & this.isInTheLine(interPoint)) {
            return interPoint;
        } else {
            return null;
        }
    }

    /**
     * Find Intersection with vertical line.
     * @param lineOne first line.
     * @param lineTwo second line.
     * @return interPoint point of intersection between the two lines.
     */
    private Point intersectionVertical(Line lineOne, Line lineTwo) {
        double interX;
        double interY;
        if (lineOne.coef == Double.POSITIVE_INFINITY) {
            interX = lineOne.start.getX();
            interY = (lineTwo.coef * interX) + lineTwo.parameter;
            Point interPoint = new Point(interX, interY);
            if (lineTwo.isInTheLine(interPoint) & lineOne.isInTheLine(interPoint)) {
                return interPoint;
            }
        }
        return null;
    }

    /**
     * Find the Point of intersection.
     * @param other other line.
     * @return intersection the point of intersection of the 2 lines if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point intersection;
        //check standard lines
        intersection = intersectionStandard(other);
        if (intersection != null) {
            return intersection;
        }
        //check if one point in common
        intersection = findOneCommonPoint(other);
        if (intersection != null) {
            return intersection;
        }
        // check vertical lines
        intersection = intersectionVertical(this, other);
        if (intersection != null) {
            return intersection;
        }
        intersection = intersectionVertical(other, this);
        return intersection;
    }

    /**
     * Check if a point is on the line.
     * @param p the point t check.
     * @return 'true' if the point is in the line and 'false' otherwise.
     */
    public boolean isInTheLine(Point p) {
        boolean checkY;
        boolean checkX;
        checkY = (p.getY() >= Math.min(this.start.getY(), this.end.getY())
                && (p.getY() <= Math.max(this.start.getY(), this.end.getY())));
        checkX = (p.getX() >= Math.min(this.start.getX(), this.end.getX())
                && (p.getX() <= Math.max(this.start.getX(), this.end.getX())));
        return checkX && checkY;
    }

    /**
     * Check if two lines are equals or not.
     * They need to have same end and start point.
     * @param other other line.
     * @return 'true' if the lines are equal, 'false' otherwise.
     */
    public boolean equals(Line other) {
        return (((this.start.equals(other.start)) && (this.end.equals(other.end)))
                || ((this.start.equals(other.end)) && (this.end.equals(other.start))));
    }

    /**
     * closest Intersection to start Of Line.
     * @param rect rectangle.
     * @return p the closest intersection point to the start of the line.
     * otherwise return null if this line does not intersect with the rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = new ArrayList();
        intersectionPoints = rect.intersectionPoints(this); //receive all intersection point
        //if there are intersections check the closest one to the start of the line
        if (!intersectionPoints.isEmpty()) { //there is points of intersection between this line and the rectangle
            double minDistance = this.length();
            double close;
            Point closest = null;
            for (Point p : intersectionPoints) { //check for the closest point of intersection
                close = p.distance(this.start);
                //find minimum distance between start of the line and the point of intersection checked
                if (close < minDistance) {
                    minDistance = close;
                    closest = p;
                }
            }
            return closest; //return the closest point
        }
        return null; //no intersection between the rectangle and the line
    }
}