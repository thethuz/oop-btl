package game.scene;
import game.player.Player;
import game.map.Map;
import game.monster.Monster;
import game.data.scene.*;
import java.util.Random;
//import

public class Scene{
  Map map;
  Monster[] monster;
  SceneItem scene =new SceneItem[100][100];
  int numbMonster;
  Random random=new Random();
  //Player player;
  public Scene(){
    int numbMonster=random.nextInt(101)+100;
    monster=new Monster[numbMonster];
    arrangeMonster();
  }
  public void arrangeMonster(){
    int count=10000/numbMonster;
    for (int i=0;i<numbMonster;i++){
      int x=count*i;
      for(int j=0;j<count;j++){
        int randPos=random.getInt(count)+x;
        if (Map.standable[randPos%100][randPos/100]){
          monster[i].setPositionX(randPos%100);
          monster[i].setPositionY(randPos/100);
          break;
        }
        if(j==count--) monster[i]=null;
      }
      if((monster[i]!=null)&&(monster[i].getPositionX()==player.getPositionX())&&(monster[i].getPositionY()==player.getPositionY()))
      monster[i]=null;
    }
  }

  public void loadScene(){
    SceneLoader sceneLoader=new SceneLoader();
  }
  public void saveScene(){

  }
  public void setPlayer(){
    Player player=new Player();
  }

  public void setGroupMonster(){
    while()
  }
}
class SceneItem{
  int objectID;
}
