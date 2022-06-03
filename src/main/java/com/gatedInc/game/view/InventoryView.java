package com.gatedInc.game.view;

import com.gatedInc.game.model.Inventory.Inventory;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class InventoryView {

    Inventory inventory;
    private final Image inventoryClosed;
    private final Image inventoryOpen;
    private final AnchorPane anchorPane;

    public InventoryView(Inventory inventory, AnchorPane anchorPane) {
        inventoryClosed = loadClosedInventory();
        inventoryOpen = loadOpenInventory();
        this.inventory = inventory;
        this.anchorPane = anchorPane;
    }

    public void render(GraphicsContext context) {
        if (InputHandler.isInventoryOpen()) {
            context.drawImage(inventoryOpen, 0, 0);
            inventory.displayItemIcon(context);
            anchorPane.getChildren().get(1).setVisible(true);
            anchorPane.getChildren().get(2).setVisible(true);
            anchorPane.getChildren().get(3).setVisible(true);
        } else {
            context.drawImage(inventoryClosed, 0, 0);
            anchorPane.getChildren().get(1).setVisible(false);
            anchorPane.getChildren().get(2).setVisible(false);
            anchorPane.getChildren().get(3).setVisible(false);
        }
    }

    public Image loadOpenInventory() {
        Image inventoryOpen = null;
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/ui/inventory_open.png");
            inventoryOpen = new Image(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inventoryOpen;
    }

    public Image loadClosedInventory() {
        Image inventoryClosed = null;
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/ui/inventory_close.png");
            inventoryClosed = new Image(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inventoryClosed;
    }

}
