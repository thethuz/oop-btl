package game.player;

//import data.player.*;
import game.player.Controller;
//import graphic.player.*;
import game.Character;
//import game.map.Map;
import java.io.Serializable;

public class Player extends Character implements Serializable{
  private int targetX=0;
  private int targetY=0;
  public Player(){
    setName("Player");
    loadPlayer();
  }
  Controller playerController=new Controller();
  //Hàm di chuyển bằng các phím mũi tên
  public void move(String control, Map map){
    if (control=="up") {
      if (map.getMapItem(this.getPositionX(),this.getPositionY()++).isMoveable())
      setPositionY(this.getPositionY()++);
      targetX=0;
      targetY=1;
    }
    if (control=="down") {
      if (Game.map.getMapItem(this.getPositionX(),this.getPositionY()--).isMoveable())
      setPositionY(this.getPositionY()--);
      targetX=0;
      targetY=-1;
    }
    if (control=="right") {
      if (Game.map.getMapItem(this.getPositionX()++,this.getPositionY()).isMoveable())
      setPositionX(this.getPositionX()++);
      targetX=1;
      targetY=0;
    }
    if (control=="left") {
      if (Game.map.getMapItem(this.getPositionX()--,this.getPositionY()).isMoveable())
      setPositionX(this.getPositionX()--);
      targetX=-1;
      targetY=0;
    }
    //anmation move
  }
  //Tấn công
  public void attack(){
    //B1: Kiểm tra ô target xem có quái ở đó không?
    if(Game.map.getMapItem( this.getPositionX()+targetX, this.getPositionY()+targetY ).getMonsterLocate()!=null){//Nếu ô mục tiêu có quái
      Game.map.getMapItem (this.getPositionX()+targetX ,this.getPositionY()+targetY).getMonsterLocate().defend(this.damage);
      //Animation
      //sleep 100/attackSpeed
    }else{

    }
    //Ô target là ô gần người chơi nhất theo phím di chuyển được ấn cuối cùng.
    //B2: Nếu có quái, gọi đến hàm sát thương của quái
    //B3: Kết thúc tấn công
    //delay();
    //animation attack
  }
  //Chết
  public void dead(){
    if(health==0){
      //animation Dead
      reborn();
    }
  }
  //Hồi sinh
  public void reborn(){
    if (health==0){

    }
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
