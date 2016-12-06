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
    }

    public String getItemName() {
        return itemName;
    }

    public String toString() {
        return "TREASURE:" + super.toString() + ":" + itemName;
    }
}
