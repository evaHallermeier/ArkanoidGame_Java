// ID: 337914121
package collision.elementGame;
import geometry.Point;

/**
 * Velocity class.
 * Velocity specifies the change in position on the `x` and the `y` axes on a point.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx  dx of the velocity.
     * @param dy dy of the velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * constructor
     * specify the velocity in terms and angle and a speed,
     * meaning speed units in the angle degrees direction.
     * @param angle angle.
     * @param speed speed.
     * @return newV velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * (Math.cos(Math.toRadians(angle)));
        double dy = -speed * (Math.sin(Math.toRadians(angle)));
        Velocity newV = new Velocity(dx, dy);
        return newV;
    }

    /**
     * get dx.
     * @return dx of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * get dy.
     * @return dy of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * applyToPoint.
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * in order to move the ball with a velocity given.
     * @param p point to apply on the velocity.
     * @return newPoint the new point after the move(depends on velocity).
     */
    public Point applyToPoint(Point p) {
        double pX;
        double pY;
        pX = p.getX() + this.dx;
        pY = p.getY() + this.dy;
        Point newPoint = new Point(pX, pY);
        return newPoint;
    }
}