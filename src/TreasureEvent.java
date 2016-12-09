public class TreasureEvent extends Event {
    private String itemName;

    private int type;

    public int getType() {
        return type;
    }

    public TreasureEvent(int x, int y, String itemName) {

        super(x, y, 17, false);
        this.itemName = itemName;
        this.type = x;
        System.out.println(itemName);
    }

    public String getItemName() {
        return this.itemName;
    }
    public void setItemName(String itemName){
      this.itemName=itemName;
    }
    //public String getItem

    public String toString() {
        return "TREASURE:"  + ":" + itemName;
    }
}
