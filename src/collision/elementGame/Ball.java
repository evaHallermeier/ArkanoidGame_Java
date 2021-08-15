// ID: 337914121

package collision.elementGame;
import collision.CollisionInfo;
import biuoop.DrawSurface;
import java.awt.Color;
import geometry.Point;
import geometry.Line;
import arkanoid.GameEnvironment;
import arkanoid.GameLevel;
import java.util.ArrayList;
import arkanoid.Sprite;
import listener.HitListener;
import listener.HitNotifier;
import java.util.List;

/**
 * A Ball Object, game object, element of the game.
 * Balls have size (radius), color, and location (a Point).
 * Balls also know how to draw themselves on a DrawSurface.
 * When the ball hits either the blocks or the paddle, it will change its direction.
 */
public class Ball implements Sprite, HitNotifier {
    private int size; //radius
    private java.awt.Color color;
    private Point center; //location of the ball
    private Velocity v;
    private GameEnvironment environment;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Block deathRegion;

    /**
     * constructor.
     * @param x  the coordinate of the center of the ball (his location).
     * @param y  the coordinate of the center of the ball (his location).
     * @param r  radius of the ball.
     * @param color  color to draw the ball on a surface.
     * @param deathRegion block where ball are removed from the game when the ball hit it.
     */
    public Ball(int x, int y, int r, java.awt.Color color, Block deathRegion) {
        this.size = r;
        this.color = color;
        Point c = new Point(x, y);
        this.center = c;
        this.deathRegion = deathRegion;
    }

    /**
     * constructor.
     * @param p  point for the center..
     * @param r  radius of the ball.
     * @param color  color to draw the ball on a surface.
     */
    public Ball(Point p, int r, java.awt.Color color) {
        this.size = r;
        this.color = color;
        this.center = p;
    }

    /**
     * get x.
     * @return this.center.getX coordinate x of the location of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * get y.
     * @return this.center.getY coordinate y of the location of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * get size.
     * @return this.size size of the ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * get velocity.
     * @return this.v velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * get Color.
     * @return this.color color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * get Game environment.
     * @return this.environment game environment of the ball.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * get death region.
     * @return this.deathRegion
     */
    public Block getDeathRegion() {
        return this.deathRegion;
    }

    /**
     * get hitlisteners.
     * @return this.hitListeners
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    /**
     * drawOn.
     * draw the ball on the given DrawSurface.
     * @param surface surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.getX(), (int) this.getY(), this.size);
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.size);
    }

    /**
     * set velocity.
     * @param velocity velocity.
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * set velocity.
     * @param dx dx of the velocity.
     * @param dy dy of the velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * set game environment.
     * @param game new game environment for the ball.
     */
    public void setGameEnvironment(GameEnvironment game) {
        this.environment = game;
    }

    /**
     * moveOneStep.
     * move the ball and check if the new position is valid.
     * change the location of the ball and velocity if exception (hit method).
     */
    public void moveOneStep() {
        Point destination = this.getVelocity().applyToPoint(this.center); // new potential position
        Point oldP = this.center;
        //calculate trajectory of the ball between his center and destination location
        Line trajectory = new Line(this.center, destination);
        //get the closest collision between the ball and the collidables objects
        CollisionInfo info = environment.getClosestCollision(trajectory);
        if (info == null) { //no collision for the next move
            this.center = destination; //the move to his normal destination
        } else { //collision for next move
            Point locationCol = info.collisionPoint(); //location of the collision
            Velocity newV = info.collisionObject().hit(this, locationCol, this.v); //calculate new velocity
            double x = this.center.getX();
            double y = this.center.getY();
            if ((newV.getDx() * this.v.getDx()) < 0) { //check if dx has been change (collision on horizontal line)
                if (newV.getDx() < 0) { //the ball will go on the left side
                    x = x - 1; //change position to the left to not have again a collision
                } else { //move to right side
                    x = x + 1;  //change position to the right to not have again a collision
                }
            }
            if ((newV.getDy() * this.v.getDy()) < 0) { //change if dy has been change (collision on vertical line)
                if (newV.getDy() < 0) { //the ball will go up
                    y = y - 1; //change position
                } else { //move to down
                    y = y + 1; //change position
                }
            }
            this.v = newV; //actualize the velocity of the ball
            this.center = new Point(x, y); //actualize location of the ball
        }
    }

    /**
     * time passed.
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        moveOneStep();
    } //make the ball move

    /**
     * add a ball to the game.
     * @param g a Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    } //add the ball to the Game in sprites objects

    /**
     * remove a block from the game.
     * @param gameLevel a Game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
}
