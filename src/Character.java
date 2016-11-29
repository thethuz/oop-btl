import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public abstract class Character implements Common {
    //private static  int speed = 4;
    //public static  double PROB_MOVE = 0.02;
    private static int health=100;
    private static BufferedImage image;
    private int id;

    // character's position (unit: tile)
    private int x, y;
    // character's position (unit: pixel)
    private int px, py;

    // character's direction (LEFT, RIGHT, UP or DOWN)
    private int direction;
    // character's animation counter
    private int count;

    // character's damage;
    private int damage;
    // character's defence;
    private int defence;
    private int level=1;
    public int getLevel(){
      return level;
    }
    private boolean isMoving;
    private int moveLength;

    private int moveType;
    private String message;

    // thread for character animation
    private Thread threadAnime;

    // reference to Map
    private Map map;

    public abstract void draw(Graphics g, int offsetX, int offsetY) ;
    public abstract boolean move();

    public abstract boolean moveLeft();

    public abstract boolean moveRight();

    public abstract boolean moveUp();

    public abstract boolean moveDown();

    //public abstract Human talkWith();

    public abstract boolean attack();

    //
    public abstract void dead();

    //public abstract TreasureEvent search();

    //public abstract DoorEvent open();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPX() {
        return px;
    }

    public int getPY() {
        return py;
    }

    public void setDirection(int dir) {
        direction = dir;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean flag) {
        isMoving = flag;
        moveLength = 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMoveType() {
        return moveType;
    }

    public void setHealth(int health){
      this.health=health;
    }

    public int getHealth(){
      return health;
    }

    public void setDamage(int damage){
      this.damage=damage;
    }

    public int getDamage(){
      return damage;
    }

    public void setDefence(int defence){
      this.defence=defence;
    }

    public int getDefence(){
      return defence;
    }

    private void loadImage(String filename) {
        try {
            image = ImageIO.read(getClass().getResource(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Animation Class
    private class AnimationThread extends Thread {
        public void run() {
            while (true) {
                if (count == 0) {
                    count = 1;
                } else if (count == 1) {
                    count = 0;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
