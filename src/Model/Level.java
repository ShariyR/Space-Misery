package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Represents a level in a space-themed game, including player bullets, enemies, and asteroids.
 */
public class Level {
    /**
     * Represents the bullets fired by the player in the game level.
     */
    private List<Bullet> playerBullets;

    /**
     * Represents the enemies present in the game level.
     */
    private List<Enemy> enemies;

    /**
     * Represents the asteroids present in the game level.
     */
    private List<Asteroid> asteroids;

    /**
     * Represents the initial X-coordinate of the player's starting position.
     */
    private int playerStartX;

    /**
     * Represents the initial Y-coordinate of the player's starting position.
     */
    private int playerStartY;

    /**
     * Represents the time interval between enemy attacks.
     */
    public float attackInterval;

    /**
     * Represents the timestamp of the last enemy attack.
     */
    public long lastAttackTime;

    /**
     * Represents the kill count for tracking defeated enemies.
     */
    private int kill = 0;


    /**
     * Constructs a level with the specified initial player starting positions, enemies, and attack interval.
     *
     * @param playerStartX   The initial x-coordinate for the player.
     * @param playerStartY   The initial y-coordinate for the player.
     * @param enemies        The list of enemies in the level.
     * @param attackInterval The time interval between enemy attacks (in seconds).
     */
    public Level(int playerStartX, int playerStartY, List<Enemy> enemies, float attackInterval) {
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.enemies = enemies;
        this.attackInterval = attackInterval;
        this.lastAttackTime = System.currentTimeMillis();  // start with current time
        this.playerBullets = new ArrayList<>();
        this.asteroids = new ArrayList<>();
    }

    /**
     * Constructs a level with the specified initial player starting positions and asteroids.
     *
     * @param playerStartX The initial x-coordinate for the player.
     * @param playerStartY The initial y-coordinate for the player.
     * @param asteroids     The list of asteroids in the level.
     */
    public Level(int playerStartX, int playerStartY, List<Asteroid> asteroids) {
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.asteroids = asteroids;
        this.enemies = new ArrayList<>();
        this.playerBullets = new ArrayList<>();
    }

    /**
     * Updates the state of the level, including enemy attacks, enemy removal, asteroid movement, and collision checks.
     */
    public void update() {
        long currentTime = System.currentTimeMillis();

        // Check if the attack interval has passed since the last attack
        if ((currentTime - lastAttackTime) >= (attackInterval * 1000)) { // Convert seconds to milliseconds
            // Trigger enemy attack
            triggerRandomEnemyAttack();

            // Update the last attack time
            lastAttackTime = currentTime;
        }

        // Remove defeated enemies
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (enemy.getHP() <= 0) {
                iterator.remove(); // Remove the enemy from the list
                enemyGotKilled();
                System.out.println("Enemy killed!");
            }
        }

        // Move asteroids
        for (Asteroid asteroid : asteroids) {
            asteroid.moveAsteroids();
        }

        // Check for collisions between bullets and enemies
        checkCollisions();
    }

    /**
     * Gets the level number.
     * Shared among all instances to keep track of the current level.
     *
     */
    public static int levelNumber = 0;

    /**
     * Gets the level number.
     *
     * @return The level number.
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * Checks for collisions between bullets and enemies, applying damage and removing entities if necessary.
     */
    private void checkCollisions() {
        // Iterate through player bullets
        Iterator<Bullet> playerBulletIterator = playerBullets.iterator();
        while (playerBulletIterator.hasNext()) {
            Bullet playerBullet = playerBulletIterator.next();

            // Iterate through enemies
            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();

                // Check for collision
                if (collisionCheck(playerBullet, enemy)) {
                    // Apply damage to the enemy
                    enemy.takeDamage(playerBullet.getDamage());

                    // Remove the bullet from the list
                    playerBulletIterator.remove();

                    // Remove the enemy if its hp is zero or less
                    if (enemy.getHP() <= 0) {
                        enemyIterator.remove();
                    }
                }
            }
        }
    }

    /**
     * Gets the list of enemies in the level.
     *
     * @return The list of enemies in the level.
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Gets the list of asteroids in the level.
     *
     * @return The list of asteroids in the level.
     */
    public List<Asteroid> getAstroids() {
        return asteroids;
    }

    /**
     * Triggers a random enemy to perform an attack.
     */
    private void triggerRandomEnemyAttack() {
        Random random = new Random();
        if (!enemies.isEmpty()) {
            int randomIndex = random.nextInt(enemies.size());
            Enemy randomEnemy = enemies.get(randomIndex);
            randomEnemy.shoot();
        }
    }

    /**
     * Creates and returns the configuration for Level 1.
     * Made static for easy invocation without the need for class instantiation.
     *
     * @return The Level 1 configuration.
     */
    public static Level createLevel1() {
        levelNumber = 1;
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(100, 75, 70, 50));
        enemies.add(new Enemy(200, 75, 70, 50));
        enemies.add(new Enemy(300, 75, 70, 50));
        enemies.add(new Enemy(400, 75, 70, 50));
        return new Level(100, 200, enemies, 2);
    }

    /**
     * Creates and returns the configuration for Level 2.
     * Made static for easy invocation without the need for class instantiation.
     *
     * @return The Level 2 configuration.
     */
    public static Level createLevel2() {
        levelNumber = 2;
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(50, 50, 70, 60));
        enemies.add(new Enemy(200, 50, 70, 60));
        enemies.add(new Enemy(350, 50, 70, 60));
        enemies.add(new Enemy(150, 150, 70, 60));
        enemies.add(new Enemy(250, 150, 70, 60));
        return new Level(50, 100, enemies, (float) 1.75);
    }

    /**
     * Creates and returns the configuration for Level 3.
     * Made static for easy invocation without the need for class instantiation.
     *
     * @return The Level 3 configuration.
     */
    public static Level createLevel3() {
        levelNumber = 3;
        List<Asteroid> asteroids = new ArrayList<>();
        asteroids.add(new Asteroid(250, 100, 50, 75));
        asteroids.add(new Asteroid(50, 222, 50, 150));
        asteroids.add(new Asteroid(600, 333, 50, 50));
        asteroids.add(new Asteroid(500, 460, 50, 50));
        return new Level(75, 100, asteroids);
    }

    /**
     * Creates and returns the configuration for Level 4.
     * Made static for easy invocation without the need for class instantiation.
     *
     * @return The Level 4 configuration.
     */
    public static Level createLevel4() {
        levelNumber = 4;
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(50, 50, 70, 60));
        enemies.add(new Enemy(200, 50, 70, 60));
        enemies.add(new Enemy(350, 50, 70, 60));
        enemies.add(new Enemy(150, 200, 70, 60));
        enemies.add(new Enemy(250, 200, 70, 60));
        enemies.add(new Enemy(50, 150, 70, 60));
        enemies.add(new Enemy(350, 150, 70, 60));
        return new Level(50, 100, enemies, (float) 1.25);
    }

    /**
     * Creates and returns the configuration for Level 5.
     * Made static for easy invocation without the need for class instantiation.
     *
     * @return The Level 5 configuration.
     */
    public static Level createLevel5() {
        levelNumber = 5;
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(50, 50, 70, 75));
        enemies.add(new Enemy(250, 50, 70, 75));
        enemies.add(new Enemy(450, 50, 70, 75));
        enemies.add(new Enemy(150, 125, 70, 75));
        enemies.add(new Enemy(350, 125, 70, 75));
        enemies.add(new Enemy(250, 200, 70, 75));
        enemies.add(new Enemy(400, 300, 70, 75));
        enemies.add(new Enemy(100, 300, 70, 75));
        enemies.add(new Enemy(250, 300, 70, 75));
        return new Level(50, 100, enemies, (float) 1);
    }

    /**
     * Creates and returns the configuration for Level 6.
     * Made static for easy invocation without the need for class instantiation.
     *
     * @return The Level 6 configuration.
     */
    public static Level createLevel6() {
        levelNumber = 6;
        List<Asteroid> asteroids = new ArrayList<>();
        asteroids.add(new Asteroid(250, 100, 75, 75));
        asteroids.add(new Asteroid(50, 222, 75, 120));
        asteroids.add(new Asteroid(600, 333, 75, 62));
        asteroids.add(new Asteroid(500, 460, 75, 50));
        asteroids.add(new Asteroid(180, 175, 75, 35));
        return new Level(75, 100, asteroids);
    }

    /**
     * Creates and returns the configuration for Level 7.
     * Made static for easy invocation without the need for class instantiation.
     *
     * @return The Level 7 configuration.
     */
    public static Level createLevel7() {
        levelNumber = 7;
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(50, 50, 70, 200));
        return new Level(50, 100, enemies, (float) 0.5);
    }

    /**
     * Increments the kill count when an enemy is defeated.
     */
    public void enemyGotKilled() {
        kill++;
    }

    /**
     * Gets the current kill count.
     *
     * @return The current kill count.
     */
    public int getKillCount() {
        return kill;
    }

    /**
     * Sets the kill count to the specified value.
     *
     * @param num The new kill count.
     */
    public void setKill(int num) {
        kill = num;
    }

    /**
     * Checks for a collision between a bullet and an enemy.
     *
     * @param bullet The bullet to check for collision.
     * @param enemy  The enemy to check for collision.
     * @return True if a collision occurred, false otherwise.
     */
    private boolean collisionCheck(Bullet bullet, Enemy enemy) {
        return (bullet.getXPosition() >= enemy.getXPosition() &&
                bullet.getXPosition() <= enemy.getXPosition() + enemy.getSize() &&
                bullet.getYPosition() >= enemy.getYPosition() &&
                bullet.getYPosition() <= enemy.getYPosition() + enemy.getSize());
    }
}
