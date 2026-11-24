package View;

import processing.core.PImage;

/**
 * The ViewInterface defines the methods that a view class implementing the
 * graphical representation of the game should provide for rendering and
 * displaying visual elements.
 */
public interface ViewInterface {

    /**
     * Configures settings for the graphical environment.
     */
    void settings();

    /**
     * Sets up the initial state of the graphical environment.
     */
    void setup();

    /**
     * Draws the graphical elements on the screen.
     */
    void draw();

    /**
     * Checks the distance between two points.
     *
     * @param x     The X-coordinate of the first point.
     * @param y     The Y-coordinate of the first point.
     * @param j     The X-coordinate of the second point.
     * @param k     The Y-coordinate of the second point.
     * @return      The distance between the two points.
     */
    int checkDistance(float x, float y, float j, float k);

    /**
     * Draws an image on the screen.
     *
     * @param p         The PImage object representing the image.
     * @param x         The X-coordinate of the image.
     * @param y         The Y-coordinate of the image.
     * @param size1     The width of the image.
     * @param size2     The height of the image.
     */
    void drawImage(PImage p, int x, int y, int size1, int size2);

    /**
     * Gets the height of the graphical environment.
     *
     * @return The height of the graphical environment.
     */
    int getHeight();

    /**
     * Gets the width of the graphical environment.
     *
     * @return The width of the graphical environment.
     */
    int getWidth();

    /**
     * Loads the background image for the main menu.
     */
    void loadMenuBackground();

    /**
     * Loads the background image for the game.
     */
    void loadBackground();

    /**
     * Draws the image of an enemy entity.
     *
     * @return The PImage object representing the enemy image.
     */
    PImage drawEnemy();

    /**
     * Draws the image of a boss entity.
     *
     * @return The PImage object representing the boss image.
     */
    PImage drawBoss();

    /**
     * Draws the image of the player entity.
     *
     * @return The PImage object representing the player image.
     */
    PImage drawPlayer();

    /**
     * Draws the image of an asteroid.
     *
     * @return The PImage object representing the asteroid image.
     */
    PImage drawAsteroid();

    /**
     * Loads the background image for the game over screen.
     */
    void loadGameOverBG();

    /**
     * Loads the background image for the next level screen.
     */
    void loadNextLevelBG();

    /**
     * Loads the background image for the victory screen.
     */
    void loadVictoryBG();

    /**
     * Loads the background image for the help screen.
     */
    void loadHelpBG();

    /**
     * Draws the image of an explosion.
     *
     * @return The PImage object representing the explosion image.
     */
    PImage drawExplosion();

    /**
     * Draws the image of the player's laser.
     *
     * @return The PImage object representing the player's laser image.
     */
    PImage drawPlayerLaser();

    /**
     * Draws the image of the enemy's laser.
     *
     * @return The PImage object representing the enemy's laser image.
     */
    PImage drawEnemyLaser();

    /**
     * Sets the fill color for shapes drawn on the screen.
     *
     * @param f The fill color to be set.
     */
    void viewFill(int f);

    /**
     * Sets the size of the text to be drawn.
     *
     * @param t The size of the text.
     */
    void gettextSize(int t);

    /**
     * Draws the player's health on the screen.
     *
     * @param hp    The health points of the player.
     * @param posx  The X-coordinate for drawing the health.
     * @param posy  The Y-coordinate for drawing the health.
     */
    void getDrawHP(int hp, int posx, int posy);

    /**
     * Aligns text to the center.
     */
    void textAlignCenter();

    /**
     * Aligns text to the left.
     */
    void textAlignLeft();

    /**
     * Aligns text to the right.
     */
    void textAlignRight();

    /**
     * Draws text on the screen.
     *
     * @param s The text to be drawn.
     * @param i The X-coordinate for drawing the text.
     * @param j The Y-coordinate for drawing the text.
     */
    void drawText(String s, int i, int j);
}
