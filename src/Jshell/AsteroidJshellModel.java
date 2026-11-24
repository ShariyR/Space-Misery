import java.util.Random;

public class AsteroidJshellModel {




    private int xPosition;
    private int yPosition;
    private int HP;

    private int moveX;
    private int moveY;

    private int size;


    Random random = new Random();


    public AsteroidJshellModel(int xPosition, int yPosition, int hp, int size) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.HP = hp;

        moveX = random.nextInt(-5, 6);
        if (moveX == 0) {
            moveX = 1;
        }

        moveY = random.nextInt(-5, 6);

        if (moveY == 0) {
            moveY = 1;
        }
        int randomSize = random.nextInt(50, 125);
        this.size = size;


    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMoveX() {
        return moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public void moveAsteroids() {

        xPosition += moveX;
        yPosition += moveY;

    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void changeXDirection() {
        moveX = -moveX;
    }

    public void changeYDirection() {

        moveY = -moveY;
    }

    public void setHP(int HP) {
        this.HP = HP;

    }

    public int getHP() {
        return HP;
    }

    @Override
    public String toString() {
        return "AsteroidJshellModel{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", HP=" + HP +
                ", moveX=" + moveX +
                ", moveY=" + moveY +
                ", size=" + size +
                '}';
    }
}






