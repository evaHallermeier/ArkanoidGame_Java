package arkanoid;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * Coundown animation class.
 * in charge of counting before starting level of the game
 */
public class CountdownAnimation implements Animation {
    private double numOfSecond;
    private SpriteCollection screen;
    private boolean runner;
    private Counter countFrom;
    private int current;

    /**
     * constructor.
     * @param numOfSeconds number of secons
     * @param countFrom number we begin the count
     * @param gameScreen sprite collection
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSecond = numOfSeconds;
        this.screen = gameScreen;
        this.runner = false;
        this.countFrom = new Counter(countFrom);
        current = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        if (this.current < this.countFrom.getValue()) {
            sleeper.sleepFor((long) ((this.numOfSecond / this.countFrom.getValue()) * 1000));
        }
        screen.drawAllOn(d);
        if (this.current != 0) {
            d.setColor(Color.RED);
            d.drawText(340, 400, Integer.toString(this.current), 170);
        }
        if (this.current == 0) {
            d.setColor(Color.RED);
            d.drawText(300, 400, "Go", 170);
        }
        this.current--;
        if (current < 0) {
            this.runner = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.runner;
    }
}
