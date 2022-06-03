package com.gatedInc.game.view.tiles.tilesBlocks;

import com.gatedInc.game.graphic.Animation;
import com.gatedInc.game.graphic.Sprite;
import com.gatedInc.game.utils.Rectangle;
import com.gatedInc.game.utils.Vector;
import com.gatedInc.game.view.tiles.ObjectView;
import javafx.scene.image.Image;

public class Container extends Tile implements ObjectView {
    private Image[] animFrames;


    private Animation animation;
    private final Rectangle rectangle;


    public Container(Image img, Vector pos, int w, int h) {
        super(img, pos, w, h);
        setAnimationImage();
        setAnimation();
        animation.start();
        this.img = animation.getSprite();
        rectangle = new Rectangle(w - w / 2, h, pos.add(new Vector(w / 2 - w / 3, 0)));
    }

    public void setAnimationImage() {
        Sprite.resetSpriteLoader();
        animFrames = new Image[]{Sprite.getSprite(0, 0, "Items/obj"), Sprite.getSprite(1, 0, "Items/obj"),
                Sprite.getSprite(2, 0, "Items/obj"), Sprite.getSprite(3, 0, "Items/obj"),
                Sprite.getSprite(0, 1, "Items/obj"), Sprite.getSprite(1, 1, "Items/obj"),
                Sprite.getSprite(2, 1, "Items/obj"), Sprite.getSprite(3, 1, "Items/obj"),};
    }

    public void setAnimation() {
        animation = new Animation(animFrames, 10);

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void update(double dt) {
        animation.update(-1);
        img = animation.getSprite();
    }

    @Override
    public Image getImage() {
        return img;
    }
}
