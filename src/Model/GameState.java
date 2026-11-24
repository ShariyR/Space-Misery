package Model;

/**
 * Enumeration representing the possible states of the game.
 * The states include: START, PLAYING, GAMEOVER, NEXTLEVEL, VICTORY, and HELP.
 */
public enum GameState {
    START,       // Represents the initial state of the game.
    PLAYING,     // Represents the state when the game is actively being played.
    GAMEOVER,    // Represents the state when the game is over.
    NEXTLEVEL,   // Represents the state when transitioning to the next level.
    VICTORY,     // Represents the state when the player achieves victory.
    HELP         // Represents the state when the player is seeking help or instructions (Controls and Power ups).
}
