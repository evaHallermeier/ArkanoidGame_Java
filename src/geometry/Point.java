

package geometry;

/**
 * A Point Object.
 * The class support method to check if two points are equals, distance between them.
 * It is implemented using created by an x and y value which are coordinates.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     * @param x  part of the coordinate of the point.
     * @param y  part of the coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the distance between this point and another one.
     * @param other other point
     * @return result return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double result;
        double first;
        double second;
        double newFirst;
        double newSecond;
        double sum;
        first = this.x - other.getX();
        second = this.y - other.getY();
        newFirst = Math.pow(first, 2);
        newSecond = Math.pow(second, 2);
        sum = newFirst + newSecond;
        result = Math.sqrt(sum);
        return result;
    }

    /**
     * Check if two points are equal or not.
     * to be equals they need same coordinates.
     * @param other other point.
     * @return 'true' if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return ((this.getX() == other.getX()) && (this.y == other.getY()));
    }

    /**
     * get x.
     * @return this.x coordinate x of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * get y.
     * @return y coordinate y of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * set x.
     * change the coordinate x of the point.
     * @param newX new coordinate x of the point.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * set y.
     * change coordinate y of the point.
     * @param newY new coordinate of the point
     */
    public void setY(double newY) {
        this.y = newY;
    }
}
