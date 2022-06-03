package com.gatedInc.game.model.Inventory;

import com.gatedInc.game.model.Items.Item;
import com.gatedInc.game.utils.Vector;
import javafx.scene.canvas.GraphicsContext;


public class InventorySlot {

    Vector coordinate;
    private Item item;
    private final int id;


    public InventorySlot(Vector coordinate, Item item, int id) {
        this.coordinate = coordinate;
        this.item = item;
        this.id = id;
    }


    public void render(GraphicsContext context) {
        item.render(context, this);
    }

    public void deleteItem() {
        item = null;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Vector getCoordinate() {
        return coordinate;
    }

    public int getId() {
        return id;
    }
}
