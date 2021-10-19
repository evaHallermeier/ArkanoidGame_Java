
package arkanoid;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.util.List;
import java.awt.Color;
import collision.elementGame.Ball;
import collision.elementGame.Block;
import collision.elementGame.Paddle;
import collision.Collidable;
import geometry.Rectangle;


/**
 * Game class.
 * hold the sprites and the collidables.
 * this class is in charge of the animation.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites; //collection of sprite objects
    private GameEnvironment environment; //collection of collidables object of the game
    private GUI gui;
    private BlockRemover blockR;
    private Block deathRegion;
    private BallRemover ballsRemover;
    private ScoreTrackingListener scoreTrack;
    private ScoreIndicator scorePlay;
    private boolean running;
    private AnimationRunner runner;
    private biuoop.KeyboardSensor keyboard;
    private Block displayZone;
    private Paddle paddle;
    private LevelInformation level;
    private String situation;

    /**
     * constructor.
     * @param g GUI
     * @param level Level information
     * @param k keyboard sensor
     * @param anim animation runner
     * @param score actual score of the entire game
     */
    public GameLevel(GUI g, LevelInformation level, KeyboardSensor k, AnimationRunner anim, int score) {
        this.sprites = new SpriteCollection(); //create new collection of sprite
        this.environment = new GameEnvironment(); //create new game environment
        this.runner = anim;
        this.keyboard = k;
        this.level = level;
        this.gui = g;
        Counter c = new Counter(score);
        this.scoreTrack = new ScoreTrackingListener(c);
    }

    /**
     * get death region.
     * @return this.deathRegion
     */
    public Block getDeathRegion() {
        return this.deathRegion;
    }

    /**
     * getSituation.
     * @return this.situation
     */
    public String getSituation() {
        return this.situation;
    }

    /**
     * getLevel.
     * @return this.level
     */
    public LevelInformation getLevel() {
        return this.level;
    }

    /**
     * add  a collidable object to the game.
     * @param c collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add sprite to the game.
     * @param s sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove  a collidable object from the game.
     * @param c collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove sprite from the game.
     * @param s sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * method initialize the game
     * Initialize/ setting up a new game: create the Blocks and Ball and Paddle.
     * and add them to the game.
     */
    public void initialize() {
        this.running = true;
        Counter counterBlock = new Counter(this.level.numberOfBlocksToRemove());
        this.blockR = new BlockRemover(this, counterBlock);
        createBorders(); //create block for borders
        addSprite(this.level.getBackground());
        createBlocks(this.level.blocks());
        Rectangle zone = new Rectangle(0, 0, 800, 18);
        this.displayZone = new Block(zone, Color.white);
        addCollidable(this.displayZone);
        addSprite(this.displayZone);
        this.scorePlay = new ScoreIndicator(this);
        this.scorePlay.timePassed();
        this.addSprite(this.scorePlay);
        Background back = new Background(this);
        addSprite(back);
    }

    /**
     * init balls
     * Initialize/ setting up balls of the game.
     * and add them to the game.
     */
    private void initBalls() {
        int nbBalls = this.level.numberOfBalls();
        for (int i = 0; i < nbBalls; i++) {
            Ball ball = new Ball(400, 550, 5, Color.WHITE, this.getDeathRegion());
            ball.setVelocity(this.level.initialBallVelocities().get(i));
            ball.setGameEnvironment(this.environment); //set ball to the game environment
            ball.addToGame(this);
            ball.addHitListener(this.ballsRemover);
        }
    }

    /**
     * get score tracking.
     * @return this.scoreTrack
     */
    public ScoreTrackingListener getScoreT() {
        return this.scoreTrack;
    }

    /**
     * method initialize the paddle.
     * Initialize/ setting up a paddle.
     * and add it to the game.
     */
    private void initPaddle() {
        //width and height of the paddle
        double w = this.level.paddleWidth();
        double h = 15;
        //initial coordinates for paddles
        double x = 400 - (w / 2);
        double y = 551;
        java.awt.Color c = Color.ORANGE;
        Rectangle r = new Rectangle(x , y, w, h); //rectangle for paddle
        this.paddle = new Paddle(r, this.runner.getGui(), c);
        paddle.addToGame(this);
        addCollidable(paddle);
    }

    /**
     * method that create block for the game and add to the game.
     * @param blocks list of all the blocks of the level
     */
    private void createBlocks(List<Block> blocks) {
        for (Block b : blocks) {
            b.addToGame(this); //adding the block
            b.addHitListener(this.blockR);
            b.addHitListener(this.scoreTrack);
        }
    }

    /**
     * method that create block for borders of the game.
     */
    private void createBorders() {
        Rectangle r1 = new Rectangle(0, 18, 800, 22); //upper edge
        Rectangle r2 = new Rectangle(0, 0, 31, 600); //left edge
        Rectangle r3 = new Rectangle(769, 0, 31, 600); //right edge
        Rectangle r4 = new Rectangle(0, 569, 800, 31); //lower edge
        java.awt.Color colorBorders = Color.GRAY;
        Block b1 = new Block(r1, colorBorders);
        Block b2 = new Block(r2, colorBorders);
        Block b3 = new Block(r3, colorBorders);
        Sprite back = this.level.getBackground();
        Block b4 = new Block(r4, this.level.getBackgroundColor());
        b4.addToGame(this); //adding the block
        b1.addToGame(this); //adding the block
        b2.addToGame(this); //adding the block
        b3.addToGame(this); //adding the block
        this.deathRegion = b4;
        this.deathRegion.addHitListener(this.blockR);
    }

    /**
     * playOneTurn/Run method.
     * run the game - start the animation loop.
     */
    public void playOneTurn() {
        Counter counterBalls = new Counter(this.level.numberOfBalls());
        this.ballsRemover = new BallRemover(this, counterBalls);
        initPaddle();
        initBalls();
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.ballsRemover.getRemainingBalls().getValue() == 0) {
            String name = new String("FAIL");
            this.situation = name;
            this.running = false;
            this.scorePlay.timePassed();
        }
        if (this.keyboard.isPressed("p")) {
              KeyPressStoppableAnimation keyPressStoppableAnimationp =
                    new KeyPressStoppableAnimation(this.keyboard, keyboard.SPACE_KEY, new PauseScreen(this.keyboard));
            this.runner.run(keyPressStoppableAnimationp); //start running the PauseScreen animation
        }
        if (this.blockR.getRemainingBlocks().getValue() == 0) { // only remain borders block
            this.scoreTrack.getCounter().increase(100); //increase 100 points when all the blocks are hit
            this.scorePlay.timePassed(); // actualize score
            this.running = false;
            String name = new String("WIN");
            this.situation = name;
        }
        this.sprites.drawAllOn(d); //call drawOn for sprites
        this.sprites.notifyAllTimePassed();
    }
}
