package com.gatedInc.game.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle {

    private final int width;
    private final int height;
    private Vector pos;

    public Rectangle(int width, int height, Vector pos) {
        this.width = width;
        this.height = height;
        this.pos = pos;
    }

    public boolean isCollide(Rectangle rectangle) {
        return !(pos.x > rectangle.pos.x + rectangle.width ||
                pos.x + width < rectangle.pos.x ||
                pos.y > rectangle.pos.y + rectangle.height ||
                pos.y + height < rectangle.pos.y);
    }

    public void update(Vector pos) {
        this.pos = pos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector getPos() {
        return pos;
    }

    public void updatePos(Vector v) {
        pos = pos.add(v);
    }

    public void render(GraphicsContext context, Camera camera, int tileSize) {
        context.setFill(Color.WHITE);
        context.fillRect(pos.x * tileSize - camera.getPos().x, pos.y * tileSize - camera.getPos().y, width * tileSize, height * tileSize);
    }

}
