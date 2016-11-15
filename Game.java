package game;
//
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import player.*;
import map.*;
import monster.*;

public class Game extends Applet implements Runnable, KeyListener, Serializable{
  enum GameState{
    Running, Dead
  }
  GameState state= GameState.Running;
  private Player player;//=new Player();
  private Monster[] monster;
  private DangerousMonster[] dangerousMonster;
  private Map;
  public Game(){
    Game myGame=null;
    if (loadGame()!=null) myGame=loadGame();
    else {

    }
  }

  //public static Map getMap(){}
  //public static Monster getMonster(){}
  //public static Robot getRobot(){}
  //public void loadMap(String filename){}
  public Game loadGame(){
    Game myGame = null;
    try{
      FileInputStream file = new FileInputStream("game.dat");
      ObjectInputStream inStream = new ObjectInputStream(file);
      myGame =(Game) inStream.readObject();
      inStream.close();

    }catch(ClassNotFoundException e){
      System.out.println("Class not found");
    }catch (IOException e){
      System.out.println("Error Read file");
    }
    return myGame;
  }
  public void saveGame(Game myGame){
    try {
      FileOutputStream f = new FileOutputStream("game.dat");
      ObjectOutputStream oStream = new ObjectOutputStream(f);
      oStream.writeObject(myGame);
      oStream.close();
    }catch (IOException e){
      System.out.println("Error Write file");
    }
  }
}
