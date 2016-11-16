package game;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GameApplet extends Applet implements Runnable, KeyListener{
  // private Player player;//=new Player();
  // private Monster[] monster;
  // private DangerousMonster[] dangerousMonster;
  // private Graphics second;
  // private URL base;
  // private List<Tile> tilearray = new ArrayList<Tile>();
  // private static Background bg1, bg2;
  @Override
  public void init(){
  //Game
  setSize(800,480);
  setBackground(Color.BLACK);
  setFocusable(true);
  addKeyListener(this);

  Frame frame = (Frame) this.getParent().getParent();
  frame.setTitle("Game");
  //
  try {
    base = getDocumentBase();
  } catch (Exception e) {
    // TODO: handle exception
  }
  //player=new Player();
  monster=new Monster[100];
  dangerousMonster=new DangerousMonster[20];
}

@Override
public void start(){
  bg1 = new Background(0, 0);
  try {

  } catch (IOException e){
    e.printStackTrace();
  }
  Thread thread = new Thread(this);
  thread.start();
}

@Override
public void stop(){
  super.stop();
}

@Override
public void destroy(){

}

@Override
public void run(){
  if (state == GameState.Running) {
    Game mygame=null;
    while (true) {
      /*
      *DO SOMETHING THERE
      */

    }
    if(mygame!=null) mygame.saveGame(Game);
  }
}

@Override
public void update(Graphics g){
  //
}

@Override
public void paint(Graphics g){
  //
}

@Override
public void keyPressed(KeyEvent e){
  switch (e.getKeyCode()){
    case KeyEvent.VK_UP:
    player.move("up");
    break;
    case KeyEvent.VK_DOWN:
    player.move("down");
    break;
    case KeyEvent.VK_RIGHT:
    player.move("right");
    break;
    case KeyEvent.VK_LEFT:
    player.move("left");
    break;
    case KeyEvent.VK_SPACE:

    player.attack();
    break;
  }
}

@Override
public void keyReleased(KeyEvent e){
  //
}

@Override
public void keyTyped(KeyEvent e){
  //
}
}
