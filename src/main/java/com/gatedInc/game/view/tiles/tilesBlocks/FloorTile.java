package com.gatedInc.game.view.tiles.tilesBlocks;

import com.gatedInc.game.utils.Vector;
import javafx.scene.image.Image;

public class FloorTile extends Tile {

    public FloorTile(Image img, Vector pos, int w, int h) {
        super(img, pos, w, h);
    }

    @Override
    public Image getImage() {
        return this.img;
    }
}
