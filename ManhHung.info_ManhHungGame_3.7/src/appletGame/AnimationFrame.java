package appletGame;

import java.awt.Image;

// created by ManhHung.info
public class AnimationFrame {
	private Image img = null;
	private long playTime = 0;
	
	public AnimationFrame(Image img, long playTime) {
		this.img = img;
		this.playTime = playTime;
	}

	/**
	 * @return the img
	 */
	public Image getImg() {
		return img;
	}

	/**
	 * @return the playTime
	 */
	public long getPlayTime() {
		return playTime;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(Image img) {
		this.img = img;
	}

	/**
	 * @param playTime the playTime to set
	 */
	public void setPlayTime(long playTime) {
		this.playTime = playTime;
	}

}
