package com.gatedInc.game.model.Items;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class HealingPotion extends Item {
    private final String description = "The Healing Potion is a healing item that restores 2 hearts when used.";
    private final String itemName = "Healing Potion";
    private final String iconPath = "HealingPotion";


    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public Image loadIcon() {
        Image itemIcon = null;
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/spritesheets/Items/"+ iconPath +".png");
            itemIcon = new Image(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return itemIcon;
    }


}
