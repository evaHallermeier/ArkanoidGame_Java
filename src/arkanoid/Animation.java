
package arkanoid;
import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {

    /**
     * do one frame.
     * in charge of the logic of the game
     * contain game-specific logic and stopping conditions
     * @param d drawsurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop.
     * is in charge of stopping condition.
     * @return d true or false depends if the game need to stop or not
     */
    boolean shouldStop();
}
