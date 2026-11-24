public class EnemyJshellModel {


        // Fields to represent the state of the enemy
        private int yPosition;
        private int xPosition;
        private int size;
        private float hp;



        /**
         * Constructs an enemy with the specified initial position, size, and health points.
         *
         * @param xPosition The initial x-coordinate of the enemy.
         * @param yPosition The initial y-coordinate of the enemy.
         * @param size      The size of the enemy.
         * @param hp        The health points of the enemy.
         */
        public EnemyJshellModel(int xPosition, int yPosition, int size, float hp) {
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.size = size;
            this.hp = hp;

        }

        /**
         * Gets the x-position of the enemy.
         *
         * @return The x-position of the enemy.
         */
        public int getXPosition() {
            return xPosition;
        }

        /**
         * Sets the x-position of the enemy.
         *
         * @param xPos The new x-position of the enemy.
         */
        public void setXPosition(int xPos){
            xPosition = xPos;
        }

        /**
         * Gets the y-position of the enemy.
         *
         * @return The y-position of the enemy.
         */
        public int getYPosition() {
            return yPosition;
        }

        /**
         * Gets the size of the enemy.
         *
         * @return The size of the enemy.
         */
        public int getSize() {
            return size;
        }

        /**
         * Sets the size of the enemy.
         *
         * @param size The new size of the enemy.
         */
        public void setSize(int size) {
            this.size = size;
        }


        /**
         * Gets the list of bullets fired by the enemy.
         *
         * @return The list of bullets fired by the enemy.
         */


        /**
         * Reduces the health points of the enemy by the specified amount.
         *
         * @param damage The amount of damage to be inflicted on the enemy.
         */
        public void takeDamage(int damage) {
            hp -= damage;
        }

        /**
         * Gets the health points of the enemy.
         *
         * @return The health points of the enemy.
         */
        public float getHP() {
            return hp;
        }

        /**
         * Sets the health points of the enemy.
         *
         * @param hp The new health points of the enemy.
         */
        public void setHP(float hp) {
            this.hp = hp;
        }

        @Override
        public String toString() {
            return "EnemyJshellModel{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", size=" + size +
                ", hp=" + hp +
                '}';
        }
    }


