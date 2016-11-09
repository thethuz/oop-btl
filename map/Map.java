package game.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import game.monster.Monster;
import game.player.Player;
import java.io.Serializable;

public class Map implements Serializable{
  private String name;
  private int sizeX=100,sizeY=100;
  private MapItem[][] map;

  // public Map(String name){
  //   setName(name);
  //   //read Local File
  //   MapLoader mapLoader=new MapLoader(name);
  // }
  public void setMapItem(int row, int column, MapItem item){

  }
  public void loadMap(String filename) throws IOException{
    List<String> lines=new ArrayList<String>();
    int width = 0;
    int height = 0;
    BufferedReader reader=new BufferedReader(new FileReader(filename));
    while(true){
      String line = reader.readLine();
      if (line == null){
        reader.close();
        break;
      }
      if (!line.startsWith("!")){
        lines.add(line);
        width = Math.max(width, line.length());
      }
    }
    height=lines.size();
    /*
    for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {
				// System.out.println(i + "is i ");
				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}
			}
		}
    */
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
class MapItem implements Serializable{
  Player playerLocate=null;
  Monster monsterLocate=null;
  boolean playerLocateStt;
  boolean monsterLocateStt;
  boolean standable;
  public void setStandable(boolean can){
    standable=can;
  }
  public boolean moveable(int positionX, int positionY){
    if(!playerLocate && !monsterLocate && standable) return true;
    return false;
  }
  public void setPlayerLocateStt(boolean stt){
    playerLocate=stt;
  }
  public void setMonsterLocateStt(boolean stt){
    monsterLocate=stt;
  }
  public boolean getPlayerLocateStt(){
    return playerLocateStt;
  }
  public boolean getMonsterLocateStt(){
    return monsterLocateStt;
  }

  public Player getPlayerLocate(){
    if(getMonsterLocateStt()){
      return playerLocate;
    }else
      return null;
  }
  public Monster getMonsterLocate(){
    if(getMonsterLocateStt()){
      return monsterLocate;
    }else
      return null;
  }
  public void setMonsterLocate(Monster x){
    monsterLocate=x;
    setMonsterLocateStt(true);
  }
  public void setPlayerLocate(Player x){
    playerLocate=x;
    setMonsterLocateStt(true);
  }
}
