package com.gatedInc.game.view.tiles.tilesBlocks;

import com.gatedInc.game.utils.Rectangle;
import com.gatedInc.game.utils.Vector;
import javafx.scene.image.Image;

public class Wall extends Tile {


    private Rectangle rectangle;


    public Wall(Image img, Vector pos, int w, int h) {
        super(img, pos, w, h);
        rectangle = new Rectangle(w, h, pos);

    }


    public Rectangle getBounds(){
        return rectangle;
    }

    @Override
    public Image getImage() {
        return this.img;
    }
}
