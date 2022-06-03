package com.gatedInc.game.model;

import com.gatedInc.game.graphic.Animation;
import com.gatedInc.game.model.CharactersAttributes.Health;
import com.gatedInc.game.model.CharactersAttributes.Strength;
import com.gatedInc.game.utils.Rectangle;
import com.gatedInc.game.utils.Vector;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public abstract class Character {

    final float SPEED;
    final float SPRINTSPEED;
    final int height;
    private final int width;
    protected Image[] walkingLeft;
    protected Image[] walkingRight;
    protected Image[] walkingUp;
    protected Image[] walkingDown;
    protected Image[] standing;
    // These are animation states
    protected Animation walkLeft;
    protected Animation walkRight;
    protected Animation standingA;
    protected Animation walkUp;
    protected Animation walkDown;
    // This is the actual animation
    Animation animation;
    boolean sprint = false;
    Vector position;
    Direction direction = Direction.STAND;
    Rectangle rectangle;
    Vector posSave;

    Health health;
    Strength strength;


    public Character(int x, int y, float speed) {

        position = new Vector(x, y);
        this.width = 53;
        this.height = 53;
        this.SPEED = speed;
        SPRINTSPEED = speed / 2;
        rectangle = new Rectangle(width - width / 4, height / 4, position);
        setAnimationImages();
        setAnimation();
        health = new Health();
        strength = new Strength();
    }

    public Character(int x, int y, float speed, int width, int height) {

        position = new Vector(x, y);
        this.width = width;
        this.height = height;
        this.SPEED = speed;
        SPRINTSPEED = speed / 2;
        rectangle = new Rectangle(width - width / 4, height / 4, position);
        setAnimationImages();
        setAnimation();
        health = new Health();
        strength = new Strength();
    }

    public abstract void setAnimationImages();

    public void hit(int strength) {
        health.hit(strength);
    }

    public Strength getStrength() {
        return strength;
    }

    public Health getHealth() {
        return health;
    }

    protected void setAnimation() {
        walkLeft = new Animation(walkingLeft, 10);
        walkRight = new Animation(walkingRight, 10);
        walkUp = new Animation(walkingUp, 10);
        walkDown = new Animation(walkingDown, 10);
        standingA = new Animation(standing, 10);
        animation = standingA;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector v) {
        position = v;
        posSave = position;
        rectangle.update(new Vector(position.x + (rectangle.getWidth() / 6), position.y + height - (height / 4)));
    }

    public Direction getDirection() {
        return direction;
    }

    public void goNorth() {
        animation = walkUp;
        animation.start();
        direction = Direction.NORTH;
    }

    public void goSouth() {
        animation = walkDown;
        animation.start();
        direction = Direction.SOUTH;
    }

    public void goEast() {
        animation = walkRight;
        animation.start();
        direction = Direction.EAST;
    }

    public void goWest() {
        animation = walkLeft;
        animation.start();
        direction = Direction.WEST;
    }

    public void setSprint() {
        sprint = true;
    }

    public void unsetSprint() {
        sprint = false;
    }

    public void stand() {
        direction = Direction.STAND;
        if (animation != null) {
            animation.stop();
            animation.reset();
            //animation = standingA;
        }

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void restorePosition() {
        this.position = posSave;
        rectangle.update(new Vector(position.x + (rectangle.getWidth() / 6), position.y + height - (height / 4)));
    }

    public void update(double dt) {
        posSave = this.position.clone();

        double speed;
        if (sprint) {
            animation.update(5);
            speed = SPRINTSPEED;
        } else {
            animation.update(-1);
            speed = SPEED;
        }

        if (direction == Direction.NORTH) {
            position = position.add(new Vector(0, dt / -speed));
        }

        if (direction == Direction.SOUTH) {
            position = position.add(new Vector(0, dt / speed));
        }
        if (direction == Direction.WEST) {
            position = position.add(new Vector(dt / -speed, 0));
        }

        if (direction == Direction.EAST) {
            position = position.add(new Vector(dt / speed, 0));
        }

        rectangle.update(new Vector(position.x + (rectangle.getWidth() / 6), position.y + height - (height / 4)));


    }

    public Animation getAnimation() {
        return animation;
    }


    enum Direction {
        NORTH(0),
        SOUTH(1),
        EAST(2),
        WEST(3),
        STAND(4);

        private static final Map<Integer, Direction> lookup = new HashMap<Integer, Direction>();

        static {
            for (Direction d : Direction.values()) {
                lookup.put(d.getIndex(), d);
            }
        }

        int index;

        Direction(int index) {
            this.index = index;
        }

        public static Direction get(int index) {
            return lookup.get(index);
        }

        public int getIndex() {
            return index;
        }


    }

}
