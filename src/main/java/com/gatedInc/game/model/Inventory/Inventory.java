package com.gatedInc.game.model.Inventory;

import com.gatedInc.game.model.Items.Item;
import com.gatedInc.game.model.Player;
import com.gatedInc.game.utils.Vector;
import com.gatedInc.game.view.InventoryView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Inventory {

    private final int SLOT_NUMBER = 15;
    private int x, y;
    private ArrayList<InventorySlot> inventorySlots;
    private InventoryView inventoryView;
    private InventorySlot selectedSlot;
    private final Player player;

    private AnchorPane anchorPane;

    public Inventory(ArrayList<Vector> slots, AnchorPane anchorPane, Player player) {
        initializeSlots(slots, anchorPane);
        this.player = player;
    }

    public void addItem(Item item) {
        for (InventorySlot s : inventorySlots) {
            if (s.getItem() == null) {
                s.setItem(item);
                return;
            }
        }
    }

    public void initializeSlots(ArrayList<Vector> slots, AnchorPane anchorPane) {
        inventorySlots = new ArrayList<>();
        int id = 0;
        this.anchorPane = anchorPane;

        Label description = new Label();
        description.setLayoutX(440);
        description.setLayoutY(512);
        description.setWrapText(true);
        description.setMaxSize(233, 98);
        description.setTextFill(Color.web("#94F3E4"));

        Button use = new Button();
        use.setLayoutX(705);
        use.setLayoutY(515);
        use.setMinSize(91, 43);
        use.setOpacity(0);

        use.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                useItem(selectedSlot, description);
            }
        });

        Button drop = new Button();
        drop.setLayoutX(705);
        drop.setLayoutY(564);
        drop.setMinSize(91, 43);
        drop.setOpacity(0);

        drop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dropItem(selectedSlot, description);
            }
        });

        anchorPane.getChildren().add(description);
        anchorPane.getChildren().add(use);
        anchorPane.getChildren().add(drop);

        for (Vector v : slots) {
            createNewSlot(v, description, id);
            id++;
        }
    }

    public void setSelectedSlot(InventorySlot id) {
        this.selectedSlot = id;
    }

    public void dropItem(InventorySlot inventorySlot, Label description) {
        if (inventorySlot == null || inventorySlot.getItem() == null)
            description.setText("You cannot drop nothing");
        else {
            description.setText("You successfully deleted " + inventorySlot.getItem().getItemName() + ".");
            inventorySlot.deleteItem();
            updateInventory();
        }

    }

    public void useItem(InventorySlot inventorySlot, Label description) {
        if (inventorySlot == null || inventorySlot.getItem() == null)
            description.setText("You cannot use nothing.");
        else {
            switch (inventorySlot.getItem().getItemName()) {
                case "Healing Potion":
                    player.getHealth().heal();
                    inventorySlot.deleteItem();
                    description.setText("Your health is now " + player.getHealth().getCurrentHealth() + " HP.");
                    updateInventory();
                    break;

                case "Toxic waste":
                    player.getStrength().getStronger();
                    inventorySlot.deleteItem();
                    description.setText("Your strength is now " + player.getStrength().getCurrentStrength() + ".");
                    updateInventory();
                    break;

                default:
                    System.out.println(inventorySlot.getItem().getItemName());
            }
        }
    }

    public String printItemDescription(InventorySlot slot) {
        if (slot.getItem() == null) {
            return "There aren't any item in that slot...";
        } else
            return slot.getItem().getDescription();
    }


    public void displayItemIcon(GraphicsContext context) {
        for (int i = 0; i < SLOT_NUMBER; i++) {
            if (inventorySlots.get(i).getItem() != null) {
                inventorySlots.get(i).render(context);
            }
        }
    }

    public void updateInventory() {
        for (int slot = selectedSlot.getId() + 1; slot < SLOT_NUMBER; slot++) {
            addItem(inventorySlots.get(slot).getItem());
            inventorySlots.get(slot).setItem(null);
        }

    }

    public void createNewSlot(Vector v, Label description, int id) {
        inventorySlots.add(new InventorySlot(v, null, id));
        Button button = new Button();
        button.setLayoutX(v.x);
        button.setLayoutY(v.y);
        button.setMinSize(53, 53);
        button.setOpacity(0);

        int tempId = id;
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSelectedSlot(inventorySlots.get(tempId));
                description.setText(printItemDescription(inventorySlots.get(tempId)));
            }
        });
        anchorPane.getChildren().add(button);
    }


}
