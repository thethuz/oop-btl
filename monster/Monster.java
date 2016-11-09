package game.monster;

//import data.monster.*;
import game.monster.Controller;
//import graphic.monster.*;
import game.Character;
import java.util.Random;
import game.map.Map;
import java.io.Serializable;

public class Monster extends Character implements Serializable{
  private int id;
  private int vision;
  public Monster(){
    Random random=new Random();
    id=random.nextInt(6);

  }
  //Controller monsterController=new Controller();
  public boolean findPlayer(){
    return false;
  }

  public void huntPlayer(){
    move();
  }
  public int getVision(){
    return vision;
  }
  public void move(){
      //random from 0 to 3
      Random random=new Random();
      int move=random.nextInt(5);
      //up
      switch (move) {
        case 0://up
        if (Map.standable(getPositionX(),getPositionY()++))
        setPositionY(getPositionY()++);
        break;
        case 1://down
        if (Map.standable(getPositionX(),getPositionY()--))
        setPositionY(getPositionY()--);
        break;
        case 2://right
        if (Map.standable(getPositionX()++,getPositionY()))
        setPositionX(getPositionX()++);
        break;
        case 3://left
        if (Map.standable(getPositionX()--,getPositionY()))
        setPositionX(getPositionX()--);
        default:
        //do nothing
      }
    }
    //animation move
  // public boolean isPlayerNextTo(){
  //
  // }
  public void attack(Character player){
    int healthDecrease= this.getDamage-player.getDefense();
    if (healthDecrease<0) healthDecrease=0;
    player.setHealth(player.getHealth()-healthDecrease);
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
      //delay 100;
      //health=fullHealth;
      //
    }
  }
}
