package Controller;

/**
 * Thread class responsible for controlling enemy movement in the Space Misery game.
 */
public class EnemyMovementThread extends Thread {
    private SMController controller;
    private boolean running;

    /**
     * Constructs an EnemyMovementThread with the specified controller.
     *
     * @param controller The controller associated with the thread.
     */
    public EnemyMovementThread(SMController controller) {
        this.controller = controller;
        this.running = true;
    }


    /**
     * Runs the thread, continuously updating enemy movement in the game.
     */
    @Override
    public void run() {
        while (running) {
            controller.enemyMovement();
            try {
                sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops the thread, terminating the continuous enemy movement updates.
     */
    public void stopThread() {
        running = false;
    }
}
