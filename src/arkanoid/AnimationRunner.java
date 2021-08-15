// ID: 337914121

package arkanoid;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * AnimationRunner class.
 * The AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor.
     * @param gui GUI
     */
    public AnimationRunner(GUI gui) {
     this.framesPerSecond = 60;
        this.gui = gui;
        this.sleeper = new Sleeper();
    }

    /**
     * getGui.
     * @return this.gui GUI of the class
     */
    public GUI getGui() {
     return this.gui;
    }

    /**
     * run.
     * @param animation an animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        DrawSurface d = gui.getDrawSurface();
        Sleeper sleep = new Sleeper();
        sleeper.sleepFor(500); //pause
        return;
    }
}