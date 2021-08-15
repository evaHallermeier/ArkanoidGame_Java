// ID: 337914121
package collision;
import geometry.Point;
import geometry.Rectangle;
import collision.elementGame.Velocity;
import collision.elementGame.Ball;

/**
 * Collidable interface.
 * for collidable objects.
 * Block or a Paddle and edges of the screen (blocks) are collidable objects.
 */

public interface Collidable {

    /**
     * method getCollisionRectangle.
     * @return Rectangle the "collision shape" of the object collided (block or paddle).
     */
    Rectangle getCollisionRectangle();

    /**
     * hit method.
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint  collision point.
     * @param hitter the ball that hit the collidable object.
     * @param currentVelocity velocity.
     * @return Velocity the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}