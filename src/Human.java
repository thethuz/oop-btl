import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class Human extends Character implements Common {
    private static final int SPEED = 4;
    private static  final double PROB_MOVE = 0.02;
    private boolean isDead=false;
    private static BufferedImage image;
    private static BufferedImage image1;
    private int id;
    private int level=0;
    private int exp=0;
    private int [] levelExp={100,200,300,400,500};
    private int damage=10;
    private int [] damageLvl={10,20,30,40,50};
    //private int [] defenceLvl={1,2,3,4,5};
    private int [] maxHealth={100,150,200,250,300};
    //private int damage=2;

    // human's position (unit: tile)
    private int x, y;
    // human's position (unit: pixel)
    private int px, py;
    //private int health;
    // human's direction (LEFT, RIGHT, UP or DOWN)
    private int direction;
    private int attackDirection=0;
    // human's animation counter
    private int count;
    private int health;
    private String name;
    //
    private static boolean suitUp =false;
    private boolean isMoving;
    private int moveLength;
//    private int level;
    private int moveType;
    private String message;
    private boolean isAttackable=true;
    // thread for human animation
    private Thread threadPlayer;
    private Thread threadAttack;
    // reference to Map
    private Map map;
    private Item item;
    public Item getItem(){
      return item;
    }
    public void setItem(Item item){
      this.item=item;
    }

    public Human(int x, int y, int id, int direction, int moveType, Map map,String name) {
        // init human
        this.x = x;
        this.y = y;
        px = x * CS;//px=x*32
        py = y * CS;//py=y*32
        this.id = id;
        this.direction = direction;
        this.moveType = moveType;
        this.map = map;
        this.name=name;
        health=100;
        count = 0;

        if (image == null) {
          System.out.println("Load");
            loadImage("image/human-extends.gif");
            loadImage("image/trangphuc1.png");

        }

        // run thread
        // threadAttack = new Thread(new AttackThread());
        // threadAttack.start();
        threadPlayer = new Thread(new PlayerThread());
        threadPlayer.start();
    }
    public void levelUp(){
      if(level<4 && exp>=levelExp[level]){
        exp-=levelExp[level];
        setLevel(level+1);
        setHealth(maxHealth[level]);
        setDamage(damageLvl[level]);
      }
    }

    public void draw(Graphics g, int offsetX, int offsetY) {
        int cx = (id % 8) * (CS * 2);
        int cy = (id / 8) * (CS * 4);
        // switch image based on animation counter
        //
        if (!suitUp) {
            g.drawImage(image,
                    px - offsetX,
                    py - offsetY,
                    px - offsetX + CS,
                    py - offsetY + CS,
                    cx + count * CS,
                    cy + direction * CS +attackDirection*CS,
                    cx + CS + count * CS,
                    cy + direction * CS + CS+attackDirection*CS,
                    null);
        } else if (suitUp && name.equals("hero")) {
            g.drawImage(image1,
                    px - offsetX,
                    py - offsetY,
                    px - offsetX + CS,
                    py - offsetY + CS,
                    cx + count * CS,
                    cy + direction * CS+attackDirection*CS,
                    cx + CS + count * CS,
                    cy + direction * CS + CS+attackDirection*CS,
                    null);
        } else if (suitUp==true && name.equals("hero") == false) {
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
    }

    public void printInfomation(Graphics g) {
        Font font = new Font("SansSerif", Font.BOLD, 16);
            g.setFont(font);
            g.setColor(Color.YELLOW);
            g.drawString(map.getMapName() + " (" + map.getCol() + "," + map.getRow() + ")", 4, 16);
            g.drawString("(" + getX() + "," + getY() + ") ", 4, 32);
            g.drawString("(" + getPX() + "," + getPY() + ")", 4, 48);
            g.drawString("Health: " + getHealth() + "/" + getMaxHealth(), 4, 64);
            g.drawString("Damage: " + getDamage(), 4, 80);
            g.drawString("Exp: " + getExp() + " Level: " + getLevel(), 4, 96);
            g.drawString(map.getBgmName(), 4, 112);
            if(item!=null){
              item.draw(g);
            }
    }

    public boolean move() {
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
    }/**/

    public boolean moveLeft() {
        int nextX = x - 1;
        int nextY = y;
        if (nextX < 0) nextX = 0;
        if (!map.isHit(nextX, nextY)) {

            px -= Human.SPEED;
            if (px < 0) px = 0;
            moveLength += Human.SPEED;
            if (moveLength >= CS) {
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

    public boolean moveRight() {
        int nextX = x + 1;
        int nextY = y;
        if (nextX > map.getCol() - 1) nextX = map.getCol() - 1;
        if (!map.isHit(nextX, nextY)) {
            px += Human.SPEED;
            if (px > map.getWidth() - CS)
                px = map.getWidth() - CS;
            moveLength += Human.SPEED;
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

    public boolean moveUp() {
        int nextX = x;
        int nextY = y - 1;
        if (nextY < 0) nextY = 0;
        if (!map.isHit(nextX, nextY)) {
            py -= Human.SPEED;
            if (py < 0) py = 0;
            moveLength += Human.SPEED;
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

    public boolean moveDown() {
        int nextX = x;
        int nextY = y + 1;
        if (nextY > map.getRow() - 1) nextY = map.getRow() - 1;
        if (!map.isHit(nextX, nextY)) {
            py += Human.SPEED;
            if (py > map.getHeight() - CS)
                py = map.getHeight() - CS;
            moveLength += Human.SPEED;
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

    // return the human which is in front of this human
    public Human talkWith() {
        int nextX = 0;
        int nextY = 0;
        switch (direction) {
        case LEFT:
            nextX = x - 1;
            nextY = y;
            break;
        case RIGHT:
            nextX = x + 1;
            nextY = y;
            break;
        case UP:
            nextX = x;
            nextY = y - 1;
            break;
        case DOWN:
            nextX = x;
            nextY = y + 1;
            break;
        }

        // is there a human?
        Human c = map.checkHuman(nextX, nextY);
        // a player and a human are opposed mutually.
        if (c != null) {
            switch (direction) {
            case LEFT:
                c.setDirection(RIGHT);
                break;
            case RIGHT:
                c.setDirection(LEFT);
                break;
            case UP:
                c.setDirection(DOWN);
                break;
            case DOWN:
                c.setDirection(UP);
                break;
            }
        }
        return c;
    }

    public boolean attack(){
      if (isAttackable){
        int nextX = 0;
        int nextY = 0;
        switch (direction) {
          case LEFT:
              nextX = x - 1;
              nextY = y;
              break;
          case RIGHT:
              nextX = x + 1;
              nextY = y;
              break;
          case UP:
              nextX = x;
              nextY = y - 1;
              break;
          case DOWN:
              nextX = x;
              nextY = y + 1;
              break;
        }
        //is there any monster??
        Monster m = map.checkMonster(nextX, nextY);
        if (m != null){
          if(m.getHealth()-this.getDamage()<=0) {
            setExp(getExp()+50*m.getLevel());
            levelUp();
          }
          m.setHealth(m.getHealth()-this.getDamage());
          attackDirection=4;
          //for(int i=400000000;i>0;i--){
          //}
        }
        isAttackable=false;
      }
      return false;
    }

    public int getDirection(){
      return direction;
    }
    public void setExp(int exp){
      this.exp=exp;
    }
    public int getExp(){
      return exp;
    }
    public void dead(){
      isDead=true;
      //System.out.println("dead");
    }
    public boolean isDead(){
      return isDead;
    }
    public TreasureEvent search() {
        Event event = map.checkEvent(x, y);
        if (event instanceof TreasureEvent) {

            return (TreasureEvent)event;
        }
        return null;
    }

    public DoorEvent open() {
        int nextX = 0;
        int nextY = 0;
        switch (direction) {
        case LEFT:
            nextX = x - 1;
            nextY = y;
            break;
        case RIGHT:
            nextX = x + 1;
            nextY = y;
            break;
        case UP:
            nextX = x;
            nextY = y - 1;
            break;
        case DOWN:
            nextX = x;
            nextY = y + 1;
            break;
        }
        Event event = map.checkEvent(nextX, nextY);
        if (event instanceof DoorEvent) {
            return (DoorEvent)event;
        }
        return null;
    }
    public int getHealth(){
      return health;
    }
    public int getMaxHealth(){
      return maxHealth[level];
    }
    public void setHealth(int health){
      if(health>0){
        this.health=health;
      }else dead();
    }
    // public int getExp(){
    //   return exp;
    // }
    public int getLevel(){
      return level;
    }
    public void setLevel(int level){
      this.level=level;
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
    public int getDamage(){
      return damage;
    }
    public void setDamage(int damage){
      this.damage=damage;
      // this.damage=damage;
    }
    public int getDamageByLevel(){
      return damageLvl[level];
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

    public void loadImage(String filename) {
      try {
          if (filename.equals("image/human-extends.gif")) {
              image = ImageIO.read(getClass().getResource(filename));
          } else if (filename.equals("image/trangphuc1.png")){
              image1 = ImageIO.read(getClass().getResource(filename));
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
    public void suitUp(boolean flag){
      this.suitUp=flag;
    }
    public double getProbMove(){
      return PROB_MOVE;
    }

    // Animation Class
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
                  attackDirection=0;
                  isAttackable=true;
                }
            }
        }
    }
}
