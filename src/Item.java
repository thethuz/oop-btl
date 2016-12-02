
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Item extends Event {

    private int x, y;
    private String itemName;

    private static BufferedImage image;

    public Item(int x, int y, String itemName) {
        super(x, y, 17, false);
        this.x = x;
        this.y = y;

        this.itemName = itemName;

    }
    public void draw(Graphics g) {

//        g.drawImage(image, 400, 16, null);
        g.drawString("sơn hai", 400, 16);
    }

    private void loadImage(String filename) {
        try {
            image = ImageIO.read(getClass().getResource(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
