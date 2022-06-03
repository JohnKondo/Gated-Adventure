package com.gatedInc.game.model;

import com.gatedInc.game.graphic.Animation;
import com.gatedInc.game.graphic.Sprite;
import com.gatedInc.game.utils.Vector;
import javafx.scene.image.Image;

import java.util.Random;

public class Enemy extends Character {

    IA ia;
    private boolean isAlive;
    private Image[] leftDying;
    private Image[] rightDying;
    private final Animation leftDie;
    private final Animation rightDie;
    public Enemy(int x, int y, float speed) {
        super(x, y, speed, 100, 100);
        isAlive = true;
        leftDie = new Animation(leftDying, 10);
        rightDie = new Animation(rightDying, 10);

        ia = new IA();
    }

    @Override
    protected void setAnimation() {
        walkLeft = new Animation(walkingLeft, 10);
        walkRight = new Animation(walkingRight, 10);
        direction = Direction.WEST;
        animation = walkLeft;
        animation.start();
    }

    public void kill() {
        isAlive = false;
        animation = rightDie;
        animation.start();


    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void update(double dt) {
        if (isAlive) {
            posSave = this.position.clone();
            animation.update(-1);
            Direction provDir = ia.nextDirection();
            if (provDir != null) {
                switch (provDir) {
                    case EAST:
                        goEast();
                        break;
                    case WEST:
                        goWest();
                        break;
                    case NORTH:
                        goNorth();
                        break;
                    case SOUTH:
                        goSouth();
                        break;
                }
            }

            if (direction == Direction.NORTH) {
                position = position.add(new Vector(0, dt / -SPEED));
            }

            if (direction == Direction.SOUTH) {
                position = position.add(new Vector(0, dt / SPEED));
            }
            if (direction == Direction.WEST) {
                position = position.add(new Vector(dt / -SPEED, 0));
            }

            if (direction == Direction.EAST) {
                position = position.add(new Vector(dt / SPEED, 0));
            }

            rectangle.update(new Vector(position.x + (rectangle.getWidth() / 6), position.y + height - (height / 4)));
        } else {

            animation.update(-1);
            animation.stopIfLast();
        }


    }

    @Override
    public void goNorth() {
        direction = Direction.NORTH;
    }

    @Override
    public void goSouth() {
        direction = Direction.SOUTH;
    }

    @Override
    public void setAnimationImages() {
        Sprite.resetSpriteLoader();
        walkingRight = new Image[]{
                Sprite.getSprite(0, 0, "enemy"), Sprite.getSprite(1, 0, "enemy"),
                Sprite.getSprite(2, 0, "enemy"), Sprite.getSprite(3, 0, "enemy")};
        walkingLeft = new Image[]{
                Sprite.getSprite(0, 1, "enemy"), Sprite.getSprite(1, 1, "enemy"),
                Sprite.getSprite(2, 1, "enemy"), Sprite.getSprite(3, 1, "enemy")};
        leftDying = new Image[]{
                Sprite.getSprite(0, 4, "enemy"), Sprite.getSprite(1, 4, "enemy"), Sprite.getSprite(2, 4, "enemy"), Sprite.getSprite(3, 4, "enemy"),
                Sprite.getSprite(0, 5, "enemy"), Sprite.getSprite(1, 5, "enemy"), Sprite.getSprite(2, 5, "enemy"), Sprite.getSprite(3, 5, "enemy")
        };
        rightDying = new Image[]{
                Sprite.getSprite(0, 2, "enemy"), Sprite.getSprite(1, 2, "enemy"), Sprite.getSprite(2, 2, "enemy"), Sprite.getSprite(3, 2, "enemy"),
                Sprite.getSprite(0, 2, "enemy"), Sprite.getSprite(1, 3, "enemy"), Sprite.getSprite(2, 3, "enemy"), Sprite.getSprite(3, 3, "enemy")
        };
    }

    private class IA {
        Character.Direction nextDirection;
        Random random;
        int delay;

        public IA() {
            random = new Random();
            delay = 0;
        }

        public Character.Direction nextDirection() {
            if (delay < 50) {
                delay++;
                return null;
            } else {
                delay = 0;
                return Character.Direction.get(random.nextInt(5));
            }
        }


    }
}
