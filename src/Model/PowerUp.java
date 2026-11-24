package Model;

/**
 * Represents a power-up entity in a space-themed game.
 */
public class PowerUp {

    /**
     * Enumerates the types of power-ups available. Double Damge will increase player's damage and HP_Restoration heals
     * player by 50+. Rebirth Rune gives the player a second chance to complete the mission.
     * If player gets defeated without Rebirth Rune, player needs to restart the level. Double Damage and HP_Restoration
     * Power ups last only till the level is finish. For the next Level player grants default values for damage and HP.
     */
    public enum Type {
        DOUBLE_DAMAGE,    // Power-up that doubles the damage of the player's bullets
        HP_RESTORATION,   // Power-up that restores the player's health points
        REBIRTH           // Power-up that grants the player a rebirth rune
    }

    private Type type;

    /**
     * Constructs a power-up with the specified type.
     *
     * @param type The type of the power-up.
     */
    public PowerUp(Type type) {this.type = type;}

    /**
     * Gets the type of the power-up.
     *
     * @return The type of the power-up.
     */
    public Type getType() {return type;}
}
