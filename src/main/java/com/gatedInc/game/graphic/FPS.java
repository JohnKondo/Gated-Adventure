package com.gatedInc.game.graphic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FPS {

    private double fps;

    public FPS() {
        fps = 0;
    }

    public void update(double dt) {
        fps = 1d / dt * 1000;
        fps = Math.round(fps);
    }

    public double getFps() {
        return fps;
    }

    public void render(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.fillText(fps + " fps", 1200, 700, 100);
    }


}
