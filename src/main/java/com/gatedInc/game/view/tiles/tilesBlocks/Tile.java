package com.gatedInc.game.view.tiles.tilesBlocks;

import com.gatedInc.game.utils.Camera;
import com.gatedInc.game.utils.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Tile {

    protected int w;
    protected int h;

    public Image img;
    public Vector pos;

    public Tile(Image img, Vector pos, int w, int h) {
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    public int getWidth() { return w; }
    public int getHeight() { return h; }

    //public abstract boolean update(AABB p);
    //public abstract boolean isInside(AABB p);

    public abstract Image getImage();

    public void setImg(Image img) {
        this.img = img;
    }

    public Vector getPos() { return pos; }

    public void render(GraphicsContext context, Camera camera) {

        context.drawImage(img, pos.getX() - camera.getPos().x, pos.getY() - camera.getPos().y, w, h);
        /*if(this instanceof Wall) {
            context.setFill(Color.RED);
            context.fillRect(pos.x - camera.getPos().x , pos.y - camera.getPos().y, 44, 44);
        }*/
    }


}
