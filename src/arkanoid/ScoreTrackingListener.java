// ID: 337914121
package arkanoid;

import listener.HitListener;
import collision.elementGame.Block;
import collision.elementGame.Ball;

/**
 * A ScoreTrackingListener Object.
 * A hitlistener that to update this counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener  implements HitListener {
    private Counter currentScore;
    private GameLevel gameLevel;

    /**
     * constructor.
     * @param gameLevel game
     * @param c counter
     */
    public ScoreTrackingListener(GameLevel gameLevel, Counter c) {
        this.gameLevel = gameLevel;
        this.currentScore = c;
    }

    /**
     * constructor.
     * @param scoreCounter counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * get score.
     * @return this.scoreCounter
     */
    public int getScore() {
        return this.currentScore.getValue();
    }

    /**
     * get counter.
     * @return this.scoreCounter
     */
    public Counter getCounter() {
        return this.currentScore;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.currentScore.increase(5); // 5 points more wen a block is hit
    }
}
