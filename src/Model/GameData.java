package Model;

/**
 * The GameData class represents the core data components of the Space Misery game.
 * It encapsulates information about the current game state, player, and the details
 * of the current game level.
 */
public class GameData {

    /**
     * Represents the current state of the game (e.g., playing, paused, game over).
     */
    public GameState state;

    /**
     * Represents the player's information and state.
     */
    public Player player;

    /**
     * Represents the details of the current game level.
     */
    public Level level;
}
