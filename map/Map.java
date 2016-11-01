package game.map;
import data.map.*;

public class Map{
  private String name;
  private int sizeX=100,sizeY=100;
  private static int[][] map;

  public Map(String name){
    setName(name,sizeX,sizeY);
    //read Local File
    MapLoader mapLoader=new MapLoader(name);
  }
  public static boolean standable(int positionX, int positionY){
    if(map[positionX][positionY]>=50) return true;
    return false;
  }
  public setMap(){
    map=mapLoader.loadMap();
  }
  public int getMapItem(int row, int column){
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
