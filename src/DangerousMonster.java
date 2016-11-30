import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class DangerousMonster extends Monster implements Common{
  private int count;
  private Thread threadPlayer;
  private static BufferedImage image;
  private int x,y,px,py,id,moveType;
  private Map map;
  private int vision=2;
  public DangerousMonster(int x, int y, int id, int direction, int moveType, Map map) {
      super(x, y, id, direction, moveType, map);
      // init monster
      this.x = x;
      this.y = y;
      px = x * CS;
      py = y * CS;
      this.id = id;
      setDirection(direction);
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

  public boolean attack(){
    if(isAttackable()) {
    Human m;
    for (int i=-vision;i<=vision;i++)
      for(int j=-vision;j<=vision;j++){
        m = map.checkHuman(getX()+i,getY()+j);
        if(m!=null) {
          //System.out.println(i+";"+j);
          if(j<0) {
            setDirection(UP);
            //System.out.println("UP");
          }
          if(j>0) {
            setDirection(DOWN);
            //System.out.println("DOWN");
          }
          if(i>0) {

            setDirection(RIGHT);
            //System.out.println("RIGHT");
          }
          if(i<0) {
            setDirection(LEFT);
            //System.out.println("LEFT");
          }
          //break;
        }
      }
        int nextX=0;
        int nextY=0;
        switch (getDirection()) {
        case LEFT:
          nextX = getX()-1;
          nextY = getY();
          break;
        case RIGHT:
          nextX = getX()+1;
          nextY = getY();
          break;
        case UP:
          nextX = getX();
          nextY = getY()-1;
          break;
        case DOWN:
          nextX = getX();
          nextY = getY()+1;
          break;
        }

      Human h = map.checkHuman(nextX, nextY);
      //System.out.println(nextX+";"+ nextY);
      if (h!=null){
        h.setHealth(h.getHealth()-getDamage());
        System.out.println("monster is attacking");
        setIsAttackable(false);
        setAttacking(true);
        return true;
      }
      setAttacking(false);
      return false;
    }
    /**/
    setAttacking(false);
    return true;
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
