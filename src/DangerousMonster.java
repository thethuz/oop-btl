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
  // private int vision=2;
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
    /*if(isAttackable()){
    Human m;
    for (int i=-vision;i<=vision;i++)
      for(int j=-vision;j<=vision;j++){
        m = map.checkHuman(this.getX()+i,this.getY()+j);
        if(m!=null) {
          if((i==-2 && j==-2) || (i==-1 && j<0)|| (i==0 && j<0) || (i==1 && j<0) || (i==2 && j==-2)) setDirection(LEFT);
          else if((i==-2 && j==+2) || (i==-1 && j>0) || (i==0 && j>0) || (i==1 && j>0) || (i==2 && j==2)) setDirection(RIGHT);
          else if(i<0) setDirection(UP);
          else if(i>0) setDirection(DOWN);
          break;
        }
      }
    }/**/
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
