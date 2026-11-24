package Model;

/**
 * Represents a bullet in a space-themed game.
 */
public class Bullet {
    private int xPosition;
    private int yPosition;
    private int speed;
    private int damage;

    /**
     * Constructs a bullet with the specified initial position, speed, and damage.
     *
     * @param xPosition The x-coordinate of the bullet.
     * @param yPosition The y-coordinate of the bullet.
     * @param speed     The speed of the bullet.
     * @param damage    The damage of the bullet.
     */
    public Bullet(int xPosition, int yPosition, int speed, int damage) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.speed = speed;
        this.damage = damage;
    }

    /**
     * Moves the bullet upward based on its speed.
     */
    public void moveUp() {
        yPosition -= speed;
    }

    /**
     * Moves the bullet downward based on its speed.
     */
    public void moveDown() {
        yPosition += speed;
    }

    /**
     * Gets the x-position of the bullet.
     *
     * @return The x-position of the bullet.
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Gets the y-position of the bullet.
     *
     * @return The y-position of the bullet.
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Gets the damage inflicted by the bullet.
     *
     * @return The damage inflicted by the bullet.
     */
    public int getDamage() {
        return damage;
    }
}
