// ID: 337914121

package arkanoid;
import listener.HitListener;
import collision.elementGame.Ball;
import collision.elementGame.Block;

/**
 * A Ball remover class.
 * Ball remover is in charge of removing balls, and updating a balls counter.
 */

public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param gameLevel Game
     * @param nbOfBalls counter to count number of balls in the game
     */
    public BallRemover(GameLevel gameLevel, Counter nbOfBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = nbOfBalls;
    }

    /**
     * get remainingBalls.
     * @return this.remainingBalls
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit == this.gameLevel.getDeathRegion()) { //if the ball hit the death region
            hitter.removeFromGame(this.gameLevel); //remove this ball hitter from the game
            hitter.removeHitListener(this); //this ball can t be hit listener anymore
            remainingBalls.decrease(1); //number of ball in the game decrease
        }
    }
}
