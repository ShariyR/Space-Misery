import Model.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SpaceMiseryTest {
    @Test
    void asteroid_ShouldMoveAsteroid() {
        Asteroid asteroid = new Asteroid(50, 50, 100, 50);

        int initialX = asteroid.getXPosition();
        int initialY = asteroid.getYPosition();

        asteroid.moveAsteroids();

        assertNotEquals(initialX, asteroid.getXPosition());
        assertNotEquals(initialY, asteroid.getYPosition());

    }

    @Test
    void asteroid_ShouldChangeXDirection() {
        Asteroid asteroid = new Asteroid(50, 50, 100, 50);

        int initialMoveX = asteroid.getMoveX();

        asteroid.changeXDirection();

        assertNotEquals(initialMoveX, asteroid.getMoveX());
        assertEquals(-initialMoveX, asteroid.getMoveX());
    }

    @Test
    void asteroid_ShouldChangeYDirection() {
        Asteroid asteroid = new Asteroid(50, 50, 100, 50);

        int initialMoveY = asteroid.getMoveY();

        asteroid.changeYDirection();

        assertNotEquals(initialMoveY, asteroid.getMoveY());
        assertEquals(-initialMoveY, asteroid.getMoveY());
    }

    @Test
    void asteroid_ShouldSetHP() {
        Asteroid asteroid = new Asteroid(50, 50, 100, 50);

        asteroid.setHP(50);

        assertEquals(50, asteroid.getHP());
    }

    @Test
    void asteroid_ShouldSetSize() {
        Asteroid asteroid = new Asteroid(50, 50, 100, 50);

        asteroid.setSize(50);

        assertEquals(50, asteroid.getSize());
    }

    @Test
    void asteroid_ShouldGetSize() {
        Asteroid asteroid = new Asteroid(50, 50, 100, 50);
        assertEquals(50, asteroid.getSize());
    }

    @Test
    public void testGetSize() {
        // Given
        Asteroid asteroid = new Asteroid(50, 50, 100, 50);

        // When
        int actualSize = asteroid.getSize();

        // Then
        int expectedSize = 50;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void asteroid_ShouldReturnHP() {
        Asteroid asteroid = new Asteroid(50, 50, 100, 50);

        assertEquals(100, asteroid.getHP());
    }

    @Test
    void bullet_ShouldDecreaseYPosition() {
        Bullet bullet = new Bullet(10, 20, 5, 15);

        int initialY = bullet.getYPosition();

        bullet.moveUp();

        assertEquals(initialY - 5, bullet.getYPosition());
    }

    @Test
    void bullet_ShouldIncreaseYPosition() {
        Bullet bullet = new Bullet(10, 20, 5, 15);

        int initialY = bullet.getYPosition();

        bullet.moveDown();

        assertEquals(initialY + 5, bullet.getYPosition());
    }

    @Test
    void bullet_ShouldReturnXPosition() {
        Bullet bullet = new Bullet(10, 20, 5, 15);

        assertEquals(10, bullet.getXPosition());
    }

    @Test
    void bullet_ShouldReturnYPosition() {
        Bullet bullet = new Bullet(10, 20, 5, 15);

        assertEquals(20, bullet.getYPosition());
    }

    @Test
    void bullet_ShouldReturnDamage() {
        Bullet bullet = new Bullet(10, 20, 5, 15);

        assertEquals(15, bullet.getDamage());
    }

    @Test
    void enemy_ShouldReturnInitialXPosition() {
        Enemy enemy = new Enemy(10, 20, 30, 40.0f);
        assertEquals(10, enemy.getXPosition());
    }

    @Test void enemy_ShouldSetXPosition(){
        Enemy enemy = new Enemy(10,20,30,40);
        enemy.setXPosition(30);
        assertEquals(30, enemy.getXPosition());
    }

    @Test
    void enemy_ShouldReturnInitialYPosition() {
        Enemy enemy = new Enemy(10, 20, 30, 40.0f);
        assertEquals(20, enemy.getYPosition());
    }

    @Test
    void enemy_ShouldReturnInitialSize() {
        Enemy enemy = new Enemy(10, 20, 30, 40.0f);
        assertEquals(30, enemy.getSize());
    }

    @Test
    void enemy_ShouldSetSize() {
        Enemy enemy = new Enemy(10, 20, 30, 40.0f);
        enemy.setSize(50);
        assertEquals(50, enemy.getSize());
    }

    @Test
    void enemy_ShouldAddBulletToList() {
        Enemy enemy = new Enemy(10, 20, 30, 40.0f);
        enemy.shoot();
        List<Bullet> bullets = enemy.getBullets();
        assertEquals(1, bullets.size());
    }

    @Test
    void enemy_ShouldReduceHP() {
        Enemy enemy = new Enemy(10, 20, 30, 40.0f);
        enemy.takeDamage(10);
        assertEquals(30.0f, enemy.getHP());
    }

    @Test
    void enemy_ShouldReturnInitialHP() {
        Enemy enemy = new Enemy(10, 20, 30, 40.0f);
        assertEquals(40.0f, enemy.getHP());
    }

    @Test
    void enemy_ShouldSetHP() {
        Enemy enemy = new Enemy(10, 20, 30, 40.0f);
        enemy.setHP(50.0f);
        assertEquals(50.0f, enemy.getHP());
    }

    @Test
    void enemyGotKilled() {
        Level level = new Level(50, 50, new ArrayList<>(), 1);
        assertEquals(0, level.getKillCount());
        level.enemyGotKilled();
        assertEquals(1, level.getKillCount());
    }

    @Test
    void getKillCount() {
        Level level = new Level(50, 50, new ArrayList<>(), 1);
        assertEquals(0, level.getKillCount());
    }

    @Test
    void setKill() {
        Level level = new Level(50, 50, new ArrayList<>(), 1);
        level.setKill(5);
        assertEquals(5, level.getKillCount());
    }

    @Test
    void testUpdate() {
        Level level = new Level(50, 50, new ArrayList<>(), 1);


        long initialLastAttackTime = (long) (System.currentTimeMillis() - level.attackInterval * 1000);
        level.lastAttackTime = initialLastAttackTime;
        level.update();

        assertTrue(level.lastAttackTime >= initialLastAttackTime, "Last attack time should not decrease.");

    }

    @Test
    void getLevelNumber() {
        Level level = new Level(50, 50, new ArrayList<>(), 1);
        assertEquals(0, level.getLevelNumber());
    }

    @Test
    void getEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(50, 50, 70, 60));
        enemies.add(new Enemy(200, 50, 70, 60));
        Level level = new Level(350, 400, enemies, 1);
        assertEquals(enemies, level.getEnemies());
    }

    @Test
    void getAstroids() {
        List<Asteroid> asteroids = new ArrayList<>();
        asteroids.add(new Asteroid(250, 100, 50, 75));
        Level level = new Level(50, 50, asteroids);
        assertEquals(asteroids, level.getAstroids());
    }

    @Test
    void createLevel1() {
        Level level = Level.createLevel1();
        assertEquals(1, level.getLevelNumber());
    }

    @Test
    void createLevel2() {
        Level level = Level.createLevel2();
        assertEquals(2, level.getLevelNumber());
    }

    @Test
    void createLevel3() {
        Level level = Level.createLevel3();
        assertEquals(3, level.getLevelNumber());
    }

    @Test
    void createLevel4() {
        Level level = Level.createLevel4();
        assertEquals(4, level.getLevelNumber());
    }

    @Test
    void createLevel5() {
        Level level = Level.createLevel5();
        assertEquals(5, level.getLevelNumber());
    }

    @Test
    void createLevel6() {
        Level level = Level.createLevel6();
        assertEquals(6, level.getLevelNumber());
    }

    @Test
    void createLevel7() {
        Level level = Level.createLevel7();
        assertEquals(7, level.getLevelNumber());
    }

    @Test
    public void GameState_EnumValues() {
        GameState[] expectedValues = {
                GameState.START,
                GameState.PLAYING,
                GameState.GAMEOVER,
                GameState.NEXTLEVEL,
                GameState.VICTORY,
                GameState.HELP
        };

        GameState[] actualValues = GameState.values();
        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    public void GameState_ToString() {
        GameState gameState = GameState.PLAYING;
        String toStringResult = gameState.toString();
        assertEquals("PLAYING", toStringResult);
    }

    @Test
    public void GameState_ValueOf() {
        String gameStateString = "GAMEOVER";
        GameState gameState = GameState.valueOf(gameStateString);
        assertEquals(GameState.GAMEOVER, gameState);
    }
    @Test
    public void Player_GetXPosition() {
        Player player = new Player(10, 20, 30, 100, 5);
        assertEquals(10, player.getXPosition());
    }

    @Test
    public void Player_SetXPosition() {
        Player player = new Player(10, 20, 30, 100, 5);
        player.setXPosition(15);
        assertEquals(15, player.getXPosition());
    }

    @Test
    public void Player_GetYPosition() {
        Player player = new Player(10, 20, 30, 100, 5);
        assertEquals(20, player.getyPosition());
    }

    @Test
    public void Player_SetYPosition() {
        Player player = new Player(10, 20, 30, 100, 5);
        player.setyPosition(25);
        assertEquals(25, player.getyPosition());
    }

    @Test
    public void Player_GetHP() {
        Player player = new Player(10, 20, 30, 100, 5);
        assertEquals(100, player.getHP());
    }

    @Test
    public void Player_SetHP() {
        Player player = new Player(10, 20, 30, 100, 5);
        player.setHP(80);
        assertEquals(80, player.getHP());
    }

    @Test
    public void Player_Shoot() {
        Player player = new Player(10, 20, 30, 100, 5);
        player.shoot();
        List<Bullet> bullets = player.getBullets();
        assertEquals(1, bullets.size());
    }

    @Test
    public void Player_GetBullets() {
        Player player = new Player(10, 20, 30, 100, 5);
        List<Bullet> bullets = player.getBullets();
        assertNotNull(bullets);
        assertEquals(0, bullets.size());
    }

    @Test
    public void Player_GetDamage() {
        Player player = new Player(10, 20, 30, 100, 5);
        assertEquals(5, player.getDamage());
    }

    @Test
    public void Player_SetDamage() {
        Player player = new Player(10, 20, 30, 100, 5);
        player.setDamage(8);
        assertEquals(8, player.getDamage());
    }

    @Test
    public void Player_GetSize() {
        Player player = new Player(10, 20, 30, 100, 5);
        assertEquals(30, player.getSize());
    }

    @Test
    public void PowerUp_ConstructorAndGetters() {
        PowerUp powerUp = new PowerUp(PowerUp.Type.DOUBLE_DAMAGE);

        assertEquals(PowerUp.Type.DOUBLE_DAMAGE, powerUp.getType());
    }


    @Test
    public void PowerUp_GetType() {
        PowerUp powerUp = new PowerUp(PowerUp.Type.HP_RESTORATION);
        PowerUp.Type type = powerUp.getType();

        assertEquals(PowerUp.Type.HP_RESTORATION, type, "getType() should return the correct type");
    }

}
