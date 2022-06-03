package com.gatedInc.game.view.tiles.tilesBlocks;

import com.gatedInc.game.model.Items.Chest;
import com.gatedInc.game.utils.Camera;
import com.gatedInc.game.view.tiles.TilesImgManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ChestView {

    private boolean isFull;
    private final Image fullImg;
    private final Image emptyImg;
    private final Chest chest;


    public ChestView(Chest chest) {
        this.chest = chest;
        fullImg = TilesImgManager.getChest(TilesImgManager.ChestType.full);
        emptyImg = TilesImgManager.getChest(TilesImgManager.ChestType.empty);

    }


    public void render(GraphicsContext context, Camera camera) {

        if (chest.isEmpty()) {
            context.drawImage(emptyImg, chest.getPosition().x - camera.getPos().x, chest.getPosition().y - camera.getPos().y, chest.getWidth(), chest.getHeight());
        } else {
            context.drawImage(fullImg, chest.getPosition().x - camera.getPos().x, chest.getPosition().y - camera.getPos().y, chest.getWidth(), chest.getHeight());
        }

    }


}
