

package arkanoid;
import listener.HitListener;
import collision.elementGame.Ball;
import collision.elementGame.Block;

/**
 * PrintingHitListener class.
 * simple test for listener
 * prints a message to the screen whenever a block is hit.
 */
public class PrintingHitListener implements HitListener {

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
