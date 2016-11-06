package game.map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map{
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
class MapItem{
  boolean playerLocate=false;
  boolean monsterLocate=false;
  boolean moveable;
  public void setMoveable(boolean can){
    moveable=can;
  }
  public boolean moveable(int positionX, int positionY){
    if(!playerLocate && !monsterLocate && moveable) return true;
    return false;
  }
  public void setPlayerLocateStt(boolean stt){
    playerLocate=stt;
  }
  public void setMonsterLocateStt(boolean stt){
    monsterLocate=stt;
  }
  public boolean getPlayerLocate(){
    return playerLocate;
  }
  public boolean getMonsterLocate(){
    return playerLocate;
  }
}
