package View;

import Controller.ControllerInterface;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Space Misery game view class implementing the ViewInterface.
 */
public class SMView extends PApplet implements ViewInterface {
    /**
     * Represents the interface to the controller, allowing interaction with the game logic.
     * This attribute is set through dependency injection.
     */
    private ControllerInterface controller;

    /**
     * Represents the image for the enemy entity in the game.
     */
    private PImage enemyImage;

    /**
     * Represents the image for the player entity in the game.
     */
    private PImage playerImage;

    /**
     * Represents the image for the main menu background.
     */
    private PImage menuImage;

    /**
     * Represents the image for the game over screen.
     */
    private PImage gameoverImage;

    /**
     * Represents the image for the player's laser.
     */
    private PImage playerLaserImage;

    /**
     * Represents the image for the enemy's laser.
     */
    private PImage enemyLaserImage;

    /**
     * Represents the image for the explosion effect in the game.
     */
    private PImage explosionImage;

    /**
     * Represents the image for an asteroid entity in the game.
     */
    private PImage asteroidImage;

    /**
     * Represents the image for the background of the game.
     */
    private PImage backgroundImage;

    /**
     * Represents the image for the next level screen.
     */
    private PImage nextLevelImage;

    /**
     * Represents the image for the victory screen.
     */
    private PImage victoryImage;

    /**
     * Represents the image for the boss entity in the game.
     */
    private PImage bossImage;

    /**
     * Represents the image for the help screen.
     */
    private PImage helpImage;




    /**
     * Sets the controller associated with the view.
     *
     * @param controller The controller to set.
     */
    public void setController(ControllerInterface controller) {
        this.controller = controller;
    }

    /**
     * Configures the size of the game window.
     */
    public void settings() {
        size(750, 850);
    }

    /**
     * Initializes the game setup, loads images, and sets initial game state.
     */
    public void setup() {
        controller.gameStart();
        playerImage = loadImage("Images/player.png");
        enemyImage = loadImage("Images/enemy.png");
        menuImage = loadImage("Images/menu.png");
        menuImage.resize(750, 850);
        gameoverImage = loadImage("Images/gameover.png");
        gameoverImage.resize(750, 850);
        playerLaserImage = loadImage("Images/playerlaser.png");
        enemyLaserImage  = loadImage("Images/enemylaser.png");
        explosionImage = loadImage("Images/explosion.png");
        asteroidImage = loadImage("Images/asteroidImage.png");
        backgroundImage = loadImage("Images/background.png");
        backgroundImage.resize(750, 850);
        nextLevelImage = loadImage("Images/LEVELUP.png");
        nextLevelImage.resize(750, 850);
        victoryImage = loadImage("Images/victory.png");
        victoryImage.resize(750, 850);
        bossImage = loadImage("/Images/boss.png");
        helpImage = loadImage("/Images/help.png");
        helpImage.resize(750, 850);
    }

    /**
     * Draws the game elements on the screen and handles user input.
     */
    public void draw() {
        background(0);
        controller.gameSetting();
        if (keyEvent != null) controller.keyPressed(keyEvent);
    }

    /**
     * Checks the distance between two points.
     *
     * @param x The x-coordinate of the first point.
     * @param y The y-coordinate of the first point.
     * @param j The x-coordinate of the second point.
     * @param k The y-coordinate of the second point.
     * @return The distance between the two points.
     */
    public int checkDistance(float x, float y, float j, float k) {
        return (int) dist(x, y, j, k);
    }

    /**
     * Draws an image on the view.
     *
     * @param p     The image to draw.
     * @param x     The x-coordinate of the image.
     * @param y     The y-coordinate of the image.
     * @param size1 The width of the image.
     * @param size2 The height of the image.
     */
    public void drawImage(PImage p, int x, int y, int size1, int size2) {
        image(p, x, y, size1, size2);
    }

    /**
     * Gets the height of the view.
     *
     * @return The height of the view.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the width of the view.
     *
     * @return The width of the view.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Loads the menu background.
     */
    public void loadMenuBackground() {
        background(menuImage);
    }

    /**
     * Loads the game background.
     */
    public void loadBackground() {
        background(backgroundImage);
    }

    /**
     * Loads the game over background.
     */
    public void loadGameOverBG() {
        background(gameoverImage);
    }

    /**
     * Loads the next level background.
     */
    public void loadNextLevelBG() {
        background(nextLevelImage);
    }

    /**
     * Loads the victory background.
     */
    public void loadVictoryBG() {
        background(victoryImage);
    }

    /**
     * Loads the help background.
     */
    public void loadHelpBG() {
        background(helpImage);
    }

    /**
     * Draws an enemy image on the view.
     *
     * @return The enemy image.
     */
    public PImage drawEnemy() {
        return enemyImage;
    }

    /**
     * Draws a boss image on the view.
     *
     * @return The boss image.
     */
    public PImage drawBoss() {
        return bossImage;
    }

    /**
     * Draws a player image on the view.
     *
     * @return The player image.
     */
    public PImage drawPlayer() {
        return playerImage;
    }

    /**
     * Draws an asteroid image on the view.
     *
     * @return The asteroid image.
     */
    public PImage drawAsteroid() {
        return asteroidImage;
    }

    /**
     * Draws an explosion image on the view.
     *
     * @return The explosion image.
     */
    public PImage drawExplosion() {
        return explosionImage;
    }

    /**
     * Draws a player laser image on the view.
     *
     * @return The player laser image.
     */
    public PImage drawPlayerLaser() {
        return playerLaserImage;
    }

    /**
     * Draws an enemy laser image on the view.
     *
     * @return The enemy laser image.
     */
    public PImage drawEnemyLaser() {
        return enemyLaserImage;
    }

    /**
     * Fills the view with a given color.
     *
     * @param f The color to fill.
     */
    public void viewFill(int f) {
        fill(255);
    }

    /**
     * Sets the text size for drawing.
     *
     * @param t The text size.
     */
    public void gettextSize(int t) {
        textSize(t);
    }

    /**
     * Draws the HP value on the view.
     *
     * @param hp   The HP value to draw.
     * @param posx The x-coordinate for drawing.
     * @param posy The y-coordinate for drawing.
     */
    public void getDrawHP(int hp, int posx, int posy) {
        text(hp, posx, posy);
    }

    /**
     * Aligns text to the center.
     */
    public void textAlignCenter() {
        textAlign(CENTER);
    }

    /**
     * Aligns text to the left.
     */
    public void textAlignLeft() {
        textAlign(LEFT);
    }

    /**
     * Aligns text to the right.
     */
    public void textAlignRight() {
        textAlign(RIGHT);
    }

    /**
     * Draws text on the view.
     *
     * @param s The text to draw.
     * @param i The x-coordinate for drawing.
     * @param j The y-coordinate for drawing.
     */
    public void drawText(String s, int i, int j) {
        text(s, i, j);
    }
}
