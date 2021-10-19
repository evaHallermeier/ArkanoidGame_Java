

package collision.elementGame;
import collision.Collidable;
import arkanoid.Sprite;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import arkanoid.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A Paddle Object, game object.
 * the "player" a rectangle controlled by the keyboard, and moves according to the player key presses to the left/right.
 */
public class Paddle implements Collidable, Sprite {
    private Rectangle shape;
    private java.awt.Color color;
    private biuoop.KeyboardSensor keyboard;
    private biuoop.GUI gui;

    /**
     * constructor.
     * @param r rectangle.
     * @param g Gui.
     * @param c color of the paddle.
     */
    public Paddle(Rectangle r, biuoop.GUI g, java.awt.Color c) {
        this.shape = r;
        this.gui = g;
        this.keyboard = gui.getKeyboardSensor();
        this.color = c;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        double a = shape.getPoints().get(3).getX(); //x of upperleft of rectangle of paddle
        double b = shape.getPoints().get(3).getY(); //y of upper left point of rectangle paddle
        double c = shape.getWidth();
        double d = shape.getHeight();
        surface.setColor(this.color);
        surface.fillRectangle((int) a, (int) b, (int) c, (int) d);
        surface.setColor(Color.black);
        surface.drawRectangle((int) a, (int) b, (int) c, (int) d);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int speed = 5; //speed of the balls
        int nbOfPart = 5; //number of part the paddle is divided
        Velocity newV;
        Line collisionLineLeftPaddle = shape.getLines().get(1);
        Line collisionLineRightPaddle = shape.getLines().get(2);
        double xCollision = collisionPoint.getX();
        double xUpperLeft = this.shape.getUpperLeft().getX();
        int part = 3; //standard in middle of the paddle
        //find in which part the ball collided the paddle
        part = (int) ((xCollision - xUpperLeft) / (this.shape.getWidth() / nbOfPart));
        if (part < 5) {
            part = part + 1;
        }
        // 90 is up with our formula to calculate velocity
        newV = Velocity.fromAngleAndSpeed((180 - (30 * part)), speed);
        //to the left is between 180 and 90 and to the right is between 90 and 0
        // 150 will be a lot to the left, 120 to the left, 30 a lot to the right and 60 to the right
        if (part == 3) { // normal change of velocity and change of vertical direction
            double dx = currentVelocity.getDx();
            double dy = currentVelocity.getDy() * (-1);
            newV = new Velocity(dx, dy);
        }
        //collision with left or right side of the paddle
        if ((collisionLineLeftPaddle.isInTheLine(collisionPoint))) {
            newV = Velocity.fromAngleAndSpeed(150, speed); // a lot to the left
            //collision with left or right side
        } else if ((collisionLineRightPaddle.isInTheLine(collisionPoint))) {
            newV = Velocity.fromAngleAndSpeed(30, speed); // a lot to the right
        }
        return newV; //return new velocity depends on the type of collision
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(this.keyboard.LEFT_KEY)) {  //the 'left arrow' key is pressed
            moveLeft();
        }
        if (keyboard.isPressed(this.keyboard.RIGHT_KEY)) { //the 'RIGHT arrow' key is pressed
            moveRight();
        }
    }

    /**
     * add this paddle to the game.
     * @param g Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * move the paddle to the left.
     */
    public void moveLeft() {
        this.shape = this.shape.moveLeft();
    }

    /**
     * move paddle to the right.
     */
    public void moveRight() {
        this.shape = this.shape.moveRight();
    }

}
