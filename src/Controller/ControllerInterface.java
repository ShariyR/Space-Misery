package Controller;

import Model.SMModel;
import processing.event.KeyEvent;

/**
 * The ControllerInterface defines the methods that a controller class
 * implementing game logic should provide for interacting with the model
 * and handling user input.
 */
public interface ControllerInterface {

    /**
     * Sets the model for the controller.
     *
     * @param model The model to be set for the controller.
     */
    void setModel(SMModel model);

    /**
     * Gets the Y position of the player.
     *
     * @return The Y position of the player.
     */
    int getPlayerYPosition();

    /**
     * Gets the X position of the player.
     *
     * @return The X position of the player.
     */
    int getPlayerXPosition();

    /**
     * Gets information about the current level.
     *
     * @return Information about the current level.
     */
    int getCurrentLevelInfo();

    /**
     * Initiates the start of the game.
     */
    void gameStart();

    /**
     * Starts a new game.
     */
    void StartNewGame();

    /**
     * Displays and allows modification of game settings.
     */
    void gameSetting();

    /**
     * Handles key events when a key is pressed.
     *
     * @param event The KeyEvent object representing the key event.
     */
    void keyPressed(KeyEvent event);

    /**
     * Handles power-up goals in the game.
     */
    void powerUpGoal();

    /**
     * Checks for collisions between game elements.
     */
    void checkCollision();

    /**
     * Stops threads related to the game.
     */
    void stopThreads();

    /**
     * Draws explosion graphics.
     */
    void drawExplosion();

    /**
     * Moves bullets in the game.
     */
    void moveBullets();

    /**
     * Checks the status of threads.
     */
    void threadsCheck();

    /**
     * Draws bullets on the screen.
     */
    void drawBullets();

    /**
     * Grants a random power-up to the player.
     */
    void grantRandomPowerUpToPlayer();

    /**
     * Checks for player rebirth conditions.
     */
    void checkRebirth();

    /**
     * Checks for double damage conditions.
     */
    void checkDoubleDamage();

    /**
     * Checks the current game state.
     *
     * @return True if the game is ongoing; false otherwise.
     */
    boolean checkGameState();

    /**
     * Moves asteroids in the game.
     */
    void asteroidsMovement();

    /**
     * Moves enemy entities in the game.
     */
    void enemyMovement();

    /**
     * Draws the player's health on the screen.
     */
    void drawPlayerHealth();

    /**
     * Checks for collisions involving asteroids.
     */
    void asteroidsCheck();

    /**
     * Checks if the player has won against enemy entities.
     *
     * @return True if the player has won; false otherwise.
     */
    boolean checkWinEnemy();

    /**
     * Loads the next game level.
     */
    void loadNextLevel();

    /**
     * Loads a specific level in the game.
     *
     * @param levelNumber The number of the level to be loaded.
     */
    void loadCurrentLevel(int levelNumber);
}
