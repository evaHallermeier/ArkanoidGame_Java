// ID: 337914121
package arkanoid;

import biuoop.DrawSurface;

/**
 * KeyPressStoppableAnimation class.
 * in charge of key pressed in the game.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed;
    private biuoop.KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;

    /**
     * constructor.
     * @param sensor keyboard sensor
     * @param key special key of the keyboard
     * @param animation animation
     */
    public KeyPressStoppableAnimation(biuoop.KeyboardSensor sensor, String key, Animation animation) {
      this.isAlreadyPressed = true;
      this.sensor = sensor;
      this.key = key;
      this.animation = animation;
      stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.isAlreadyPressed = true;
                this.stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    /**
     * shouldStop.
     * @return d true or false depends if the game need to stop or not
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
