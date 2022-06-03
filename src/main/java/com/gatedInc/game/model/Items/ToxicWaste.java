package com.gatedInc.game.model.Items;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ToxicWaste extends Item {
    private final String description = "Maybe you should try to spray it all over your sword...";
    private final String itemName = "Toxic waste";
    private final String iconPath = "ToxicWaste";

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
            InputStream inputStream = new FileInputStream("src/main/resources/spritesheets/Items/" + iconPath + ".png");
            itemIcon = new Image(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return itemIcon;
    }
}
