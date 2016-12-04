public class TreasureEvent extends Event {
    private String itemName;

    private int x;//?dafuq x mean?

    public int getX() {
        return x;
    }

    public TreasureEvent(int x, int y, String itemName) {

        super(x, y, 17, false);
        this.itemName = itemName;
        this.x = x;
    }

    public String getItemName() {
        return itemName;
    }

    public String toString() {
        return "TREASURE:" + super.toString() + ":" + itemName;
    }
}
