
package arkanoid;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * pause screen class.
 * option to pause the game when pressing the p key.
 * It is a very simple animation,
 * that will display a screen with the message paused -- press space to continue until a key is pressed.
 */
public class PauseScreen implements Animation {
        private KeyboardSensor keyboard;
        private boolean stop;

    /**
     * constructor.
     * @param k keyboard sensor
     */
   public PauseScreen(KeyboardSensor k) {
            this.keyboard = k;
            this.stop = false;
   }

   @Override
   public void doOneFrame(DrawSurface d) {
       d.setColor(Color.gray);
       d.fillRectangle(0, 0, 800, 600);
       d.setColor(Color.black);
       d.fillCircle(375, 250, 100);
       d.setColor(Color.WHITE);
       d.fillRectangle(320, 190, 45, 120);
       d.fillRectangle(380, 190, 45, 120);
       d.drawText(65, 450, "paused press space to continue", 50);
       if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
           this.stop = true;
       }
   }

   @Override
   public boolean shouldStop() {
       return this.stop;
   }
}
