// ID: 337914121
package listener;
import collision.elementGame.Block;
import collision.elementGame.Ball;
/**
 * The HitListener interface.
 * for objects that want to be notified of hit events
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that have being hit
     * @param hitter the ball that hit that block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
