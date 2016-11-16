package game.map;

//import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileInputStream;

import game.monster.Monster;
import game.player.Player;

public class Map implements Serializable{
  private String name;
  private int sizeX=100,sizeY=100;
  private MapItem[][] map;

  public Map(){
  }
  public void setMapItem(int row, int column, MapItem item){

  }

  public MapItem getMapItem(int row, int column){
    return map[row][column];
  }
  public void setName(String name){
    this.name=name;
  }
  public String getName(){
    return name;
  }
  public void setSizeX(int sizeX){
    this.sizeX=sizeX;
  }
  public int getSizeX(){
    return sizeX;
  }
  public void setSizeY(int sizeY){
    this.sizeY=sizeY;
  }
  public int getSizeY(){
    return sizeY;
  }
}

/**
*
*
*
**/
