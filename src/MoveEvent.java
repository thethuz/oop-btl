public class MoveEvent extends Event {
    public int destMapNo;
    public int destX;
    public int destY;
    //tọa độ x, tọa độ y, kiểu event, destX di chuyển đc hay k
    public MoveEvent(int x, int y, int chipNo, int destMapNo, int destX, int destY) {
        super(x, y, chipNo, false);
        this.destMapNo = destMapNo;
        this.destX = destX;
        this.destY = destY;
    }

    public String toString() {
        return "MOVE:" + super.toString() + ":" + destMapNo + ":" + destX + ":" + destY;
    }
}
