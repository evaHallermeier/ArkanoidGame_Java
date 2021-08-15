// ID: 337914121
package arkanoid;

import collision.elementGame.Velocity;
import collision.elementGame.Block;
import geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level four.
 * blue background - three ball - level "final four" with colored blocks clouds and rain
 */
public class LevelFour implements LevelInformation {
    private List<Block> blocks;
    private int height;
    private double width;

    @Override
    public  int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList();
        for (int i = 0; i < 3; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(50 + (25 * i), 5);
            velocities.add(v);
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return new String("Final Four");
    }

    @Override
    public Sprite getBackground() {
        Rectangle r = new Rectangle(30 , 40, 739, 600);
        Block back = new Block(r, new Color(0, 150, 250));
        return back;
    }

    @Override
    public java.awt.Color getBackgroundColor() {
        return new Color(0, 150, 250);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList();
        this.blocks = blocksList;
        this.height = 20;
        this.width = 49.2;
        int x = 31;
        int y = 80;
        Counter c = new Counter();
        initColoredBlocks(x, y, Color.GRAY, 15);
        c.increase(1);
        y = 80 + this.height;
        initColoredBlocks(x, y, Color.RED, 15);
        c.increase(1);
        y = 80 + (c.getValue() * this.height);
        initColoredBlocks(x, y, Color.yellow, 15);
        c.increase(1);
        y = 80 + c.getValue() * this.height;
        initColoredBlocks(x, y, Color.green, 15);
        c.increase(1);
        y = 80 + c.getValue() * this.height;
        initColoredBlocks(x, y, Color.white, 15);
        c.increase(1);
        y = 80 + c.getValue() * this.height;
        initColoredBlocks(x, y, Color.pink, 15);
        c.increase(1);
        y = 80 + c.getValue() * this.height;
        initColoredBlocks(x, y, Color.cyan, 15);
        return this.blocks;
    }

    /**
     * method that create colored blocks of the game.
     * @param x coordinate block
     * @param y coordinate block
     * @param c color of the block
     * @param n number of blocks
     */
    private void initColoredBlocks(double x, double y, java.awt.Color c, int n) {
        for (int i = 0; i < n; i++) {
            //only change coordinate x to have all the block on a single line
            Rectangle r = new Rectangle((x + (i * this.width)), y, this.width, this.height);
            Block b = new Block(r, c);
            this.blocks.add(b);
        }
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
