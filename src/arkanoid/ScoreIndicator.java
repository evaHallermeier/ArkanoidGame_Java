

package arkanoid;
import biuoop.DrawSurface;

/**
 * A ScoreIndicator Object.
 * A sprite which will be in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    private GameLevel gameLevel;
    private int currentScore;

    /**
     * constructor.
     * @param gameLevel game
     */
    public ScoreIndicator(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    @Override
    public void drawOn(DrawSurface surface) {
    String score = String.format("Score: %d", currentScore);
    String name = String.format("Level Name: " + this.gameLevel.getLevel().levelName());
    surface.drawText(350, 15, score, 15);
        surface.drawText(520, 15, name, 15);
    }

    @Override
    public void timePassed() {
        this.currentScore = this.gameLevel.getScoreT().getScore();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
