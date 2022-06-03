package com.gatedInc.game.model.Items;


import com.gatedInc.game.model.Inventory.InventorySlot;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Item {
    private String description;
    private String itemName;
    private String iconPath;
    private Image icon;

    public Item() {
        icon = loadIcon();
    }

    public void render(GraphicsContext context, InventorySlot slot){
        context.drawImage(icon, slot.getCoordinate().getX(), slot.getCoordinate().getY(), 52, 52);
    }

    public abstract String getDescription();

    public abstract String getItemName();

    public abstract Image loadIcon();


}
