package com.gatedInc.game.model;

import com.gatedInc.game.graphic.Sprite;
import com.gatedInc.game.model.CharactersAttributes.Health;
import com.gatedInc.game.model.CharactersAttributes.Strength;
import com.gatedInc.game.model.Inventory.Inventory;
import com.gatedInc.game.model.Items.Chest;
import com.gatedInc.game.model.Items.HealingPotion;
import com.gatedInc.game.model.Items.Item;
import com.gatedInc.game.model.Items.ToxicWaste;
import com.gatedInc.game.utils.Vector;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Player extends Character {

    Inventory inventory;
    ArrayList<Vector> slots;

    Item item;
    HealingPotion healingPotion;
    ToxicWaste toxicWaste;

    AnchorPane anchorPane;
    String playerMessage;


    public Player(int x, int y, float speed, AnchorPane anchorPane) {
        super(x, y, speed);

        slots = new ArrayList<Vector>();
        this.anchorPane = anchorPane;
        loadSlots();
        inventory = new Inventory(slots, anchorPane, this);
        healingPotion = new HealingPotion();
        toxicWaste = new ToxicWaste();

    }

    @Override
    public void setAnimationImages() {
        Sprite.resetSpriteLoader();
        walkingLeft = new Image[]{Sprite.getSprite(1, 1, "player"), Sprite.getSprite(0, 1, "player"), Sprite.getSprite(2, 1, "player")};
        walkingRight = new Image[]{Sprite.getSprite(1, 2, "player"), Sprite.getSprite(0, 2, "player"), Sprite.getSprite(2, 2, "player")};
        walkingDown = new Image[]{Sprite.getSprite(1, 0, "player"), Sprite.getSprite(0, 0, "player"), Sprite.getSprite(2, 0, "player")};
        walkingUp = new Image[]{Sprite.getSprite(1, 3, "player"), Sprite.getSprite(0, 3, "player"), Sprite.getSprite(2, 3, "player")};
        standing = new Image[]{Sprite.getSprite(1, 0, "player")};
    }

    public Health getHealth() {
        return health;
    }

    public Strength getStrength() {
        return strength;
    }


    private void loadSlots() {
        slots.add(new Vector(439, 254));
        slots.add(new Vector(513, 254));
        slots.add(new Vector(587, 254));
        slots.add(new Vector(661, 254));
        slots.add(new Vector(735, 254));
        slots.add(new Vector(439, 328));
        slots.add(new Vector(513, 328));
        slots.add(new Vector(587, 328));
        slots.add(new Vector(661, 328));
        slots.add(new Vector(735, 328));
        slots.add(new Vector(439, 402));
        slots.add(new Vector(513, 402));
        slots.add(new Vector(587, 402));
        slots.add(new Vector(661, 402));
        slots.add(new Vector(735, 402));
    }

    public void addItemsInInventory(Chest chest) {
        ArrayList<Item> remove = new ArrayList<>();
        for (Item item : chest.getItems()) {
            remove.add(item);
            updateString(item.getItemName());
            inventory.addItem(item);
        }
        for (Item item : remove) {
            chest.removeItem(item);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public String getPlayerMessage() {
        return playerMessage;
    }

    private void updateString(String string) {
        playerMessage = "You just found a " + string + ".";
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> playerMessage = null);
        pause.play();
    }
}
