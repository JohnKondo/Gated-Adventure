package com.gatedInc.game.utils;

import com.gatedInc.game.App;
import com.gatedInc.game.model.Player;

public class Camera {

    private Vector pos;
    private final float speed;
    private Rectangle rectangle;
    private Player target;


    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;

    public Camera() {
        speed = 4f;
    }


    public void setTarget(Player target) {
        this.target = target;
        int rectangleWidth = 200;
        int rectangleHeight = 200;
        rectangle = new Rectangle(rectangleWidth, rectangleHeight,
                new Vector(target.getPosition().x - ((rectangleWidth / 2) - (target.getWidth() / 2)),
                        target.getPosition().y - ((rectangleHeight / 2) - (target.getHeight() / 2))));
        pos = new Vector(rectangle.getPos().x - ((App.WINDOW_WIDTH / 2) - (rectangle.getWidth() / 2)),
                rectangle.getPos().y - ((App.WINDOW_HEIGHT / 2) - (rectangle.getHeight() / 2)));
    }

    public Vector getPos() {
        return pos;
    }

    public void update(double dt) {
        move(dt);
        left = target.getPosition().x < rectangle.getPos().x;
        right = target.getPosition().x + target.getWidth() > rectangle.getPos().x + rectangle.getWidth();
        up = target.getPosition().y < rectangle.getPos().y;
        down = target.getPosition().y + target.getHeight() * 2 > rectangle.getPos().y + rectangle.getHeight();
    }

    public void move(double dt) {
        if (up) {
            rectangle.updatePos(new Vector(0, dt / -speed));
            pos = new Vector(rectangle.getPos().x - ((App.WINDOW_WIDTH / 2) - (rectangle.getWidth() / 2)),
                    rectangle.getPos().y - ((App.WINDOW_HEIGHT / 2) - (rectangle.getHeight() / 2)));
        }
        if (down) {
            pos = pos.add(new Vector(0, dt / speed));
            rectangle.updatePos(new Vector(0, dt / speed));
        }
        if (right) {
            pos = pos.add(new Vector(dt / speed, 0));
            rectangle.updatePos(new Vector(dt / speed, 0));
        }
        if (left) {
            pos = pos.add(new Vector(dt / -speed, 0));
            rectangle.updatePos(new Vector(dt / -speed, 0));
        }
    }

}
