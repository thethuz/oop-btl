package game.map;
import game.player.*;
import game.monster.*;
import java.io.Serializable;

public class MapItem implements Serializable{
	  Player playerLocate=null;
	  Monster monsterLocate=null;
	  private int type;
	  
	  //private boolean playerLocateStt;
	  //private boolean monsterLocateStt;
	  private boolean standable;
	  // public void getEffect(int type){
	  //   switch (type) {
	  //     case 0: break;
	  //     case 1: break;
	  //     case 2: break;
	  //     case 3: break;
	  //     case 4: break;
	  //   }
	  // }
	  public void setStandable(boolean can){
	    standable=can;
	  }
	  public boolean getStandable(){
	    return standable;
	  }
	  public boolean isMoveable(){
	    if(playerLocate==null && monsterLocate==null && standable) return true;
	    return false;
	  }
	  // public void setPlayerLocateStt(boolean stt){
	  //   playerLocate=stt;
	  // }
	  // public void setMonsterLocateStt(boolean stt){
	  //   monsterLocate=stt;
	  // }
	  // public boolean getPlayerLocateStt(){
	  //   return playerLocateStt;
	  // }
	  // public boolean getMonsterLocateStt(){
	  //   return monsterLocateStt;
	  // }

	  public Player getPlayerLocate(){
	      return playerLocate;
	  }
	  public Monster getMonsterLocate(){
	      return monsterLocate;
	  }
	  public void setMonsterLocate(Monster x){
	    monsterLocate=x;
	    //setMonsterLocateStt(true);
	  }
	  public void setPlayerLocate(Player x){
	    playerLocate=x;
	    //setMonsterLocateStt(true);
	  }
	

}
