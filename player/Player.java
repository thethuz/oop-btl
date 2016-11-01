package game.player;
import data.player.*;
import game.player.Controller;
import graphic.player.*;
import game.Character;
import game.map.Map;
public class Player extends Character{
  public Player(){
    setName("Player");
    loadPlayer();
  }
  Controller playerController=new Controller();
  public void move(String control){
    if (control=="up") {
      if (Map.standable(getPositionX(),getPositionY()++))
      setPositionY(getPositionY()++);
    }
    if (control=="down") {
      if (Map.standable(getPositionX(),getPositionY()--))
      setPositionY(getPositionY()--);
    }
    if (control=="right") {
      if (Map.standable(getPositionX()++,getPositionY()))
      setPositionX(getPositionX()++);
    }
    if (control=="left") {
      if (Map.standable(getPositionX()--,getPositionY()))
      setPositionX(getPositionX()--);
    }
    //anmation move
  }
  public void loadPlayer(){
    setHealth(PlayerLoader.health());
    setDamage(PlayerLoader.health());
    setDefense(PlayerLoader.health());
    setAttackSpeed(PlayerLoader.health());
    setMoveSpeed(PlayerLoader.health());
    setPositionX
    setPositionY
    setTimeReborn
  }
  public void savePlayer(){

  }
  public void attack(Character monster){
    int healthDecrease= this.getDamage-monster.getDefense();
    if (healthDecrease<0) healthDecrease=0;
    monster.setHealth(monster.getHealth()-healthDecrease);
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
