import java.io.*;
/*

*/
public class MapLoader {
  private String fileName="asset/";
  private int[][] arrayInt;
  private int sizeX,sizeY;
  public MapLoader(String name,int sizeX, int sizeY) {
    fileName=fileName+name;
    setSizeX(sizeX);
    setSizeY(sizeY);
    //readFile();
  }
  public getSizeX(){
    return sizeX;
  }
  public setSizeX(int sizeX){
    this.sizeX=sizeX;
  }
  public getSizeY(){
    return sizeY;
  }
  public setSizeY(int sizeY){
    this.sizeY=sizeY;
  }
  public int[][] loadMap()throws IOException{

    arrayInt=new int[sizeX][sizeY];
    return arrayInt;
  }

}
