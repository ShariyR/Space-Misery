public class PlayerJshellModel {


        // Fields to represent player properties
        private int size;           // Size of the player entity
        private int xPosition;      // X-coordinate of the player on the game screen
        private int yPosition;      // Y-coordinate of the player on the game screen
        private int hp;             // Health points of the player
        private int damage;         // Damage dealt by the player's bullets

        /**
         * Constructs a player with the specified initial position, size, health points, and bullet damage.
         *
         * @param xPosition The initial x-coordinate of the player.
         * @param yPosition The initial y-coordinate of the player.
         * @param size      The size of the player.
         * @param hp        The initial health points of the player.
         * @param damage    The damage dealt by the player's bullets.
         */
        public PlayerJshellModel(int xPosition, int yPosition, int size, int hp, int damage) {
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.size = size;
            this.hp = hp;
            this.damage = damage;
        }

        /**
         * Gets the x-coordinate of the player.
         *
         * @return The x-coordinate of the player.
         */
        public int getXPosition() {
            return xPosition;
        }

        /**
         * Sets the x-coordinate of the player.
         *
         * @param xpos The new x-coordinate of the player.
         */
        public void setXPosition(int xpos) {
            xPosition = xpos;
        }

        /**
         * Gets the y-coordinate of the player.
         *
         * @return The y-coordinate of the player.
         */
        public int getYPosition() {
            return yPosition;
        }

        /**
         * Sets the y-coordinate of the player.
         *
         * @param ypos The new y-coordinate of the player.
         */
        public void setyPosition(int ypos) {
            yPosition = ypos;
        }

        /**
         * Gets the health points of the player.
         *
         * @return The health points of the player.
         */
        public int getHP() {
            return hp;
        }

        /**
         * Sets the health points of the player.
         *
         * @param num The new health points of the player.
         */
        public void setHP(int num) {
            hp = num;
        }

        /**
         * Sets the damage dealt by the player's bullets.
         *
         * @param damage The new damage value.
         */
        public void setDamage(int damage) {
            this.damage = damage;
        }

        /**
         * Gets the damage dealt by the player's bullets.
         *
         * @return The damage value.
         */
        public int getDamage() {
            return damage;
        }

        /**
         * Gets the size of the player.
         *
         * @return The size of the player.
         */
        public int getSize() {
            return size;
        }

    @Override
    public String toString() {
        return "PlayerJshellModel{"
                + " xPosition=" + xPosition
                + ", yPosition=" + yPosition
                + ", size=" + size
                + ", hp=" + hp
                + ", damage=" + damage
                + '}';
    }
}



