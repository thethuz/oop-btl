//package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Loader{
  public void loader(){
    Game myGame = new Game();
    try {
      FileOutputStream f = new FileOutputStream("game.dat");
      ObjectOutputStream oStream = new ObjectOutputStream(f);
      oStream.writeObject(myGame);
      oStream.close();
    } catch (IOException e){
      System.out.println("Error Write file");
    }

    Game myGame = null;

    try {
      FileInputStream f = new FileInputStream("student.dat");
      ObjectInputStream inStream = new ObjectInputStream(f);
      myGame = (Game) inStream.readObject();
      inStream.close();
    } catch (ClassNotFoundException e){
      System.out.println("Class not found");
    } catch (IOException e){
      System.out.println("Error Read file");
    }
     //System.out.println("My name is " + myGame.name + ". I am " + myGame.age + " years old");
     //System.out.println(myGame.loadcc.xxx);
  }
}
public class LoadGame implements Serializable{
  String xxx="xxxxxxx";
  public static void main(String[] args){
    Loader loaderX=new Loader();
    loaderX.loader();
  }
}
class MyStudent implements Serializable{
  String name="Nguyen Vu The Thu";
  int age = 21;
  LoadGame loadcc=new LoadGame();
}
