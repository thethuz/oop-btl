import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Vector;

public class Item{

  private int x,y,z;

  private String itemName;

  private static BufferedImage image;

    public Item(int x,int y,int z) {
      this.x=x;
      this.y=y;
      this.z=z;
      //System.out.println(x+" : "+y+" : "+z);
    }
    public void effect(Human human){
        human.setDamage(human.getDamageByLevel()*2);
        z--;
        if (z <= 0) {
            z = 89;
            y--;
        }
        if (y <= 0 && x > 0) {
            y = 59;
            x--;
            System.out.println(x);
        } else if (x == 0) {
            if (y <= 1) {
                y = 0;
                if (y == 0) {
                        z = 0;
                        human.setDamage(human.getDamageByLevel());
                        //MainPanel.checkEvent[0] = 0;
                }
            }
        }

    }
    public void draw(Graphics g) {

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
                x--;
            } else if (x == 0) {
                if (y <= 1) {
                    y = 0;
                    if (y == 0) {

                            z = 0;
                            //human.setDamage(human.getDamageByLevel()/2);
                            //MainPanel.checkEvent[0] = 0;

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
