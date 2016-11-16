import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class MapGeneration{
    public void generate(){
      private Map map0=new Map();
      private Map map1=new Map();
      map0.name="map0";
      map0.map=new MapItem[100][100];

      map1.name="map1";
      map1.map=new MapItem[100][100];
      for(int i=0;i<100;i++)
        for(int j=0;j<100;j++){
          map0.map[i][j]=new MapItem();
          map1.map[i][j]=new MapItem();
        }
      try {
        FileOutputStream f0= new FileOutputStream("Map0.dat");
        ObjectOutputStream outStream = new ObjectOutputStream(f0);
        outStream.writeObject(map0);
        outStream.clode();
        FileOutputStream f1= new FileOutputStream("Map1.dat");
        ObjectOutputStream outStream = new ObjectOutputStream(f1);
        outStream.writeObject(map1);
        outStream.clode();
      }catch (IOException e){
        System.out.println("Error write file");
      }
      System.out.println("generation success!");
  }
}
