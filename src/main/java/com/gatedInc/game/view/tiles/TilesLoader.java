package com.gatedInc.game.view.tiles;

import com.gatedInc.game.graphic.Sprite;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class TilesLoader {


    public static HashMap<TilesImgManager.ChestType, Image> loadChestTiles() {
        Sprite.resetSpriteLoader();
        HashMap<TilesImgManager.ChestType, Image> t = new HashMap<TilesImgManager.ChestType, Image>();
        t.put(TilesImgManager.ChestType.full, Sprite.getSprite(0, 0, "Items/chest"));
        t.put(TilesImgManager.ChestType.empty, Sprite.getSprite(1, 0, "Items/chest"));
        return t;
    }

    public static HashMap<TilesImgManager.FloorTilesType, Image> loadFloorTiles() {
        Sprite.resetSpriteLoader();
        HashMap<TilesImgManager.FloorTilesType, Image> t = new HashMap<TilesImgManager.FloorTilesType, Image>();
        t.put(TilesImgManager.FloorTilesType.BASIC, Sprite.getSprite(0, 0, "floor"));
        t.put(TilesImgManager.FloorTilesType.MANHOLE, Sprite.getSprite(1, 0, "floor"));
        t.put(TilesImgManager.FloorTilesType.VENTRAD, Sprite.getSprite(2, 0, "floor"));
        t.put(TilesImgManager.FloorTilesType.BLOOD1, Sprite.getSprite(3, 0, "floor"));
        t.put(TilesImgManager.FloorTilesType.RAD, Sprite.getSprite(4, 0, "floor"));
        t.put(TilesImgManager.FloorTilesType.VENT, Sprite.getSprite(0, 1, "floor"));
        t.put(TilesImgManager.FloorTilesType.TILES, Sprite.getSprite(1, 1, "floor"));
        t.put(TilesImgManager.FloorTilesType.BLOOD2, Sprite.getSprite(2, 1, "floor"));
        t.put(TilesImgManager.FloorTilesType.WATER, Sprite.getSprite(3, 1, "floor"));
        t.put(TilesImgManager.FloorTilesType.WIRE, Sprite.getSprite(4, 1, "floor"));
        return t;
    }


    public static HashMap<TilesImgManager.WallTilesType, Image> loadWallTiles() {
        Sprite.resetSpriteLoader();
        HashMap<TilesImgManager.WallTilesType, Image> t = new HashMap<TilesImgManager.WallTilesType, Image>();
        t.put(TilesImgManager.WallTilesType.BOTTOM_LEFT, Sprite.getSprite(0, 0, "walls"));
        t.put(TilesImgManager.WallTilesType.BOTTOM_RIGHT, Sprite.getSprite(1, 0, "walls"));
        t.put(TilesImgManager.WallTilesType.TOP_LEFT, Sprite.getSprite(2, 0, "walls"));
        t.put(TilesImgManager.WallTilesType.TOP_RIGHT, Sprite.getSprite(3, 0, "walls"));
        t.put(TilesImgManager.WallTilesType.NORMAL_WALL, Sprite.getSprite(0, 1, "walls"));
        t.put(TilesImgManager.WallTilesType.RARE_WALL, Sprite.getSprite(1, 1, "walls"));
        t.put(TilesImgManager.WallTilesType.LEFT_WALL, Sprite.getSprite(2, 1, "walls"));
        t.put(TilesImgManager.WallTilesType.RIGHT_WALL, Sprite.getSprite(3, 1, "walls"));
        return t;
    }

    public static Image loadDialog() {
        Sprite.resetSpriteLoader();
        return Sprite.getSprite(0, 0, "dialog");
    }

    public static Image loadFight() {
        Image fight = null;
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/ui/fight.png");
            fight = new Image(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fight;
    }

    public static Image loadPlayer() {
        Sprite.resetSpriteLoader();
        return Sprite.getSprite(1, 0, "player");
    }

    public static Image loadEnemy() {
        Sprite.resetSpriteLoader();
        return Sprite.getSprite(1, 1, "enemy");
    }
}
