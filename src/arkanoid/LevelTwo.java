
package arkanoid;

import collision.elementGame.Velocity;
import collision.elementGame.Block;
import geometry.Rectangle;
import geometry.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level two.
 * white background - multiple balls - level "wide easy" with a single line of blocks
 */
public class LevelTwo implements LevelInformation {
    private List<Block> blocks;
    private int height;
    private double width;
    private double x;
    private int y;

    @Override
    public  int numberOfBalls() {
        return 10;
    }


    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(40 + (8 * i), 5);
            velocities.add(v);
        }
        for (int i = 0; i < 5; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(100 + (8 * i), 5);
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
        return 600;
    }

    @Override
    public String levelName() {
        return new String("Wide Easy");
    }

    @Override
    public Sprite getBackground() {
        Rectangle r = new Rectangle(30 , 40, 740, 600);
        Block back = new Block(r, Color.white);
        return back;
    }

    @Override
    public java.awt.Color getBackgroundColor() {
        return Color.WHITE;
    }

    /**
     * initcoloredblocks
     * method that create colored blocks of the game.
     * @param c color of the block
     * @param n number of block to create
     */
    private void initColoredBlocks(java.awt.Color c, int n) {
        for (int i = 0; i < n; i++) {
            Rectangle r = new Rectangle(new Point(this.x + (this.width * i), this.y), this.width, this.height);
            Block b = new Block(r, c);
            this.blocks.add(b);
        }
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList();
        this.blocks = blocksList;
        this.width = 49.3;
        this.height = 25;
        //coordinate for first colored block
        this.x = 31;
        this.y = 250;
        initColoredBlocks(Color.red, 2);
        this.x = this.x + (2 * this.width);
        initColoredBlocks(Color.orange, 2);
        this.x = this.x + (2 * this.width);
        initColoredBlocks(Color.yellow, 2);
        this.x = this.x + (2 * this.width);
        initColoredBlocks(Color.green, 3);
        this.x = this.x + (3 * this.width);
        initColoredBlocks(Color.blue, 2);
        this.x = this.x + (2 * this.width);
        initColoredBlocks(Color.pink, 2);
        this.x = this.x + (2 * this.width);
        initColoredBlocks(Color.cyan, 2);
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
