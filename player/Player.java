package game.player;

//import data.player.*;
//import game.player.Controller;
//import graphic.player.*;
import game.Character;
import game.map.Map;
import game.map.MapItem;
import java.io.Serializable;

public class Player extends Character implements Serializable{
  private int targetX=0;
  private int targetY=0;
  public Player(){
    setName("Player");
    //loadPlayer();
  }
  //Controller playerController=new Controller();
  //Hàm di chuyển bằng các phím mũi tên
  
  public void move(String control, Map map){
    if (control=="up") {
      if (map.getMapItem(this.getPositionX(),this.getPositionY()+1).isMoveable())
      setPositionY(this.getPositionY()+1 );
      targetX=0;
      targetY=1;
    }
    if (control=="down") {
      if (map.getMapItem(this.getPositionX(),this.getPositionY()-1).isMoveable())
      setPositionY(this.getPositionY()-1);
      targetX=0;
      targetY=-1;
    }
    if (control=="right") {
      if (map.getMapItem(this.getPositionX()+1,this.getPositionY()).isMoveable())
      setPositionX(this.getPositionX()+1);
      targetX=1;
      targetY=0;
    }
    if (control=="left") {
      if (map.getMapItem(this.getPositionX()-1,this.getPositionY()).isMoveable())
      setPositionX(this.getPositionX()-1);
      targetX=-1;
      targetY=0;
    }
    //anmation move
  }
  //Tấn công
  public void attack(){
    //B1: Kiểm tra ô target xem có quái ở đó không?
    if(2>=1){//Game.map.getMapItem( this.getPositionX()+targetX, this.getPositionY()+targetY ).getMonsterLocate()!=null){//Nếu ô mục tiêu có quái
      //Game.map.getMapItem (this.getPositionX()+targetX ,this.getPositionY()+targetY).getMonsterLocate().defend(this.damage);
      //Animation
      //sleep 100/attackSpeed
    }else{
    	//
    }
    //Ô target là ô gần người chơi nhất theo phím di chuyển được ấn cuối cùng.
    //B2: Nếu có quái, gọi đến hàm sát thương của quái
    //B3: Kết thúc tấn công
    //delay();
    //animation attack
  }
  //Chết
  //Hồi sinh
  public void reborn(){
	  setHealth(getMaxHealth());
  }
    //Sử dụng kỹ năng
  Skill skill[]=new Skill[4];
  public void useSkill(int skillNumber){

  }
  //Sử dụng đồ
  Item item[]=new Item[4];
  public void useItem(int itemNumber){

  }
}
