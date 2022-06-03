package com.gatedInc.game.model.Items;

import com.gatedInc.game.utils.Rectangle;
import com.gatedInc.game.utils.Vector;
import com.gatedInc.game.view.tiles.tilesBlocks.ChestView;

import java.util.ArrayList;
import java.util.Random;


public class Chest {

    private final ArrayList<Item> items;
    private final Vector position;
    private final ChestView chestView;
    private final Rectangle rectangle;
    private final int width;
    private final int height;

    public Chest(int x, int y) {
        width = 54;
        height = 54;
        position = new Vector(x, y);
        chestView = new ChestView(this);
        rectangle = new Rectangle(width, height, position);
        items = new ArrayList<>();
        generateChestContent();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ChestView getChestView() {
        return chestView;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean isEmpty() {
        return items.size() == 0;
    }

    public Vector getPosition() {
        return position;
    }

    private void generateChestContent() {
        Random random = new Random();
        int tempRandom = random.nextInt(2);
        if (tempRandom == 1) {
            addItem(new HealingPotion());
        } else
            addItem(new ToxicWaste());
    }
}
