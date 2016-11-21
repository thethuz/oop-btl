import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
public class Monster extends Character implements Common{
  private static final int SPEED = 4;
  public static  final double PROB_MOVE = 0.02;

  private static BufferedImage image;
  private int id;
  private int health;
  private int damage;
  private int defence;
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
  public void draw(Graphics g, int offsetX, int offsetY) {
      int cx = (id % 8) * (CS * 2);
      int cy = (id / 8) * (CS * 4);
      // switch image based on animation counter
      g.drawImage(image,
                  px - offsetX,
                  py - offsetY,
                  px - offsetX + CS,
                  py - offsetY + CS,
                  cx + count * CS,
                  cy + direction * CS,
                  cx + CS + count * CS,
                  cy + direction * CS + CS,
                  null);
  }
  private void loadImage(String filename) {
      try {
          image = ImageIO.read(getClass().getResource(filename));
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

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
}
