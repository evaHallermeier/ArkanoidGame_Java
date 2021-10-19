
package arkanoid;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * end game class.
 * to close the game when pressing the space key.
 * It is a very simple animation,
 * that will display a screen until a key is pressed.
 */
public class EndGame implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String situation;
    private int score;

    /**
     * constructor.
     * @param k keyboard sensor
     * @param situation string that describe the situation of the player win or fail
     * @param scorePlayer score of the entire game
     */
    public EndGame(KeyboardSensor k, String situation, int scorePlayer) {
        this.keyboard = k;
        this.stop = false;
        this.situation = situation;
        this.score = scorePlayer;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String scoreNumber = String.format("%d", this.score);
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.yellow);
        if (this.situation.equals("FAIL")) {
            d.drawText(30, d.getHeight() / 2, "Game Over." + " Your score is " + scoreNumber, 50);
        }
        if (this.situation.equals("WIN")) {
            d.drawText(40, d.getHeight() / 2, "You Win! Your score is " + scoreNumber, 50);
        }
        d.drawText(170, 500, "end -- press space to continue", 30);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
