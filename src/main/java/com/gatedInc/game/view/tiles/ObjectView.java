package com.gatedInc.game.view.tiles;

import com.gatedInc.game.utils.Camera;
import javafx.scene.canvas.GraphicsContext;

public interface ObjectView {

    void render(GraphicsContext context, Camera camera);

    void update(double dt);
}
