package com.gatedInc.game.graphic;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.io.FileInputStream;
import java.io.InputStream;

public class Sprite {


    private static Image spriteSheet;
    private static int TILE_SIZE = 32;

    public static Image loadSprite(String file) {
        Image sprite = null;

        try {
            String path = "src/main/resources/spritesheets/" + file + ".png";
            InputStream inputStream = new FileInputStream(path);
            sprite = new Image(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sprite;
    }

    public static void resetSpriteLoader() {
        spriteSheet = null;
    }

    public static Image getSprite(int xGrid, int yGrid, String sprite) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite(sprite);
        }

        switch (sprite) {
            case "player":
                TILE_SIZE = 32;
                break;
            case "floor":
                TILE_SIZE = 96;
                break;
            case "walls":
                TILE_SIZE = 96;
                break;
            case "door":
                TILE_SIZE = 96;
                break;
            case "Items/obj":
                TILE_SIZE = 96;
                break;
            case "Items/chest":
                TILE_SIZE = 75;
                break;
            case "enemy":
                TILE_SIZE = 96;
                break;
            case "dialog":
                TILE_SIZE = 444;
        }
        //HEALTH BAR
        if (sprite.equals("HealthBar/ZERO_HP") || sprite.equals("HealthBar/ONE_HP") ||
                sprite.equals("HealthBar/TWO_HP") || sprite.equals("HealthBar/THREE_HP") ||
                sprite.equals("HealthBar/FOUR_HP") || sprite.equals("HealthBar/FIVE_HP") ||
                sprite.equals("HealthBar/SIZE_HP")) TILE_SIZE = 108;


        return getSubImage(spriteSheet, xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static Image getSubImage(Image img, int x, int y, int w, int h) {
        PixelReader reader = img.getPixelReader();
        return new WritableImage(reader, x, y, w, h);
    }

}
