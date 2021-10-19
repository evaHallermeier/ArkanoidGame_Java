
package collision;
import geometry.Point;

/**
 * Collision Info class.
 * collision info have a location of the collision and an object type (paddle or a block) the ball collide to.
 */
public class CollisionInfo {
    private Point location;
    private Collidable collisionObject;

    /**
     * Constructor.
     * @param p a point for location of the collision.
     * @param object a collidable object (block or paddle).
     */
    public CollisionInfo(Point p, Collidable object) {
        this.location = p;
        this.collisionObject = object;
    }

    /**
     * collision point.
     * @return this.location the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.location;
    }

    /**
     * collision object.
     * @return this.collisionObject the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
