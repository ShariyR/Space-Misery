package Model;

import java.util.Random;

/**
 * Represents an asteroid in a space-themed game.
 */
public class Asteroid {
    private int xPosition;
    private int yPosition;
    private int HP;
    private int moveX;
    private int moveY;
    private int size;

    Random random = new Random();       // Random object for generating random values

    /**
     * Constructs an asteroid with the specified initial position, health points, and size.
     *
     * @param xPosition x-coordinate of the asteroid.
     * @param yPosition y-coordinate of the asteroid.
     * @param hp        The health points of the asteroid.
     * @param size      The size of the asteroid.
     */
    public Asteroid(int xPosition, int yPosition, int hp, int size) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        // random movement values for x and y
        moveX = random.nextInt(-5, 6);
        moveX = (moveX == 0) ? 1 : moveX;
        moveY = random.nextInt(-5, 6);
        moveY = (moveY == 0) ? 1 : moveY;

        this.HP = hp;
        this.size = size;
    }

    /**
     * Gets the size of the asteroid.
     *
     * @return The size of the asteroid.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the asteroid.
     *
     * @param size The new size of the asteroid.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets the x-axis movement value of the asteroid.
     *
     * @return The x-axis movement value.
     */
    public int getMoveX() {
        return moveX;
    }

    /**
     * Gets the y-axis movement value of the asteroid.
     *
     * @return The y-axis movement value.
     */
    public int getMoveY() {
        return moveY;
    }

    /**
     * Moves the asteroid based on its current movement values.
     */
    public void moveAsteroids() {
        xPosition += moveX;
        yPosition += moveY;
    }

    /**
     * Gets the x-position of the asteroid.
     *
     * @return The x-position of the asteroid.
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Gets the y-position of the asteroid.
     *
     * @return The y-position of the asteroid.
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Reverses the direction of movement along the x-axis.
     */
    public void changeXDirection() {
        moveX = -moveX;
    }

    /**
     * Reverses the direction of movement along the y-axis.
     */
    public void changeYDirection() {
        moveY = -moveY;
    }

    /**
     * Sets the health points of the asteroid.
     *
     * @param HP The new health points of the asteroid.
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * Gets the health points of the asteroid.
     *
     * @return The health points of the asteroid.
     */
    public int getHP() {
        return HP;
    }
}
