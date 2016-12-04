import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class FlyingMonster extends Monster implements Common{
  private static final int SPEED = 4;
  public static  final double PROB_MOVE = 0.02;
  private int count;
  private Thread threadPlayer;
  private static BufferedImage image;
  // private int x,y,px,py,id,moveType;
  //
  private boolean isFlying;
  private int moveLength;
  private Map map;
  //private int vision=2;
  public FlyingMonster(int x, int y, int id, int direction, int moveType, Map map) {
      super(x, y, id, direction, moveType, map);
      // init monster
      // this.x = x;
      // this.y = y;
      // px = x * CS;
      // py = y * CS;
      // this.id = id;
      // setDirection(direction);
      // this.moveType = moveType;
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
    int nextX = getX() - 1;
    int nextY = getY();
    if (nextX <= 0) nextX = 0;
    if (!map.isUnflyable(nextX, nextY)) {
        setPX(getPX()- FlyingMonster.SPEED) ;
        if (getPX() < 0) setPX(0);
        moveLength += FlyingMonster.SPEED;
        if (moveLength >= CS) {
            // pixel-based scrolling is completed
            // hero moves to left tile
            setX(getX()-1);
            setPX( getX() * CS);
            setMoving(false);
            moveLength=0;
            return true;
        }
    } else {
        setMoving(false);
        setPX(getX()*CS);
        setPY(getY()*CS);
      }
    return false;
  }

  public boolean moveRight(){
    int nextX =getX()+ 1;
    int nextY = getY();
    if (nextX >= map.getCol() - 1) nextX = map.getCol() - 1;
    if (!map.isUnflyable(nextX, nextY)) {
        setPX(getPX()+ FlyingMonster.SPEED);
        if (getPX() > map.getWidth() - CS)
            setPX(map.getWidth() - CS);
        moveLength += FlyingMonster.SPEED;
        if (moveLength >= CS) {
            setX(getX()+1);
            setPX(getX()* CS);
            setMoving(false);
            moveLength=0;
            return true;
        }
    } else {
        setMoving(false);
        setPX(getX()*CS);
        setPY(getY()*CS);
    }
    return false;
  }

  public boolean moveUp(){
    int nextX =getX();
    int nextY =getY()- 1;
    if (nextY <= 0) nextY = 0;
    if (!map.isUnflyable(nextX, nextY)) {
        setPY(getPY()-FlyingMonster.SPEED);
        if (getPY() < 0) setPY(0);
        moveLength += FlyingMonster.SPEED;
        if (moveLength >= CS) {
            setY(getY()-1);
            setPY(getY()*CS);
            setMoving(false);
            return true;
        }
    } else {
        setMoving(false);
        setPX(getX()*CS);
        setPY(getY()*CS);
    }
    return false;
  }

  public boolean moveDown(){
    int nextX =getX();
    int nextY =getY()+ 1;
    if (nextY >= map.getRow() - 1) nextY = map.getRow() - 1;
    if (!map.isUnflyable(nextX, nextY)) {
        setPY(getPY()+FlyingMonster.SPEED);
        if (getPY() > map.getHeight() - CS)
            setPY(map.getHeight() - CS);
        moveLength += FlyingMonster.SPEED;
        if (moveLength >= CS) {
            setY(getY()+1);
            setPY(getY()*CS);
            setMoving(false);
            return true;
        }
    } else {
        setMoving(false);
        setPX(getX()*CS);
        setPY(getY()*CS);

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
                  Thread.sleep(400);
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
