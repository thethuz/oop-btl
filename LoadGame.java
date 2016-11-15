//package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Loader implements Serializable {
  private int x;
  public void loader(Loader myGame){
    //Game myGame = new Game();
    //LoadGame myGame

    try {
      FileOutputStream f = new FileOutputStream("student.dat");
      ObjectOutputStream oStream = new ObjectOutputStream(f);
      oStream.writeObject(myGame);
      oStream.close();
    } catch (IOException e){
      System.out.println("Error Write file");
    }

    //Game myGame = null;

    try {
      FileInputStream f = new FileInputStream("student.dat");
      ObjectInputStream inStream = new ObjectInputStream(f);
      myGame = (Loader) inStream.readObject();
      inStream.close();
      System.out.println(myGame);
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
    loaderX.loader(loaderX);
  }
}
class MyStudent implements Serializable{
  String name="Nguyen Vu The Thu";
  int age = 21;
  //LoadGame loadcc=new LoadGame();
}
