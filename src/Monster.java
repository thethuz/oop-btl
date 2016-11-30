import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
public class Monster extends Character implements Common{
  private static final int SPEED = 4;
  public static  final double PROB_MOVE = 0.02;

  private static BufferedImage image;
  private int id;
  private int health=100;
  private int damage=1;
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
  private boolean isAttacking=false;
  private boolean isAttackable=true;
  private int moveType;
  private String message;

  private int attackType=1;
  // thread for monster animation
  private Thread threadPlayer;
  //private Thread threadAttack;
  // reference to Map
  private Map map;
  //Tọa độ x, tọa độ y, vị trí trong image/monster.gif, hướng, kiểu di chuyển(0 đứng yên, 1 tự động), map
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
      // threadAttack = new Thread(new AttackThread());
      // threadAttack.start();
      threadPlayer = new Thread(new PlayerThread());
      threadPlayer.start();
  }

  public boolean move(){
    switch (direction) {
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
    if (!map.isHit(nextX, nextY)) {
        px -= Monster.SPEED;
        if (px < 0) px = 0;
        moveLength += Monster.SPEED;
        if (moveLength >= CS) {
            // pixel-based scrolling is completed
            // hero moves to left tile
            x--;
            px = x * CS;
            isMoving = false;
            return true;
        }
    } else {
        isMoving = false;
        px = x * CS;
        py = y * CS;
    }
    return false;
  }

  public boolean moveRight(){
    int nextX = x + 1;
    int nextY = y;
    if (nextX > map.getCol() - 1) nextX = map.getCol() - 1;
    if (!map.isHit(nextX, nextY)) {
        px += Monster.SPEED;
        if (px > map.getWidth() - CS)
            px = map.getWidth() - CS;
        moveLength += Monster.SPEED;
        if (moveLength >= CS) {
            x++;
            px = x * CS;
            isMoving = false;
            return true;
        }
    } else {
        isMoving = false;
        px = x * CS;
        py = y * CS;
    }
    return false;
  }

  public boolean moveUp(){
    int nextX = x;
    int nextY = y - 1;
    if (nextY < 0) nextY = 0;
    if (!map.isHit(nextX, nextY)) {
        py -= Monster.SPEED;
        if (py < 0) py = 0;
        moveLength += Monster.SPEED;
        if (moveLength >= CS) {
            y--;
            py = y * CS;
            isMoving = false;
            return true;
        }
    } else {
        isMoving = false;
        px = x * CS;
        py = y * CS;
    }
    return false;
  }

  public boolean moveDown(){
    int nextX = x;
    int nextY = y + 1;
    if (nextY > map.getRow() - 1) nextY = map.getRow() - 1;
    if (!map.isHit(nextX, nextY)) {
        py += Monster.SPEED;
        if (py > map.getHeight() - CS)
            py = map.getHeight() - CS;
        moveLength += Monster.SPEED;
        if (moveLength >= CS) {
            y++;
            py = y * CS;
            isMoving = false;
            return true;
        }
    } else {
        isMoving = false;
        px = x * CS;
        py = y * CS;
    }
    return false;
  }

  public boolean attack(){
    if(isAttackable){
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
      Human h = map.checkHuman(nextX,nextY);
      if (h!=null){
        h.setHealth(h.getHealth()-damage);
        isAttackable=false;
        setAttacking(true);
        return true;
      }
      setAttacking(false);
      return false;
    }
      //Sẽ trả về false <=> h==null&&isAttackable=true || isAttackable=false?
    return true;
  }

  public boolean isMonsterAttacking(){
    return isAttacking;
  }
  public void setAttacking(boolean x){
    this.isAttacking=x;
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
  public void loadImage(String filename) {
      try {
          image = ImageIO.read(getClass().getResource(filename));
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  public int getAttackType(){
    return attackType;
  }
  private class AttackThread extends Thread{
    public void run(){
      while (true){
        if(isAttackable==false)
        {
          // isAttackable=false;
          try{
            Thread.sleep(0);
          }catch (InterruptedException e) {
              e.printStackTrace();
          }
          isAttackable=true;
        }

      }
    }
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
              if(isAttackable==false)
              {
                // isAttackable=false;
                try{
                  Thread.sleep(500);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isAttackable=true;
              }
          }
      }
  }
  public boolean isAttackable(){
    return isAttackable;
  }
  public void setIsAttackable(boolean x){
    this.isAttackable=x;
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
  public int getDirection() {
      return direction;
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
    System.out.println(health);
    if(health>0){
      this.health=health;
    } else dead();
  }
  public void dead(){
    map.removeMonster(this);
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
