// ID: 337914121
package arkanoid;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * Sprite collection class.
 * collection of sprite objects : blocks, balls, paddles.
 */
public class SpriteCollection {
    private List<Sprite> spriteObjects = new ArrayList<Sprite>();

    /**
     * method that add the given sprite object to the sprite collection.
     * @param s a sprite object.
     */
    public void addSprite(Sprite s) {
        spriteObjects.add(s);
    }

    /**
     * method that remove a given sprite object from the sprite collection.
     * @param s a sprite object.
     */
    public void removeSprite(Sprite s) {
        spriteObjects.remove(s);
    }

    /**
     * method notify all time passed.
     * that call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteObj = new ArrayList<Sprite>(this.spriteObjects);
       for (Sprite objectS : spriteObj) {
        objectS.timePassed();
       }
    }

    /**
     * method draw all the sprite objects.
     * that call drawOn(d) on all sprites.
     * @param d drawsurface.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spriteObj = new ArrayList<Sprite>(this.spriteObjects);
        for (Sprite objectS : spriteObj) {
            objectS.drawOn(d);
        }
    }
}
