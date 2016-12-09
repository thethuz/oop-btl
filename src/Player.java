import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class Player extends Human implements Common{

  private Map map;

  public Player(int x, int y, int id, int direction, int moveType, Map map,String name) {

      super(x,y,id,direction,moveType,map,name);
      this.map = map;
  }

}
