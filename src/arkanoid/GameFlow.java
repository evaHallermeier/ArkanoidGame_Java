
package arkanoid;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * Game flow class.
 * in charge of the flow of the game to pass level after level.
 */
public class GameFlow {
    private KeyboardSensor sensor;
    private AnimationRunner animationR;
    private biuoop.GUI gui;
    private GameLevel game;
    private int score;

    /**
     * constructor.
     * @param gui GUI
     * @param ar animation runner
     * @param ks keyboard sensor
     */
    public GameFlow(biuoop.GUI gui, AnimationRunner ar, KeyboardSensor ks) {
        this.sensor = ks;
        this.animationR = ar;
        this.gui = gui;
    }

    /**
     * constructor.
     * @param levels list of all the levels we need to run and depend on the arguments from the main
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter c = new Counter(levels.size());
        for (LevelInformation levelInfo : levels) {
            c.decrease(1);
            GameLevel level = new GameLevel(this.gui, levelInfo, this.sensor, this.animationR, this.score);
            this.game = level;
            this.game.initialize();
            while (!level.shouldStop()) {
                level.playOneTurn();
            }
            this.score = level.getScoreT().getCounter().getValue();
            if (level.getSituation().equals("WIN") && (c.getValue() == 0)) {
                this.animationR.run(new EndGame(this.sensor, "WIN",
                        level.getScoreT().getCounter().getValue()));
                this.gui.close();
            }
            if (level.getSituation().equals("FAIL")) {
                this.animationR.run(new EndGame(this.sensor, "FAIL",
                        level.getScoreT().getCounter().getValue()));
                this.gui.close();
            }
        }
    }
}
