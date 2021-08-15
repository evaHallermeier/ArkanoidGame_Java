// ID: 337914121
package collision.elementGame;
import biuoop.DrawSurface;
import java.awt.Color;
import collision.Collidable;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import arkanoid.GameLevel;
import java.util.ArrayList;
import arkanoid.Sprite;
import listener.HitListener;
import listener.HitNotifier;
import java.util.List;

/**
 * A Block Object, game object, element of the game.
 * Obstacles on the screen that don t move but are removed when they are hit.
 * blocks are collidables because they can be hit by ball.
 * we need to draw them so they are sprite.
 * when a block is hit it has to prevent it to the block remover.
 */
public class Block  implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private java.awt.Color color;
    private List<HitListener> hitListeners;
    private boolean isDeathRegion;

    /**
     * constructor.
     * @param r rectangle.
     * @param c color of the block.
     */
    public Block(Rectangle r, java.awt.Color c) {
        this.shape = r;
        this.color = c;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
    return this.shape;
    }

    /**
     * getColor.
     * @return this.color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
            for (Line linesOfBlock : this.shape.getLines()) { //over all lines of the rectangle (block)
                if (linesOfBlock.isInTheLine(collisionPoint)) { //case collision on the block
                    if (linesOfBlock.getCoef() == Double.POSITIVE_INFINITY) { //collision on vertical line
                        dx = currentVelocity.getDx() * (-1); //change horizontal direction
                         dy = currentVelocity.getDy();
                    }
                    if (linesOfBlock.getCoef() == 0) { // collision on horizontal lines of the block
                         dx = currentVelocity.getDx();
                         dy = currentVelocity.getDy() * (-1); //change vertical direction
                    }
                    if (this == hitter.getDeathRegion()) { //case hit death region
                        dx = currentVelocity.getDx();
                        dy = currentVelocity.getDy();
                    }
                }
            }
        Velocity newV = new Velocity(dx, dy); //create new velocity
        this.notifyHit(hitter);
        return newV; //return new velocity
    }

    @Override
    public void drawOn(DrawSurface surface) {
        double a = shape.getPoints().get(3).getX(); //x of upper left of rectangle block
        double b = shape.getPoints().get(3).getY(); //y of upper left point of rectangle block
        double c = shape.getWidth(); //width of the block
        double d = shape.getHeight(); //height of the block
            surface.setColor(this.color);
            surface.fillRectangle((int) a, (int) b, (int) c, (int) d);
            surface.setColor(Color.black);
            surface.drawRectangle((int) a, (int) b, (int) c, (int) d);

    }

    @Override
    public void timePassed() {
    }

    /**
     * add to game.
     * add a block to the game.
     * add the block to sprites and collidables  objects of the game
     * @param g a Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove a block from the game.
     * @param gameLevel a Game.
     */
    public void removeFromGame(GameLevel gameLevel) {
     gameLevel.removeSprite(this);
     gameLevel.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notifyHit.
     * method that  notify all listeners about a hit event
     * @param hitter the ball that hit this block
     */
    public void notifyHit(Ball hitter) {
        if (this == hitter.getDeathRegion()) { //case the ball hit the death region
            List<HitListener> listen = new ArrayList<HitListener>(hitter.getHitListeners());
            // Notify all listeners about a hit event
            for (HitListener hlB : listen) {
                hlB.hitEvent(this, hitter);
            }
        } else { //case of hitting a normal block (border or colored one)
            // Make a copy of the hitListeners before iterating over them.
            List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
            // Notify all listeners about a hit event
            for (HitListener hl : listeners) {
                hl.hitEvent(this, hitter);
            }
        }
    }
    }