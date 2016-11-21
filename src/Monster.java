import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
public class Monster extends Character{
  private static final int SPEED = 4;
  public static  final double PROB_MOVE = 0.02;

  private static BufferedImage image;
  private int id;

  // monster's position (unit: tile)
  private int x, y;
  // monster's position (unit: pixel)
  private int px, py;

  // monster's direction (LEFT, RIGHT, UP or DOWN)
  private int direction;
  // monster's animation counter
  private int count;

  private boolean isMoving;
  private int moveLength;

  private int moveType;
  private String message;

  // thread for monster animation
  private Thread threadAnime;

  // reference to Map
  private Map map;

  public Monster(int x, int y, int id, int direction, int moveType, Map map) {
      // init monster
      this.x = x;
      this.y = y;
      px = x * CS;
      py = y * CS;
      this.id = id;
      this.direction = direction;
      this.moveType = moveType;
      this.map = map;

      count = 0;

      if (image == null) {
          loadImage("image/human.gif");
      }

      // run thread
      threadAnime = new Thread(new AnimationThread());
      threadAnime.start();
  }
  public boolean move(){
    return false;
  }

  public boolean moveLeft(){
    return false;
  }

  public boolean moveRight(){
    return false;
  }

  public boolean moveUp(){
    return false;
  }

  public boolean moveDown(){
    return false;
  }
public boolean attack(){
    int nextX=0;
    int nextY=0;
    switch (direction) {
    case LEFT:
      nextX = x-1;
      nextY = y;
      break;
    case RIGHT:
      nextX = x+1;
      nextY = y;
      break;
    case UP:
      nextX = x;
      nextY = y-1;
      break;
    case DOWN:
      nextX = x;
      nextY = y+1;
      break;
    }
    //có phải human k?
    Human c = map.checkHuman(nextX, nextY);
    if (c!=null){

    }
    return false;
  }
}
