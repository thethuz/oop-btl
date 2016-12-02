import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class FlyingMonster extends Monster implements Common{
  private static final int SPEED = 6;
  public static  final double PROB_MOVE = 0.02;
  private int count;
  private Thread threadPlayer;
  private static BufferedImage image;
  private int x,y,px,py,id,moveType;
  //
  private int moveLength;
  private Map map;
  //private int vision=2;
  public FlyingMonster(int x, int y, int id, int direction, int moveType, Map map) {
      super(x, y, id, direction, moveType, map);
      // init monster
      this.x = x;
      this.y = y;
      px = x * CS;
      py = y * CS;
      this.id = id;
      setDirection(getDirection());
      this.moveType = moveType;
      this.map = map;

      count = 0;

      if (image == null) {
          loadImage("image/monster.gif");
      }
      threadPlayer = new Thread(new PlayerThread());
      threadPlayer.start();
  }

  public boolean move(){
    switch (getDirection()) {
    case LEFT:
        if (moveLeft()) {
            // return true if pixel-based scrolling is completed.
            return true;
        }
        break;
    case RIGHT:
        if (moveRight()) {
            return true;
        }
        break;
    case UP:
        if (moveUp()) {
            return true;
        }
        break;
    case DOWN:
        if (moveDown()) {
            return true;
        }
        break;
    }
    return false;
  }

  public boolean moveLeft(){
    int nextX = x - 1;
    int nextY = y;
    if (nextX < 0) nextX = 0;
    if (!map.isUnflyable(nextX, nextY)) {
        px -= FlyingMonster.SPEED;
        if (px < 0) px = 0;
        moveLength += FlyingMonster.SPEED;
        if (moveLength >= CS) {
            // pixel-based scrolling is completed
            // hero moves to left tile
            x--;
            px = x * CS;
            setMoving(false);
            return true;
        }
    } else {
        setMoving(false);
        px = x * CS;
        py = y * CS;
    }
    return false;
  }

  public boolean moveRight(){
    int nextX = x + 1;
    int nextY = y;
    if (nextX > map.getCol() - 1) nextX = map.getCol() - 1;
    if (!map.isUnflyable(nextX, nextY)) {
        px += FlyingMonster.SPEED;
        if (px > map.getWidth() - CS)
            px = map.getWidth() - CS;
        moveLength += FlyingMonster.SPEED;
        if (moveLength >= CS) {
            x++;
            px = x * CS;
            setMoving(false);
            return true;
        }
    } else {
        setMoving(false);
        px = x * CS;
        py = y * CS;
    }
    return false;
  }

  public boolean moveUp(){
    int nextX = x;
    int nextY = y - 1;
    if (nextY < 0) nextY = 0;
    if (!map.isUnflyable(nextX, nextY)) {
        py -= FlyingMonster.SPEED;
        if (py < 0) py = 0;
        moveLength += FlyingMonster.SPEED;
        if (moveLength >= CS) {
            y--;
            py = y * CS;
            setMoving(false);
            return true;
        }
    } else {
        setMoving(false);
        px = x * CS;
        py = y * CS;
    }
    return false;
  }

  public boolean moveDown(){
    int nextX = x;
    int nextY = y + 1;
    if (nextY > map.getRow() - 1) nextY = map.getRow() - 1;
    if (!map.isUnflyable(nextX, nextY)) {
        py += FlyingMonster.SPEED;
        if (py > map.getHeight() - CS)
            py = map.getHeight() - CS;
        moveLength += FlyingMonster.SPEED;
        if (moveLength >= CS) {
            y++;
            py = y * CS;
            setMoving(false);
            return true;
        }
    } else {
        setMoving(false);
        px = x * CS;
        py = y * CS;
    }
    return false;
  }


  private class PlayerThread extends Thread {
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
              if(isAttackable()==false)
              {
                // isAttackable=false;
                try{
                  Thread.sleep(500);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setIsAttackable(true);
              }
          }
      }
  }
}
