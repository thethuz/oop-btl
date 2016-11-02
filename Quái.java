
public class Quái{
	int vision ;
	public Quái{
		Time.
	}
	public void defense(Character x){
		attack(x);
	}
	public boolean observe(){
	if(MapItem[getPositionX()-1][getPositionY()-1].playerLocate()||
	MapItem[getPositionX()-1][getPositionY()+1].playerLocate()||
				MapItem[getPositionX()+1][getPositionY()-1].playerLocate()||
				MapItem[getPositionX()+1][getPositionY()+1].playerLocate())
				attack();
		for(int i=0; i<=vision;i++){
			for(int j=0;j<=vision;j++){
				if(Map.getItem(getPositionX()-i,getPositionY()-j).playerLocate() ||
				MapItem[getPositionX()-i][getPositionY()+j].playerLocate()||
				MapItem[getPositionX()+i][getPositionY()-j].playerLocate()||
				MapItem[getPositionX()+i][getPositionY()+j].playerLocate()) return true;
			}
		}
		return false;
	}

}
public class Player{
	public void defense(Character x){
		attack(x);
	}
}
