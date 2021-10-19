
package arkanoid;

import collision.elementGame.Velocity;
import collision.elementGame.Block;
import geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level three.
 * green background - two ball - level "green 3" with colored blocks on right side
 */
public class LevelThree implements LevelInformation {
    private List<Block> blocks;
    private int height;
    private double width;

    @Override
    public  int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList();
        for (int i = 0; i < 2; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(50 + (70 * i), 5);
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
        return 120;
    }

    @Override
    public String levelName() {
        return new String("Green 3");
    }

    @Override
    public Sprite getBackground() {
        Rectangle r = new Rectangle(30 , 40, 740, 600);
        Block back = new Block(r, new Color(0, 130, 30));
        return back;
    }

    @Override
    public java.awt.Color getBackgroundColor() {
        return new Color(0, 130, 30);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList();
        this.blocks = blocksList;
        this.width = 49; //colored block have a width of 50
        this.height = 20; //colored block have a height of 20
        //coordinate for first colored block
        double x = 280;
        double y = 162;
        initColoredBlocks(x , y, Color.gray, 10); //first lines with 12 orange blocks
        initColoredBlocks(329, 182, Color.red, 9); //second lines with 11 magenta blocks
        initColoredBlocks(378, 202, Color.yellow, 8); //third lines with 10 cyan blocks
        initColoredBlocks(427, 223, Color.blue, 7); //fourth lines with 9 green blocks
        initColoredBlocks(476, 243, Color.white, 6); //fifth lines with 8 yellow blocks
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

    /**
     * method that create block for the game and add to the game.
     * @param r rectangle of the block
     * @param c color of the block
     * @return bk new block created
     */
    private Block createBlock(Rectangle r, java.awt.Color c) {
        Block bk = new Block(r, c);
        return  bk;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
