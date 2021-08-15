// ID: 337914121
import arkanoid.LevelInformation;
import arkanoid.LevelOne;
import arkanoid.LevelTwo;
import arkanoid.LevelThree;
import arkanoid.LevelFour;
import arkanoid.AnimationRunner;
import arkanoid.GameFlow;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * Game of ass6.
 */
public class Ass6Game {

    /**
     * main function for the game.
     * @param args arguments.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList();
        if ((args.length == 0)) {
          classicGame(levels);
        } else {
            for (int i = 0; i < args.length; i++) {
                    // Parse the string argument into an integer value.
                if (isLevel(args[i])) {
                    int level = Integer.parseInt(args[i]);
                    if ((level < 5) && (level > 0)) {
                        LevelInformation levelName = checkLevel(level);
                        levels.add(levelName);
                    }
                }
                if (levels.isEmpty()) {
                    classicGame(levels);
                }
            }
        }
        GUI gui = new GUI("Arkanoid", 800, 600); //creating the GUI 800 * 600
        AnimationRunner animationR = new AnimationRunner(gui);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        GameFlow gameflow = new GameFlow(gui, animationR, keyboardSensor);
        gameflow.runLevels(levels);
        gui.close();
    }

    /**
     * checkLevel.
     * @param l number of the level.
     * @return part a level information
     */
    private static LevelInformation checkLevel(int l) {
        if (l == 1) {
            LevelInformation part = new LevelOne();
            return part;
        }
        if (l == 2) {
            LevelInformation  part = new LevelTwo();
            return part;
        }
        if (l == 3) {
            LevelInformation part = new LevelThree();
            return part;
        }
        if (l == 4) {
            LevelInformation part = new LevelFour();
            return part;
        }
        return null;
    }

    /**
     * check if the argument is valid (if it s a number).
     * @param argument argument from the main.
     * @return true or false depends if the string is a number or not
     */
    public static boolean isLevel(String argument) {
        try {
            Integer.parseInt(argument);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * classicGame.
     * @param levels list of level information.
     */
    public static void classicGame(List<LevelInformation> levels) {
        LevelInformation level1 = new LevelOne();
        LevelInformation level2 = new LevelTwo();
        LevelInformation level3 = new LevelThree();
        LevelInformation level4 = new LevelFour();
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
    }

}
