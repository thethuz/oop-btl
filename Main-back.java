import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class MainPanel extends JPanel implements KeyListener, Runnable, Common {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 640;
    // 20ms/frame = 50fps
    private static final int PERIOD = 20;

    // debug mode
    private static final boolean DEBUG_MODE = true;

    // map list
    private Map[] maps;
    // current map number
    private int mapNo;

    // our hero!
    private Human hero;

    public static int[] checkEvent = new int[3];

    // our hero's enemy!

    private Monster monster;
    private Monster[] monsters=new Monster[10];
    private Monster[] dangerousMonster=new DangerousMonster[10];
    private Monster[] flyingMonster =new FlyingMonster[10];
    // action keys
    private ActionKey leftKey;
    private ActionKey rightKey;
    private ActionKey upKey;
    private ActionKey downKey;
    private ActionKey spaceKey;
    private ActionKey attackKey;

    private Thread gameLoop;
    private Random rand = new Random();

    private MessageWindow messageWindow;
    private static Rectangle WND_RECT = new Rectangle(142, 480, 356, 140);

    private MidiEngine midiEngine = new MidiEngine();
    private WaveEngine waveEngine = new WaveEngine();

    // BGM

    private static final String[] bgmNames = {"castle", "field"};
    // Sound Clip
    private static final String[] soundNames = {"treasure", "door", "step"};

    // double buffering
    private Graphics dbg;
    private Image dbImage = null;

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setFocusable(true);
        addKeyListener(this);

        // create action keys
        leftKey = new ActionKey();
        rightKey = new ActionKey();
        upKey = new ActionKey();
        downKey = new ActionKey();
        attackKey = new ActionKey();
        spaceKey = new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);

        // create map
        maps = new Map[3];
        maps[0] = new Map("map/castle.map", "event/castle.evt", "castle", this);
        maps[1] = new Map("map/field.map", "event/field.evt", "field", this);
        maps[2] = new Map("map/forest.map", "event/forest.evt", "forest", this);
        mapNo = 0;  // initial map

        // create human
        System.out.println("Khoi tao");
        hero = new Human(6, 6, 0, DOWN, 0, maps[mapNo],"hero");
        //Human hero1 = new Human(6, 7, 0, UP, 0, maps[mapNo]);
        monster = new Monster (6,7, 0, DOWN, 0, maps[mapNo]);

        arrangeMonster(1,7);
        arrangeMonster(2,7);
        // add humans to the map
        maps[mapNo].addHuman(hero);
        //maps[mapNo].addHuman(hero1);
        maps[mapNo].addMonster(monster);
        //maps[1].addMonster(monsters);
        //maps
        // create message window
        messageWindow = new MessageWindow(WND_RECT);
        System.out.println(hero);
        System.out.println(monster);
        // load BGM and sound clips
        loadSound();

        midiEngine.play(maps[mapNo].getBgmName());
        System.out.println("start");
        // start game loop
        gameLoop = new Thread(this);

        gameLoop.start();
    }
    public void arrangeMonster(int mapID, int numberOfMonsters){
      for(int i=0;i<numberOfMonsters;i++){
        int x, y;
        do{
          x=rand.nextInt(maps[mapID].getRow());
          y=rand.nextInt(maps[mapID].getCol());

        } while(maps[mapID].isHit(x,y));
        monsters[i]=new Monster(x,y,0,DOWN,1, maps[mapID]);
        monsters[i].setName("Quỷ chưa nguy hiểm lắm");
        //
        do{
          x=rand.nextInt(maps[mapID].getRow());
          y=rand.nextInt(maps[mapID].getCol());

        } while(maps[mapID].isHit(x,y));
        dangerousMonster[i]=new DangerousMonster(x,y,1,DOWN,1, maps[mapID]);
        dangerousMonster[i].setName("Quỷ nguy hiểm");
        maps[mapID].addMonster(monsters[i]);
        maps[mapID].addMonster(dangerousMonster[i]);
        //
        do{
          x=rand.nextInt(maps[mapID].getRow());
          y=rand.nextInt(maps[mapID].getCol());

        } while(maps[mapID].isUnflyable(x,y));
        flyingMonster[i]= new FlyingMonster(x,y,3,DOWN,1,maps[mapID]);
        flyingMonster[i].setName("Quỷ biết bay");
        maps[mapID].addMonster(flyingMonster[i]);
      }
    }

    public void run() {
        long beforeTime, timeDiff, sleepTime;

        beforeTime = System.currentTimeMillis();
        while (true) {
            checkInput();
            gameUpdate();
            gameRender();
            printScreen();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleepTime = PERIOD - timeDiff;
            // sleep at least 5ms
            if (sleepTime <= 0) {
                sleepTime = 5;
            }

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            beforeTime = System.currentTimeMillis();
        }
    }

    private void checkInput() {
        if (messageWindow.isVisible()) {
            messageWindowCheckInput();
        } else {
            mainWindowCheckInput();
        }
    }

    private void gameUpdate() {
        if (!messageWindow.isVisible()) {

            heroMove();
            humanMove();
            monsterMove();
            monsterAttack();
        }
    }
    //Render đồ họa trò chơi
    private void gameRender() {
        if (dbImage == null) {
            // buffer image
            dbImage = createImage(WIDTH, HEIGHT);
            if (dbImage == null) {
                return;
            } else {
                // device context of buffer image
                dbg = dbImage.getGraphics();
            }
        }

        dbg.setColor(Color.WHITE);
        dbg.fillRect(0, 0, WIDTH, HEIGHT);
        //
        // calculate offset so that the hero is in the center of a screen.
        int offsetX = hero.getPX() - MainPanel.WIDTH / 2;
        // do not scroll at the edge of the map
        if (offsetX < 0) {
            offsetX = 0;
        } else if (offsetX > maps[mapNo].getWidth() - MainPanel.WIDTH) {
            offsetX = maps[mapNo].getWidth() - MainPanel.WIDTH;
        }

        int offsetY = hero.getPY() - MainPanel.HEIGHT / 2;
        // do not scroll at the edge of the map
        if (offsetY < 0) {
            offsetY = 0;
        } else if (offsetY > maps[mapNo].getHeight() - MainPanel.HEIGHT) {
            offsetY = maps[mapNo].getHeight() - MainPanel.HEIGHT;
        }

        // draw map
        maps[mapNo].draw(dbg, offsetX, offsetY);

        // draw message window
        messageWindow.draw(dbg);

        // thông tin góc trên bên trái
        hero.printInfomation(dbg);
        if (hero.isDead() == true) {
            dbg.setColor(Color.BLACK);
            dbg.fillRect(0, 0, MainPanel.WIDTH, MainPanel.HEIGHT);
            dbg.setColor(Color.WHITE);
            dbg.drawString("GAME OVER", 300, 320);
        }
      }

    private void printScreen() {
        Graphics g = getGraphics();
        if ((g != null) && (dbImage != null)) {
            g.drawImage(dbImage, 0, 0, null);
        }
        Toolkit.getDefaultToolkit().sync();
        if (g != null) {
            g.dispose();
        }
    }

    private void mainWindowCheckInput() {
        if (leftKey.isPressed()) {
            if (!hero.isMoving()) {
                hero.setDirection(LEFT);
                hero.setMoving(true);
            }
        }

        if (rightKey.isPressed()) {
            if (!hero.isMoving()) {
                hero.setDirection(RIGHT);
                hero.setMoving(true);
            }
        }

        if (upKey.isPressed()) {
            if (!hero.isMoving()) {
                hero.setDirection(UP);
                hero.setMoving(true);
            }
        }

        if (downKey.isPressed()) {
            if (!hero.isMoving()) {
                hero.setDirection(DOWN);
                hero.setMoving(true);
            }
        }

        if (attackKey.isPressed()){
          hero.attack();
        }

        if (spaceKey.isPressed()) {
            // cannot open window if hero is moving
            if (hero.isMoving()) {
                return;
            }

            // search
            TreasureEvent treasure = hero.search();
            if (treasure != null) {
                waveEngine.play("treasure");
                messageWindow.setMessage("HERO DISCOVERED/" + treasure.getItemName());
                if (treasure.getX() == 10) {
                    checkEvent[0] = 10;
                }
                if (treasure.getX() == 11) {
                    Human.suitUp = 1;
                }
                if (treasure.getX() == 12) {
                    checkEvent[2] = 12;
                }
                messageWindow.show();
                maps[mapNo].removeEvent(treasure);
                return;
            }

            // door
            DoorEvent door = hero.open();
            if (door != null && checkEvent[2] == 12) {
                waveEngine.play("door");
                maps[mapNo].removeEvent(door);
                return;
            } else {
                messageWindow.setMessage("YOU NEED TO HAVE THE KEY TO OPEN");
                    messageWindow.show();
            }

            // talk
            if (!messageWindow.isVisible()) {
                Human c = hero.talkWith();
                if (c != null) {
                    messageWindow.setMessage(c.getMessage());
                    messageWindow.show();
                } else {
                    messageWindow.setMessage("THERE IS NO ONE/IN THAT DIRECTION");
                    messageWindow.show();
                }
            }
        }
    }

    private void messageWindowCheckInput() {
        if (spaceKey.isPressed()) {
            if (messageWindow.nextPage()) {
                messageWindow.hide();
            }
        }
    }

    private void heroMove() {
        if (hero.isMoving()) {
            if (hero.move()) {
                Event event = maps[mapNo].checkEvent(hero.getX(), hero.getY());
                if (event instanceof MoveEvent) {
                    waveEngine.play("step");
                    // move to another map
                    MoveEvent m = (MoveEvent)event;
                    int tempHealth=hero.getHealth();
                    int tempLevel=hero.getLevel();
                    int tempDamage=hero.getDamage();
                    int tempExp=hero.getExp();
                    maps[mapNo].removeHuman(hero);

                    //Human heroTemp=new Human();
                    //int temp=mapNo;
                    //
                    mapNo = m.destMapNo;

                    //maps[mapNo].addHuman(hero);

                    hero = new Human(m.destX, m.destY, 0, DOWN, 0, maps[mapNo],"hero");
                    //tempHero.setHealth
                    hero.setHealth(tempHealth);
                    hero.setDamage(tempDamage);
                    hero.setLevel(tempLevel);
                    hero.setExp(tempExp);
                    maps[mapNo].addHuman(hero);
                    //

                    midiEngine.play(maps[mapNo].getBgmName());
                }
            }
        }
    }

    private void monsterMove() {
      // get monsters in the map
      Vector<Monster> monsters = maps[mapNo].getMonsters();
      // move each monster
      for (int i = 0; i < monsters.size(); i++) {
          Monster m = monsters.get(i);
          //Nếu c.isMoving==true và c.isNotAttacking==true then move;
          //else hoặc isMoving=false hoặc isNotAttacking==false
          //Nếu isNotAttacking thì đổi hướng
          //thì đổi hướng
          if (m.getMoveType() == 1) {
              if (m.isMoving()&&(m.isMonsterAttacking()==false)) {
                  //System.out.println("monster "+i+" is moving"+c.getDirection());
                  m.move();
                  // System.out.println(m.getX()+";"+m.getY());
              } else if ( (m.isMonsterAttacking()==false) && (rand.nextDouble() < Monster.PROB_MOVE) ) {
                  m.setDirection(rand.nextInt(4));
                  m.setMoving(true);
              }
          }
      }
    }

    private void monsterAttack() {
      // get monsters in the map
      Vector<Monster> monsters = maps[mapNo].getMonsters();
      // move each monster
      for (int i = 0; i < monsters.size(); i++) {
          Monster m = monsters.get(i);
          if (m.getAttackType() == 1) {
              if (m.attack()) {
                  //System.out.println("Monster "+i+" is attacking"+c.getDirection());

              } else if (rand.nextDouble() < Monster.PROB_MOVE) {
                  // System.out.println("Monster "+i+": Hahaha");
                  m.setDirection(rand.nextInt(4));
                  m.setMoving(true);
              }
          }
      }
    }

    private void humanMove() {
        // get humans in the map
        Vector<Human> humans = maps[mapNo].getHumans();
        // move each human
        for (int i = 0; i < humans.size(); i++) {
            Human c = humans.get(i);
            if (c.getMoveType() == 1) {
                if (c.isMoving()) {
                    c.move();
                } else if (rand.nextDouble() < Human.PROB_MOVE) {
                    c.setDirection(rand.nextInt(4));
                    c.setMoving(true);
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            leftKey.press();
        }
        if (keyCode == KeyEvent.VK_A) {
            attackKey.press();

        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            rightKey.press();
        }
        if (keyCode == KeyEvent.VK_UP) {
            upKey.press();
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downKey.press();
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            spaceKey.press();
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            leftKey.release();
        }
        if (keyCode == KeyEvent.VK_A) {
            attackKey.release();
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            rightKey.release();
        }
        if (keyCode == KeyEvent.VK_UP) {
            upKey.release();
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downKey.release();
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            spaceKey.release();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    private void loadSound() {
        // load midi files
        for (int i = 0; i < bgmNames.length; i++) {
            midiEngine.load(bgmNames[i], "bgm/" + bgmNames[i] + ".mid");
        }

        // load sound clip files
        for (int i = 0; i < soundNames.length; i++) {
            waveEngine.load(soundNames[i], "sound/" + soundNames[i] + ".wav");
        }
    }

    public int[] getCheckEvent() {
        return checkEvent;
    }
}
