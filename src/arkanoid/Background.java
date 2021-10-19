
package arkanoid;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Background class.
 * in charge of the background depends on levels of the game.
 */
public class Background implements Sprite {
    private GameLevel g;

    /**
     * constructor.
     * @param game gamelevel
     */
    public Background(GameLevel game) {
        this.g = game;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.g.getLevel().levelName().equals("Direct Hit")) {
            int size = 65;
            for (int i = 0; i < 3; i++) {
                d.setColor(Color.blue);
                d.drawCircle(400, 175, size + (25 * i));
            }
            d.setColor(Color.blue);
            d.drawLine(270, 169, 550, 169);
            d.drawLine(400, 40, 400, 310);
        }
        if (this.g.getLevel().levelName().equals("Wide Easy")) {
            int size = 30;
            d.setColor(Color.yellow);
            for (int i = 0; i < 100; i++) {
                d.drawLine(120, 120, 31 + (6 * i), 250);
            }
            d.setColor(Color.orange);
            d.fillCircle(120, 120, size);
        }
        if (this.g.getLevel().levelName().equals("Green 3")) {
            //red point on top of the building
            d.setColor(Color.RED);
            d.fillCircle(117, 250, 9);
            //for building
            d.setColor(Color.DARK_GRAY);
            d.fillRectangle(112, 258, 10, 380);
            d.fillRectangle(103, 380, 30, 48);
            d.setColor(Color.BLACK);
            d.fillRectangle(75, 428, 90, 250);
            d.setColor(Color.WHITE);
            d.fillRectangle(85, 440, 70, 210);
            d.setColor(Color.BLACK);
            d.fillRectangle(91, 440, 7, 210);
            d.fillRectangle(109, 440, 7, 210);
            d.fillRectangle(125, 440, 7, 210);
            d.fillRectangle(142, 440, 7, 210);
            d.fillRectangle(85, 480, 70, 7);
            d.fillRectangle(85, 530, 70, 7);
            d.fillRectangle(85, 570, 70, 7);
            d.fillRectangle(85, 620, 70, 7);
        }
        if (this.g.getLevel().levelName().equals("Final Four")) {
            d.setColor(Color.WHITE);
            //for rain
            for (int i = 0; i < 10; i++) {
                d.drawLine(140 + (10 * i), 455, 125 + (10 * i), 600);
                d.drawLine(580 + (8 * i), 520, 550 + (8 * i), 600);
            }
            //for clouds
            d.setColor(Color.lightGray);
            d.fillCircle(150, 450, 25);
            d.fillCircle(180, 452, 25);
            d.fillCircle(210, 450, 25);
            d.fillCircle(180, 460, 21);
            d.fillCircle(160, 465, 21);
            d.fillCircle(180, 470, 19);
            d.fillCircle(600, 500, 19);
            d.fillCircle(620, 510, 21);
            d.fillCircle(615, 520, 21);
            d.fillCircle(605, 525, 18);
            d.fillCircle(590, 520, 18);
            d.fillCircle(630, 520, 22);
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel gamelevel) {
    }
}
