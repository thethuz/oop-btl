public class DangerousMonster extends Monster{
  public boolean findPlayer(){
    if()
  }
  public void huntPlayer(){
    if(Map.getMapItem(getPositionX()-1,getPositionY()).getPlayerLocate()||
    Map.getMapItem(getPositionX()+1,getPositionY()).getPlayerLocate()||
    Map.getMapItem(getPositionX(),getPositionY()-1).getPlayerLocate()||
    Map.getMapItem(getPositionX(),getPositionY()+1).getPlayerLocate())
    {
      attack(Map.getPlayer());
    }
    else findPlayer();
  }
}
