package com.gatedInc.game.view;

import javafx.animation.AnimationTimer;

public class Timer extends AnimationTimer {

    private final JavaFXView javaFXView;
    private long lastTick = -1;
    private boolean isStopped = false;

    public Timer(JavaFXView view) {
        this.javaFXView = view;
    }

    @Override
    public void handle(long now) {
        if (isStopped) {
            return;
        }
        if (lastTick < 0) {
            this.lastTick = now;
            return;
        }
        double dt = (now - lastTick) * 1e-6;
        javaFXView.tick(dt);
        lastTick = now;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void stop() {
        lastTick = -1;
        isStopped = true;
    }

    public void restart() {
        super.start();
        isStopped = false;
    }
}
