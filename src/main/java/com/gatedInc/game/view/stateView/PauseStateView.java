package com.gatedInc.game.view.stateView;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PauseStateView implements StateView {

    Image pauseImg;

    public PauseStateView() {
        try {
            pauseImg = new Image(new FileInputStream("src/main/resources/spritesheets/pause.png"));
        } catch (FileNotFoundException e) {
            System.out.println("Can't load pause img");
        }

    }

    @Override
    public void render(GraphicsContext context) {
        context.setFill(Color.rgb(42, 50, 55, 0.1));
        context.fillRect(0, 0, 1280, 720);
        context.drawImage(pauseImg, 1280 / 2 - 200 / 2, 720 / 2 - 160 / 2, 200, 160);

    }
}
