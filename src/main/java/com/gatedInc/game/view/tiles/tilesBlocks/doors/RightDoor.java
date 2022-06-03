package com.gatedInc.game.view.tiles.tilesBlocks.doors;

import com.gatedInc.game.graphic.Animation;
import com.gatedInc.game.graphic.Sprite;
import com.gatedInc.game.utils.Camera;
import com.gatedInc.game.utils.Rectangle;
import com.gatedInc.game.utils.Vector;
import com.gatedInc.game.view.tiles.ObjectView;
import com.gatedInc.game.view.tiles.tilesBlocks.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RightDoor extends Tile implements ObjectView, Doors {

    private Image[] opening;
    private Image[] close;

    private Animation closed;
    private Animation opened;
    private Animation curentAnimation;
    private final Rectangle rectangle;


    public RightDoor(Image img, Vector pos, int w, int h) {
        super(img, pos, w, h);
        setAnimationImage();
        setAnimation();
        curentAnimation = closed;
        curentAnimation.start();
        this.img = curentAnimation.getSprite();
        rectangle = new Rectangle(w, h, pos);

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setAnimationImage() {
        Sprite.resetSpriteLoader();
        close = new Image[]{Sprite.getSprite(1, 0, "door"), Sprite.getSprite(3, 0, "door"), Sprite.getSprite(5, 0, "door"), Sprite.getSprite(7, 0, "door")};
        opening = new Image[]{Sprite.getSprite(1, 1, "door"), Sprite.getSprite(3, 1, "door"), Sprite.getSprite(5, 1, "door"), Sprite.getSprite(7, 1, "door"),
                Sprite.getSprite(1, 2, "door"), Sprite.getSprite(3, 2, "door"), Sprite.getSprite(5, 2, "door"), Sprite.getSprite(7, 2, "door")
        };


    }

    public void setAnimation() {
        closed = new Animation(close, 10);
        opened = new Animation(opening, 10);
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public void render(GraphicsContext context, Camera camera) {
        context.drawImage(img, pos.x - camera.getPos().x, pos.y - camera.getPos().y, w, h);
    }

    @Override
    public void update(double dt) {
        if (curentAnimation == opened) curentAnimation.stopIfLast();
        curentAnimation.update(-1);
        img = curentAnimation.getSprite();
    }

    @Override
    public boolean setOpenAnim() {
        if (curentAnimation != opened) {
            curentAnimation = opened;
            //curentAnimation.reset();
            curentAnimation.start();
        }


        return curentAnimation.isStopped();

    }

    @Override
    public void setCloseAnim() {
        curentAnimation.reset();
        curentAnimation = closed;
        curentAnimation.start();
    }
}
