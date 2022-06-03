package com.gatedInc.game.view.stateView;

import com.gatedInc.game.App;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameOverStateView implements StateView {
    @Override
    public void render(GraphicsContext context) {
        context.setFill(new Color(0, 0, 0, 0.1));
        context.fillRect(0, 0, App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
        context.setFill(Color.WHITE);
        context.fillText("Vous avez perdu appuyé sur < N > pour relancer une partie.", 100, 100);
    }
}
