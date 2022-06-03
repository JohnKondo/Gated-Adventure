package com.gatedInc.game.graphic;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;


public class Animation {

    private final int animationDirection;
    private final int totalFrames;
    private final List<Frame> frames = new ArrayList<Frame>();
    private int frameCount;
    private int frameDelay;
    private int currentFrame;
    private boolean stopped;

    public Animation(Image[] frames, int frameDelay) {

        this.frameDelay = frameDelay;
        this.stopped = true;

        for (Image frame : frames) {
            addFrame(frame, frameDelay);
        }

        this.frameCount = 0;
        this.frameDelay = frameDelay;
        this.currentFrame = 0;
        this.animationDirection = 1;
        this.totalFrames = this.frames.size();

    }

    public boolean isStopped() {
        return stopped;
    }

    public boolean stopIfLast() {
        if (currentFrame == frames.size() - 1) {
            stop();
            return true;
        }
        return false;
    }

    public void start() {
        if (!stopped) {
            return;
        }

        if (frames.size() == 0) {
            return;
        }

        stopped = false;
    }

    public void stop() {
        if (frames.size() == 0) {
            return;
        }

        stopped = true;
    }

    public void restart() {
        if (frames.size() == 0) {
            return;
        }

        stopped = false;
        currentFrame = 0;
    }

    public void reset() {
        this.stopped = true;
        this.frameCount = 0;
        this.currentFrame = 0;
    }

    private void addFrame(Image frame, int duration) {
        if (duration <= 0) {
            System.err.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }

        frames.add(new Frame(frame, duration));
        currentFrame = 0;
    }

    public Image getSprite() {
        return frames.get(currentFrame).getFrame();
    }


    public void update(int fd) {
        if (fd < 0) {
            frameDelay = 10;
        } else {
            frameDelay = fd;
        }

        if (!stopped) {
            frameCount++;

            if (frameCount > frameDelay) {
                frameCount = 0;
                currentFrame += animationDirection;

                if (currentFrame > totalFrames - 1) {
                    currentFrame = 0;
                } else if (currentFrame < 0) {
                    currentFrame = totalFrames - 1;
                }
            }
        }

    }

}