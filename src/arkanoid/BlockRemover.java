// ID: 337914121

package arkanoid;
import listener.HitListener;
import collision.elementGame.Block;
import collision.elementGame.Ball;

/**
 * A Block remover class.
 * a BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel Game
     * @param removedBlocks counter to count number of blocks in the game
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * get remainingBlocks.
     * @return this.remainingBlocks
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}