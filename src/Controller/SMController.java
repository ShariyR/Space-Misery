package Controller;

import Model.*;
import View.ViewInterface;
import processing.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Controller class for Space Misery.
 */
public class SMController implements ControllerInterface{
    private int explosionFrameCounter = 0;              //explosionImage countdown
    private final int explosionDuration = 8;            //explosionImage duration
    int PlayerMovementSpeed = 5;                        //Player Movement Speed
    private int enemyMove = 1;                          //Enemy Movement Speed
    private ViewInterface view;                         //Interface for the view component of the Space Misery.
    private SMModel model;                              //Model representing the data of the Space Misery
    private GameState state;                            //Enum representing the possible states of the Space Misery
    private Player player;                              //Object representing the player in the Space Misery
    private GameData data;                              //Data container for managing different game-related information in Space Misery
    private Enemy enemy;                                //Represents an enemy in the Space Misery
    private Asteroid asteroid;                          //Represents an asteroid in the Space Misery
    private int spaceCooldown = 0;
    private boolean spacePressed;                       //Cooldown counter for controlling the firing rate of the player's bullets
    private EnemyMovementThread enemyMovementThread;    //Thread responsible for managing enemy Movements
    private Level level;                                //Level details and information
    private boolean rebirth = false;                    //Rebirth set to false for the start of the game


    /**
     * Sets the model associated with the controller.
     *
     * @param model The model to be associated with the controller.
     */
    @Override
    public void setModel(SMModel model) {
        this.model = model;
    }


    /**
     * Sets the view associated with the controller.
     *
     * @param view The view to be associated with the controller.
     */
    public void setView(ViewInterface view) {
        this.view = view;
    }

    /**
     * Gets the Y position of the player.
     *
     * @return The Y position of the player.
     */
    public int getPlayerYPosition() {
        return view.getHeight() - 75;
    }

    /**
     * Gets the X position of the player.
     *
     * @return The X position of the player.
     */
    public int getPlayerXPosition() {
        return view.getWidth() / 2 - 25;
    }

    /**
     * Gets information about the current level.
     *
     * @return Information about the current level.
     */
    public int getCurrentLevelInfo() {
        return level.getLevelNumber();
    }


    /**
     * Starts the game by initializing the game data.
     */
    public void gameStart() {
        data = new GameData();
        data.state = state.START;
        level = Level.createLevel1();
        enemyMovementThread = new EnemyMovementThread(this);

    }

    /**
     * Default constructor for the SMController class.
     */
    public SMController() {

        data = new GameData();
        data.state = GameState.START;
    }

    /**
     * Initializes a new game.
     */
    public void StartNewGame() {
        data.player = new Player(getPlayerXPosition(), getPlayerYPosition(), 75, 100,1);
    }


    /**
     * Handles game settings based on the current game state.
     */
    public void gameSetting() {
        switch (data.state) {
            case START -> {
                view.loadMenuBackground();}
            case PLAYING -> {
                view.loadBackground();
                for (Enemy enemy : data.level.getEnemies()) {
                    if(getCurrentLevelInfo() != 7) {
                        view.drawImage(view.drawEnemy(), enemy.getXPosition(), enemy.getYPosition(), enemy.getSize(), enemy.getSize());
                    }else{
                        view.drawImage(view.drawBoss(), enemy.getXPosition(), enemy.getYPosition(), enemy.getSize() + 10, enemy.getSize() +10);
                    }
                }
                var player = data.player;
                view.drawImage(view.drawPlayer(), player.getXPosition(), player.getyPosition(), player.getSize(), player.getSize());
                if (data.level.getAstroids() != null) {
                    for (Asteroid asteroid : data.level.getAstroids()) {
                        view.drawImage(view.drawAsteroid(), asteroid.getXPosition(), asteroid.getYPosition(), asteroid.getSize(), asteroid.getSize());
                    }
                }

                threadsCheck();
                drawPlayerHealth();
                checkGameState();
                drawBullets();
                moveBullets();
                data.level.update();
                moveEnemyBullets();
                asteroidsMovement();
                asteroidsCheck();
                checkWinEnemy();
                checkCollision();
                drawEnemyHP();
                powerUpGoal();
                checkRebirth();
                checkDoubleDamage();
                drawExplosion();
                drawLevelInfo();

            }
            case GAMEOVER -> {
                view.loadGameOverBG();
            }
            case NEXTLEVEL -> {
                view.loadNextLevelBG();}
            case VICTORY -> {
                view.loadVictoryBG();
            }
            case HELP -> {
                view.loadHelpBG();
            }
            default -> throw new IllegalStateException("Unexpected value: " + data.state);
        }
    }

    boolean levelChosen = false;

    /**
     * Handles key presses based on the current game state.
     *
     * @param event The KeyEvent representing the key press.
     */
    public void keyPressed(KeyEvent event) {
        switch (data.state) {
            case START -> {
                if (event.getKey() == '1') {
                    data.state = GameState.PLAYING;
                    StartNewGame();
                    data.level = Level.createLevel1();
                }
                if (event.getKey() == '2') {
                    data.state = GameState.PLAYING;
                    StartNewGame();
                    data.level = Level.createLevel2();
                }
                if (event.getKey() == '3') {
                    data.state = GameState.PLAYING;
                    StartNewGame();
                    data.level = Level.createLevel3();
                }
                if (event.getKey() == '4') {
                    data.state = GameState.PLAYING;
                    StartNewGame();
                    data.level = Level.createLevel4();
                }
                if (event.getKey() == '5') {
                    data.state = GameState.PLAYING;
                    StartNewGame();
                    data.level = Level.createLevel5();

                }
                if (event.getKey() == '6') {
                    data.state = GameState.PLAYING;
                    StartNewGame();
                    data.level = Level.createLevel6();
                }
                if (event.getKey() == '7') {
                    data.state = GameState.PLAYING;
                    StartNewGame();
                    data.level = Level.createLevel7();
                }
                if(event.getKey() == 'i'){
                    data.state = GameState.HELP;
                }else{
                    if(!levelChosen){
                        System.out.println("Please choose a number between 1 - 7 (Numbers) in order to start the Level you wish to play or press i for Help.");
                        levelChosen = true;
                    }
                }
            }

            case PLAYING -> {
                if (event.getKey() == 'k' && spaceCooldown == 0) {
                    spacePressed = true;
                    spaceCooldown = 15;
                }
                var p = data.player;
                checkFrameLimit();
                //Movement
                if (event.getKey() == 'w' || event.getKey() == 'W') {
                    p.setyPosition(p.getyPosition() - PlayerMovementSpeed);  // Move up
                }
                if (event.getKey() == 's' || event.getKey() == 'S') {
                    p.setyPosition(p.getyPosition() + PlayerMovementSpeed);  // Move down
                }
                if (event.getKey() == 'd' || event.getKey() == 'D') {
                    p.setXPosition(p.getXPosition() + PlayerMovementSpeed);  // Move right
                }
                if (event.getKey() == 'a' || event.getKey() == 'A') {
                    p.setXPosition(p.getXPosition() - PlayerMovementSpeed);  // Move left
                }
                // Handle shooting
                if (event.getKey() == 'k' || event.getKey() == 'K') {
                    data.player.shoot();
                }
            }
            case GAMEOVER -> {
                doubleDamage = false;
                if(rebirth){
                    if (event.getKey() == 'r' || event.getKey() == 'R') {
                        rebirth = false;
                        data.state = GameState.PLAYING;
                        StartNewGame();
                    }
                }
                if (event.getKey() == 't' || event.getKey() == 'T') {
                    data.state = GameState.PLAYING;
                    StartNewGame();
                    loadCurrentLevel(data.level.getLevelNumber());
                }
                if (event.getKey() == 'q' || event.getKey() == 'Q') {
                    rebirth = false;
                    StartNewGame();
                    data.state = GameState.START;
                }
            }

            case NEXTLEVEL -> {
                doubleDamage = false;
                if (event.getKey() == 'q' || event.getKey() == 'Q') {
                    data.state = GameState.START;
                }
                if(event.getKey() == 'n' || event.getKey() == 'N'){
                    loadNextLevel();
                }
            }
            case VICTORY, HELP -> {
                if (event.getKey() == 'q' || event.getKey() == 'Q') {
                    data.state = GameState.START;
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + data.state);
        }
    }

    /**
     * Draws the current level information on the screen.
     */
    private void drawLevelInfo(){
        view.viewFill(255);
        view.gettextSize(35);
        view.textAlignRight();
        view.drawText("Level: " + getCurrentLevelInfo(), view.getWidth() - 20, view.getHeight() - 20);

    }

    /**
     * Checks if the player has achieved the required kill count for a power-up in the current level,
     * and grants a random power-up to the player if conditions are met.
     */
    public void powerUpGoal(){
        int currentLevel = level.getLevelNumber();
        switch(currentLevel){
            case 1 -> {
                if(data.level.getKillCount() == 2){
                    grantRandomPowerUpToPlayer();
                    data.level.setKill(0) ;
                }
            }
            case 2, 4, 5, 6 -> {
                if(data.level.getKillCount() == 3){
                    grantRandomPowerUpToPlayer();
                    data.level.setKill(0) ;
                }
            }
            default -> {

            }
        }
    }

    /**
     * Checks for collisions between the player and enemies, enemy bullets, and asteroids,
     * updating player HP and game state accordingly.
     */
    public void checkCollision() {
        if (data.player != null && data.level != null) {
            int playerRadius = data.player.getSize() / 2;
            // Check collision with each enemy in the current level
            for (Enemy enemy : data.level.getEnemies()) {
                int enemyRadius = enemy.getSize()/2;
                float distance = view.checkDistance(
                        data.player.getXPosition() + playerRadius,
                        data.player.getyPosition() + playerRadius,
                        enemy.getXPosition() + enemyRadius,
                        enemy.getYPosition() + enemyRadius
                );

                if (distance < playerRadius + enemyRadius) {
                    // Player collided with an enemy, reduce player HP
                    data.player.setHP(0);
                    // Check if player's HP is less than or equal to 0
                    checkGameState();
                }


                // Check collision with enemy bullets
                Iterator<Bullet> bulletIterator = enemy.getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    float bulletX = bullet.getXPosition();
                    float bulletY = bullet.getYPosition();
                    float playerX = data.player.getXPosition() + playerRadius;
                    float playerY = data.player.getyPosition() + playerRadius;
                    float collisionDistance = 50; // collision distance

                    if (view.checkDistance(bulletX, bulletY, playerX, playerY) < collisionDistance) {
                        // Player is hit by an enemy bullet, reduce player HP
                        data.player.setHP(data.player.getHP() - 25);

                        // Remove the bullet from the enemy's bullets
                        bulletIterator.remove();
                        explosionFrameCounter = explosionDuration;

                        // Check if player's HP is less than or equal to 0
                        checkGameState();
                    }
                }
            }


            for (Asteroid asteroid : data.level.getAstroids()) {
                int asteroidRadius = asteroid.getSize() / 2;
                int distanceAsteroid = view.checkDistance(
                        data.player.getXPosition() + playerRadius,
                        data.player.getyPosition() + playerRadius,
                        asteroid.getXPosition() + asteroidRadius,
                        asteroid.getYPosition() + asteroidRadius
                );

                if (distanceAsteroid < playerRadius + asteroidRadius) {
                    // Player collided with an asteroid, reduce player HP by 1
                    data.player.setHP(data.player.getHP() - 1);
                    checkGameState();
                    explosionFrameCounter = explosionDuration;
                    drawExplosion();

                }

                float collisionDistance = asteroid.getSize();
                List<Bullet> bulletsToRemove = new ArrayList<>();

                for (Bullet bullet : data.player.getBullets()) {
                    if (view.checkDistance(bullet.getXPosition(), bullet.getYPosition(), asteroid.getXPosition(), asteroid.getYPosition()) < collisionDistance) {
                        asteroid.setHP(asteroid.getHP() - data.player.getDamage());
                        bulletsToRemove.add(bullet);
                    }
                }
                data.player.getBullets().removeAll(bulletsToRemove);
            }
            for (Enemy enemy : data.level.getEnemies()) {
                int enemyRadius = enemy.getSize() / 2;

                // Create a new iterator for the player's bullets for each enemy
                Iterator<Bullet> bulletIterator = data.player.getBullets().iterator();

                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    float bulletX = bullet.getXPosition();
                    float bulletY = bullet.getYPosition();

                    float enemyX = enemy.getXPosition() + enemyRadius;
                    float enemyY = enemy.getYPosition() + enemyRadius;
                    float collisionDistance = 50;

                    if (view.checkDistance(bulletX, bulletY, enemyX, enemyY) < collisionDistance) {
                        enemy.setHP(enemy.getHP() - data.player.getDamage());

                        bulletIterator.remove();
                    }
                }
            }
        }
    }

    /**
     * Checks the status of the enemy movement thread and starts it if necessary.
     * Stops the thread if the game state is not playing or if the thread is already alive.
     */
    public void threadsCheck(){
        if (data.state == GameState.PLAYING && (enemyMovementThread == null || !enemyMovementThread.isAlive())) {
            // Start the thread only if it hasn't been started or is not alive
            enemyMovementThread = new EnemyMovementThread(this);
            enemyMovementThread.start();
        } else {
            stopThreads();
        }
    }

    /**
     * Stops the enemy movement thread if it is running.
     */
    public void stopThreads() {
        if (enemyMovementThread != null) {
            enemyMovementThread.stopThread();
        }
    }

    /**
     * Draws an explosion effect on the screen if the explosion frame counter is greater than 0.
     */
    public void drawExplosion() {
        if (explosionFrameCounter > 0) {
            view.drawImage(view.drawExplosion(), data.player.getXPosition() - 50, data.player.getyPosition() - 75, 150, 150);
            explosionFrameCounter--;
        }
    }

    /**
     * Moves player bullets upward and enemy bullets downward. Removes bullets that go off-screen.
     */
    public void moveBullets() {
        Iterator<Bullet> iterator = data.player.getBullets().iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.moveUp(); // Move player bullets upward

            // Remove bullets that go off-screen
            if (bullet.getYPosition() < 0) {
                iterator.remove();
            }
        }

        for (Enemy enemy : data.level.getEnemies()) {
            for (Bullet bullet : enemy.getBullets()) {
                bullet.moveDown(); // Move enemy bullets downward
            }
        }
    }



    /**
     * Draws player bullets and enemy bullets on the view.
     */
    public void drawBullets() {
        for (Bullet bullet : data.player.getBullets()) {
            view.drawImage(view.drawPlayerLaser(),bullet.getXPosition() - 25, bullet.getYPosition() -25, 50, 50);
        }

        for (Enemy enemy : data.level.getEnemies()) {
            for (Bullet bullet : enemy.getBullets()) {
                view.drawImage(view.drawEnemyLaser(),bullet.getXPosition() - 25, bullet.getYPosition() -25, 85, 70);
            }
        }
    }

    boolean doubleDamage = false;
    Random random = new Random();

    /**
     * Grants a random power-up to the player based on predefined power-up types.
     * Handles logic for double damage, HP restoration, and rebirth power-ups.
     * Double Damage and HP restoration will reset after each level.
     */
    public void grantRandomPowerUpToPlayer() {
        int randomNumber = random.nextInt(3) ;
        PowerUp.Type[] powerUpTypes = PowerUp.Type.values();
        PowerUp.Type randomType = powerUpTypes[randomNumber];

        switch (randomType) {
            case DOUBLE_DAMAGE:
                doubleDamage = true;
                data.player.setDamage(data.player.getDamage()*2);
                System.out.println("POWER UP: Double Damage! you're now dealing "+ data.player.getDamage() + " damage to the enemies!");
                break;

            case HP_RESTORATION:
                data.player.setHP(data.player.getHP() + 50);
                System.out.println("POWER UP: +50 HP");
                break;

            case REBIRTH:
                if(!rebirth){rebirth = true;
                    System.out.println("POWER UP: Rebirth granted!");}
                break;
        }
    }

    /**
     * Checks if the rebirth power-up is available and displays a message on the view.
     */
    public void checkRebirth(){
        if (rebirth){
            view.viewFill(255);
            view.gettextSize(20);
            view.textAlignCenter();
            view.drawText("Rebirth available", view.getWidth()/2, view.getHeight() - 20);
        }
    }

    /**
     * Checks if the double damage power-up is active and displays a message on the view.
     */
    public void checkDoubleDamage(){
        if(doubleDamage) {
            view.viewFill(255);
            view.gettextSize(15);
            view.textAlignLeft();
            view.drawText("POWER UP: Double Damage (" + data.player.getDamage() + ")", 5, view.getHeight() - 55);
        }
    }

    /**
     * Draws the health points (HP) of enemies and asteroids on the view.
     */
    private void drawEnemyHP(){
        for(Enemy enemy: data.level.getEnemies()){
            view.viewFill(255);
            view.gettextSize(15);
            view.textAlignCenter();
            view.getDrawHP((int)enemy.getHP(), enemy.getXPosition() + enemy.getSize()/2, enemy.getYPosition() - 20);
        }

        for (Asteroid asteroid : data.level.getAstroids()){
            view.viewFill(255);
            view.gettextSize(15);
            view.textAlignCenter();
            view.getDrawHP((int)asteroid.getHP(), asteroid.getXPosition() + asteroid.getSize()/2, asteroid.getYPosition() - 20);
        }
    }

    /**
     * moves Enemy Bullets from Enemy's Position (bullets will be removed after going off the screen).
     */
    private void moveEnemyBullets() {
        for (Enemy enemy : data.level.getEnemies()) {
            Iterator<Bullet> iterator = enemy.getBullets().iterator();
            while (iterator.hasNext()) {
                Bullet bullet = iterator.next();
                bullet.moveDown();

                // Remove bullets that go off screen
                if (bullet.getYPosition() > view.getHeight()) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Preventing Player from leaving the game frame
     */
    public void checkFrameLimit() {

        if (data.player != null) {
            int leftBoundary = 0;
            int rightBoundary = view.getWidth() - 75;
            int topBoundary = 25;
            int bottomBoundary = view.getHeight() - 75;

            if (data.player.getXPosition() < leftBoundary) {
                data.player.setXPosition(leftBoundary);
            } else if (data.player.getXPosition() > rightBoundary) {
                data.player.setXPosition(rightBoundary);
            } else if (data.player.getyPosition() < topBoundary) {
                data.player.setyPosition(topBoundary);
            } else if (data.player.getyPosition() > bottomBoundary) {
                data.player.setyPosition(bottomBoundary);
            }
        }

    }

    /**
     * Moving Asteroids around in the frame and keeping them in the game frame
     */
    public void asteroidsMovement() {
        if (data.level.getAstroids() != null) {
            for (Asteroid asteroid : data.level.getAstroids()) {
                // Check X boundaries (Frame)
                if (asteroid.getXPosition() < 0 || asteroid.getXPosition() > view.getWidth() - asteroid.getSize()) {
                    asteroid.changeXDirection();
                }

                // Check Y boundaries (Frame)
                if (asteroid.getYPosition() < 0 || asteroid.getYPosition() > view.getHeight() - asteroid.getSize()) {
                    asteroid.changeYDirection();
                }

            }
        }
    }

    /**
     * Moving enemies and keeping them in the game frame
     */
    public void enemyMovement() {
        for (Enemy enemy : data.level.getEnemies()) {
            enemy.setXPosition(enemy.getXPosition() + enemyMove);
            if (enemy.getXPosition() > view.getWidth() - enemy.getSize() || enemy.getXPosition() < 0) {
                enemyMove = -enemyMove;
            }
        }
    }

    /**
     * draw player's Health Point
     */
    public void drawPlayerHealth() {
        if (data.player != null) {
            int playerHP = data.player.getHP();
            view.viewFill(255);
            view.gettextSize(35);
            view.textAlignLeft();
            view.drawText("HP: " + playerHP, 5, view.getHeight() - 20);
        }
    }

    /**
     * Checks the game state by verifying if the player's health points are greater than zero.
     * If the player's health points are zero or less, sets the game state to GAMEOVER.
     *
     * @return True if the player's health points are greater than zero, false otherwise.
     */
    public boolean checkGameState() {
        if (data.player.getHP() > 0) {
            return true;
        } else {
            data.state = GameState.GAMEOVER;
            return false;
        }
    }

    /**
     * check whether the Asteroid still exists. If it's HP falls bellow 1 it will get destroyed
     */
    public void asteroidsCheck() {
        Iterator<Asteroid> iterator = data.level.getAstroids().iterator();

        while (iterator.hasNext()) {
            Asteroid asteroid = iterator.next();
            if (asteroid.getHP() <= 0) {
                iterator.remove();
            }
        }
    }

    /**
     * check whether all enemies of the Level have been defeated.
     * @return True if all Enemies are defeated. false if they still exist
     */
    public boolean checkWinEnemy() {
        for (Enemy enemy : data.level.getEnemies()) {
            if (enemy != null) {
                return false;
            }
        }
        for(Asteroid asteroid : data.level.getAstroids()){
            if (asteroid != null) {
                return false;
            }
        }

        if(data.level.getLevelNumber() != 7){
            data.state = GameState.NEXTLEVEL;
            return true;}else{
            data.state = GameState.VICTORY;
            return true;
        }
    }

    /**
     * Loads the next level in the game, updating the level based on the current level number.
     * Increments the level number and sets the game state to PLAYING for the new level.
     * If there is no next level available, sets the state to START.
     */
    public void loadNextLevel() {
        int currentLevel = data.level.getLevelNumber();
        int nextLevel = currentLevel + 1;

        // Check if there is a level available with the next level number
        switch (nextLevel) {
            case 2 -> data.level = Level.createLevel2();
            case 3 -> data.level = Level.createLevel3();
            case 4 -> data.level = Level.createLevel4();
            case 5 -> data.level = Level.createLevel5();
            case 6 -> data.level = Level.createLevel6();
            case 7 -> data.level = Level.createLevel7();
            default -> {
                data.state = GameState.START;
                return;
            }
        }

        // Reset the player for the new level
        StartNewGame();
        // Set the game state to PLAYING for the new level
        data.state = GameState.PLAYING;
    }

    /**
     * Loads the specified level in the game based on the provided level number.
     * Sets the game state to START if the level number is not within the valid range.
     *
     * @param levelNumber The level number to load.
     */
    public void loadCurrentLevel(int levelNumber){
        int currentLevel = data.level.getLevelNumber();
        switch (currentLevel) {
            case 1 -> data.level = Level.createLevel1();
            case 2 -> data.level = Level.createLevel2();
            case 3 -> data.level = Level.createLevel3();
            case 4 -> data.level = Level.createLevel4();
            case 5 -> data.level = Level.createLevel5();
            case 6 -> data.level = Level.createLevel6();
            case 7 -> data.level = Level.createLevel7();
            default -> {
                // If there is no next level, set the state to GAMEOVER or handle it accordingly
                data.state = GameState.START;

            }
        }
    }
}







