package appletGame;

import java.awt.Rectangle;

// created by ManhHung.info
public class Enemy {
	private int power, speedX, centerX, centerY;
	private Background bg = StartingClass.getBg1();
	public Rectangle r = new Rectangle(0, 0, 0, 0);

	public int health = 5;

	private int movementSpeed;
	private Robot robot = StartingClass.getRobot();

	public Enemy() {
		// TODO Auto-generated constructor stub
	}

	public void follow() {
		if (centerX < -95 || centerX > 810) {
			movementSpeed = 0;
		} else if (Math.abs(robot.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		} else {
			if (robot.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}
	}

	public void update() {
		follow();	
		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;

		r.setBounds(centerX - 25, centerY - 25, 50, 60);
		if (r.intersects(Robot.boundRect)) {
			checkCollision();
		}
	}

	private void checkCollision() {
		if (r.intersects(Robot.rect) || r.intersects(Robot.rect2)
				|| r.intersects(Robot.rect3) || r.intersects(Robot.rect4)) {
			System.out.println("collision");
		}
	}

	public void die() {
	}

	public void attack() {
	}

	/**
	 * @return the power
	 */
	public int getPower() {
		return power;
	}

	/**
	 * @return the speedX
	 */
	public int getSpeedX() {
		return speedX;
	}

	/**
	 * @return the centerX
	 */
	public int getCenterX() {
		return centerX;
	}

	/**
	 * @return the centerY
	 */
	public int getCenterY() {
		return centerY;
	}

	/**
	 * @return the bg
	 */
	public Background getBg() {
		return bg;
	}

	/**
	 * @param power
	 *            the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * @param speedX
	 *            the speedX to set
	 */
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	/**
	 * @param centerX
	 *            the centerX to set
	 */
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	/**
	 * @param centerY
	 *            the centerY to set
	 */
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	/**
	 * @param bg
	 *            the bg to set
	 */
	public void setBg(Background bg) {
		this.bg = bg;
	}
}
