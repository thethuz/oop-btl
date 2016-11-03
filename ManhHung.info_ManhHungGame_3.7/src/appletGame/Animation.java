package appletGame;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	private ArrayList<AnimFrame> frames;
	private int currentFrame;
	private long animTime;
	private long totalDuration;

	public Animation() {
		frames = new ArrayList<AnimFrame>();
		totalDuration = 0;

		synchronized (this) {
			animTime = 0;
			currentFrame = 0;
		}
	}

	public synchronized void addFrame(Image image, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}

	public synchronized void update(long elapsedTime) {
		if (frames.size() > 1) {
			animTime += elapsedTime;
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;
			}

			while (animTime > getFrame(currentFrame).getEndTime()) {
				currentFrame++;
			}
		}
	}

	public synchronized Image getImage() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentFrame).getImage();
		}
	}

	private AnimFrame getFrame(int i) {
		return frames.get(i);
	}

	public ArrayList<AnimFrame> getFrames() {
		return frames;
	}

	public void setFrames(ArrayList<AnimFrame> frames) {
		this.frames = frames;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public long getAnimTime() {
		return animTime;
	}

	public void setAnimTime(long animTime) {
		this.animTime = animTime;
	}

	public long getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(long totalDuration) {
		this.totalDuration = totalDuration;
	}
}
