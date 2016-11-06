package game;
public abstract class Character{
  private int health;
  private int damage,defense;
  private String name;
  private int moveSpeed;
  private int attackSpeed;
  private int timeReborn;
  private int positionX, positionY;
  public abstract void move(String control);
  public abstract void attack(Character enemy);
  public abstract void dead();
  public abstract void reborn();
  public abstract void defense(Character enemy);
  public abstract void defense(Character enemy){
    attack(enemy);
  }
  public int getHealth(){
    return health;
  }
  public void setHealth(int health){
    if (health<=0) {
      this.health=0;
      dead();
    }
    this.health=health;
  }

  public int getDamage(){
    return damage;
  }
  public void setDamage(int damage){
    this.damage=damage;
  }

  public int getDefense(){
    return defense;
  }
  public void setDefense(int defense){
    this.defense=defense;
  }

  public int getAttackSpeed(){
    return attackSpeed;
  }
  public void setAttackSpeed(int attackSpeed){
    this.attackSpeed= attackSpeed;
  }

  public int getMoveSpeed(){
    return moveSpeed;
  }
  public void setMoveSpeed(int moveSpeed){
    this.moveSpeed= moveSpeed;
  }

  public int getTimeReborn(){
    return timeReborn;
  }
  public void setTimeReborn(int timeReborn){
    this.timeReborn= timeReborn;
  }

  public int getName(){
    return name;
  }
  public void setName(String name){
    this.name= name;
  }
  public void setPositionX(int x){
    this.positionX=x;
  }
  public int getPositionX(){
    return positionX;
  }
  public void setPositionY(int y){
    this.positionY=y;
  }
  public int getPositionY(){
    return positionY;
  }
}
