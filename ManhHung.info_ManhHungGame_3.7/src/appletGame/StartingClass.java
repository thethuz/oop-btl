package appletGame;

//created by ManhHung.info
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StartingClass extends Applet implements Runnable, KeyListener {
	enum GameState {
		Running, Dead
	}

	GameState state = GameState.Running;

	private static Robot robot;
	public static Heliboy hb, hb2;
	public static int score = 0;
	private Font font = new Font(null, Font.BOLD, 30);

	private Image image, currentSprite, character, character2, character3,
			characterDown, characterJumped, background, heliboy, heliboy2,
			heliboy3, heliboy4, heliboy5;
	private Animation robotAnim, heliBoyAnim;
	private Graphics second;
	private URL base;
	//
	private static Background bg1, bg2;
//Map
	public static Image tilegrassTop, tilegrassBot, tilegrassLeft,
			tilegrassRight, tiledirt;
	private List<Tile> tilearray = new ArrayList<Tile>();

	@Override
	public void init() {
		//Game
		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);

		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Manh Hung Game");
		//Game
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// Player
		character = getImage(base, "res/character.png");
		character2 = getImage(base, "res/character2.png");
		character3 = getImage(base, "res/character3.png");

		characterDown = getImage(base, "res/down.png");
		characterJumped = getImage(base, "res/jumped.png");
		//Game
		background = getImage(base, "res/background.png");
		//Enemy
		heliboy = getImage(base, "res/heliboy.png");
		heliboy2 = getImage(base, "res/heliboy2.png");
		heliboy3 = getImage(base, "res/heliboy3.png");
		heliboy4 = getImage(base, "res/heliboy4.png");
		heliboy5 = getImage(base, "res/heliboy5.png");

		robotAnim = new Animation();
		robotAnim.addFrame(character, 1250);
		robotAnim.addFrame(character2, 50);
		robotAnim.addFrame(character3, 50);
		robotAnim.addFrame(character2, 50);

		heliBoyAnim = new Animation();
		heliBoyAnim.addFrame(heliboy, 100);
		heliBoyAnim.addFrame(heliboy2, 100);
		heliBoyAnim.addFrame(heliboy3, 100);
		heliBoyAnim.addFrame(heliboy4, 100);
		heliBoyAnim.addFrame(heliboy5, 100);
		heliBoyAnim.addFrame(heliboy4, 100);
		heliBoyAnim.addFrame(heliboy3, 100);
		heliBoyAnim.addFrame(heliboy2, 100);

		currentSprite = robotAnim.getImage();
		//Map
		tiledirt = getImage(base, "res/tiledirt.png");
		tilegrassTop = getImage(base, "res/tilegrasstop.png");
		tilegrassBot = getImage(base, "res/tilegrassbot.png");
		tilegrassLeft = getImage(base, "res/tilegrassleft.png");
		tilegrassRight = getImage(base, "res/tilegrassright.png");
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		robot = new Robot();
		// Initialize Tiles
		try {
			loadMap("res/map.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		hb = new Heliboy(340, 360);
		hb2 = new Heliboy(700, 360);

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		if (state == GameState.Running) {
			while (true) {
				robot.update();
				if (robot.isJumped()) {
					currentSprite = characterJumped;
				} else if (robot.isJumped() == false
						&& robot.isDucked() == false) {
					currentSprite = robotAnim.getImage();
				}
				ArrayList<Bullet> bullets = robot.getBullets();
				for (int i = 0; i < bullets.size(); i++) {
					Bullet b = bullets.get(i);
					if (b.isVisible() == true) {
						b.update();
					} else {
						bullets.remove(i);
					}
				}
				hb.update();
				hb2.update();
				bg1.update();
				bg2.update();
				for (int i = 0; i < tilearray.size(); i++) {
					Tile t = (Tile) tilearray.get(i);
					t.update();
				}
				robotAnim.update(10);
				heliBoyAnim.update(50);
				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (robot.getCenterY() > 500) {
					state = GameState.Dead;
				}
			}
		}
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		if (state == GameState.Running) {
			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
			for (int i = 0; i < tilearray.size(); i++) {
				Tile t = (Tile) tilearray.get(i);
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
			}

			ArrayList<Bullet> bullets = robot.getBullets();
			for (int i = 0; i < bullets.size(); i++) {
				Bullet b = bullets.get(i);
				g.setColor(Color.YELLOW);
				g.fillRect(b.getX(), b.getY(), 10, 5);
			}
			//			g.drawRect((int) robot.rect.getX(), (int) robot.rect.getY(),
			//					(int) robot.rect.getWidth(), (int) robot.rect.getHeight());
			//			g.drawRect((int) robot.rect2.getX(), (int) robot.rect2.getY(),
			//					(int) robot.rect2.getWidth(), (int) robot.rect2.getHeight());
			//			g.drawRect((int) robot.rect3.getX(), (int) robot.rect3.getY(),
			//					(int) robot.rect3.getWidth(), (int) robot.rect3.getHeight());
			//			g.drawRect((int) robot.rect4.getX(), (int) robot.rect4.getY(),
			//					(int) robot.rect4.getWidth(), (int) robot.rect4.getHeight());
			g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63, this);
			g.drawImage(heliBoyAnim.getImage(), hb.getCenterX() - 48, hb.getCenterY() - 48, this);
			g.drawImage(heliBoyAnim.getImage(), hb2.getCenterX() - 48, hb2.getCenterY() - 48, this);

			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 740, 30);
		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			robot.jump();
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if (robot.isJumped() == false) {
				robot.setDucked(true);
				// robot.setSpeedX(0);
			}
			break;

		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			robot.setMovingLeft(true);
			break;

		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			robot.setMovingRight(true);
			break;

		case KeyEvent.VK_SPACE:
			if (robot.isDucked() == false && robot.isJumped() == false) {
				robot.shoot();
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = robotAnim.getImage();
			robot.setDucked(false);
			break;

		case KeyEvent.VK_LEFT:
			robot.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			robot.stopRight();
			break;

		case KeyEvent.VK_SPACE:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static Robot getRobot() {
		return robot;
	}

	private void loadMap(String filename) throws IOException {
		List<String> lines = new ArrayList<String>();
		int width = 0;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}
			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());
			}
		}
		height = lines.size();

		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {
				// System.out.println(i + "is i ");
				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}
			}
		}
	}
}
