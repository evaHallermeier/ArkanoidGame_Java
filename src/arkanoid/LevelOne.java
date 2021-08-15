// ID: 337914121
package arkanoid;

import collision.elementGame.Velocity;
import collision.elementGame.Block;
import geometry.Rectangle;
import geometry.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level one.
 * black background - one ball - level "direct hit" with a single red block in the middle
 */
public class LevelOne implements LevelInformation {

    @Override
   public  int numberOfBalls() {
   return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList();
        Velocity v = Velocity.fromAngleAndSpeed(90, 5);
        velocities.add(v);
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
      return new String("Direct Hit");
    }

    @Override
    public Sprite getBackground() {
        Rectangle r = new Rectangle(30 , 40, 740, 600);
        Block back = new Block(r, Color.black);
        return back;
    }

    @Override
    public java.awt.Color getBackgroundColor() {
        return Color.black;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList();
        Rectangle b1 = new Rectangle(new Point(383, 150), 35, 35);
        Block b = new Block(b1, Color.RED);
        blocks.add(b);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
      return 1;
    }
}
