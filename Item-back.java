import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Vector;

public class Item{

  private int x = 0, y = 1, z = 30;

  private String itemName;

  private static BufferedImage image;

    public Item() {

    }
    public void draw(Graphics g, Human humansOwner) {

        System.out.println(MainPanel.checkEvent[0] + " " + MainPanel.checkEvent[1]);

        if (MainPanel.checkEvent[0] == 10) {
            humans.setDamage(2);
            if (image == null) {
                loadImage("image/thuoctangluc.png");
            }
            g.drawImage(image, 430, 0, 462, 32, 0, 0, 32, 32, null);
            g.setColor(Color.YELLOW);
            g.drawString(x + " : " + y, 465, 20);

            z--;
            if (z <= 0) {
                z = 89;
                y--;
            }
            if (y <= 0 && x > 0) {
                y = 59;
                z--;
            } else if (x == 0) {
                if (y <= 1) {
                    y = 0;
                    if (y == 0) {
                        if (z <= 1) {
                            z = 0;
                            humans.setDamage(1);
                            MainPanel.checkEvent[0] = 0;
                        }
                    }
                }
            }
        }
    }

    private void loadImage(String filename) {
        try {
            image = ImageIO.read(getClass().getResource(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
