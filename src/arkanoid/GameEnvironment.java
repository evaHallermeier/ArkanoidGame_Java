
package arkanoid;
import java.util.ArrayList;
import java.util.List;
import collision.Collidable;
import collision.CollisionInfo;
import geometry.Point;
import geometry.Line;

/**
 * Game environment class.
 * collection of objects a Ball can collide with.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();

  /**
   * method that add the given collidable to the environment.
   * @param c a collidable object.
   */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * method that remove a given collidable from the environment.
     * @param c a collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * get closest collision method.
     * find collision info about the next future collision.
     * with an object moving from line.start() to line.end() the method check
     * witch point will be the next collision location.
     * @param trajectory line that is the trajectory of the ball.
     * @return null if this object will not collide with any of the collidables in this collection.
     * else, return the information about the closest collision that is going to occur.
     */
      public CollisionInfo getClosestCollision(Line trajectory) {
        for (Collidable object  : this.collidables) { //over all the collidables
            Point collision = trajectory.closestIntersectionToStartOfLine(object.getCollisionRectangle());
                if (collision != null) { //find a future collision
                CollisionInfo info = new CollisionInfo(collision, object); //create collsion info
                return info; //return collision info
                }
        }
        return null;
      }
}
