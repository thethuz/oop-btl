package game.player;

//import data.player.*;
import game.player.Controller;
//import graphic.player.*;
import game.Character;
import game.map.Map;
import java.io.Serializable;

public class Player extends Character{
  private int targetX=0;
  private int targetY=0;
  public Player(){
    setName("Player");
    loadPlayer();
  }
  Controller playerController=new Controller();
  public void move(String control){
    if (control=="up") {
      if (Map.standable(getPositionX(),getPositionY()++))
      setPositionY(getPositionY()++);
      targetX=0;
      targetY=1;
    }
    if (control=="down") {
      if (Map.standable(getPositionX(),getPositionY()--))
      setPositionY(getPositionY()--);
      targetX=0;
      targetY=-1;
    }
    if (control=="right") {
      if (Map.standable(getPositionX()++,getPositionY()))
      setPositionX(getPositionX()++);
      targetX=1;
      targetY=0;
    }
    if (control=="left") {
      if (Map.standable(getPositionX()--,getPositionY()))
      setPositionX(getPositionX()--);
      targetX=-1;
      targetY=0;
    }
    //anmation move
  }
  public void loadPlayer(){

  }
  public void savePlayer(){

  }
  public void attack(){
    if()
    int healthDecrease= this.getDamage-monster.getDefense();
    if (healthDecrease<0) healthDecrease=0;
    monster.setHealth(monster.getHealth()-healthDecrease);
    monster.defense(this);
    //animation attack
  }
  public void dead(){
    if(health==0){
      //animation Dead
      reborn();
    }
  }
  public void reborn(){
    if (health==0){

    }
  }
}
