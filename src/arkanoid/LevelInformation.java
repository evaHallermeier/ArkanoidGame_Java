
package arkanoid;

import collision.elementGame.Block;
import collision.elementGame.Velocity;
import java.util.List;

/**
 * interface level information.
 *  specifies the information required to fully describe a level
 */
public interface LevelInformation {

    /**
     * numberOfBalls.
     *  specifies nb of balls for the level
     * @return number of ball
     */
    int numberOfBalls();

    /**
     * initialBallVelocities.
     *  specifies The initial velocity of each ball for the level
     * @return list of velocity of all the balls of the game
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddlespeed.
     *  specifies speed of the paddle for the level
     * @return speed of the paddle
     */
    int paddleSpeed();

    /**
     * paddleWidth.
     *  specifies wisth of the paddle for the level
     * @return width of the paddle
     */
    int paddleWidth();

    /**
     * levelName.
     * the level name will be displayed at the top of the screen.
     *  specifies name of  the level
     * @return name of the level
     */
    String levelName();


    /**
     * getBackgroound.
     *  specifies background of  the level
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * getBackgrooundColor.
     *  specifies color background
     * @return color of the background
     */
    java.awt.Color getBackgroundColor();

    /**
     * blocks.
     *  specifies Blocks that make up this level, each block contains its size, color and location.
     * @return list of all the blocks of the level
     */
    List<Block> blocks();

    /**
     * number of blockstoremove.
     *  specifies Number of blocks that should be removed in the level
     * @return number of blocks of the level
     */
    int numberOfBlocksToRemove();
}
