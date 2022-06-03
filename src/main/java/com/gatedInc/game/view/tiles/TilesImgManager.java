package com.gatedInc.game.view.tiles;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class TilesImgManager {

    private static HashMap<FloorTilesType, Image> tileFloorImg;
    private static HashMap<WallTilesType, Image> tileWallImg;
    private static HashMap<ChestType, Image> tileChestImg;
    private static Image dialog;
    private static Image fight;
    private static Image player;
    private static Image enemy;

    public static void loadTiles() {
        tileFloorImg = TilesLoader.loadFloorTiles();
        tileWallImg = TilesLoader.loadWallTiles();
        tileChestImg = TilesLoader.loadChestTiles();
        dialog = TilesLoader.loadDialog();
        fight = TilesLoader.loadFight();
        player = TilesLoader.loadPlayer();
        enemy = TilesLoader.loadEnemy();
    }

    public static Image getFloorImg(FloorTilesType floorType) {
        return tileFloorImg.get(floorType);
    }

    public static Image getWallImg(WallTilesType wallTilesType) {
        return tileWallImg.get(wallTilesType);
    }

    public static Image getChest(ChestType chestType) {
        return tileChestImg.get(chestType);
    }

    public static Image getDialog() {
        return dialog;
    }

    public static Image getFight() {
        return fight;
    }

    public static Image getPlayer() {
        return player;
    }

    public static Image getEnemy() {
        return enemy;
    }

    public enum ChestType {
        full,
        empty;

        int index;

        public int getIndex() {
            return index;
        }

        /*FloorTilesType(int index){
            this.index = index;
        }*/
    }


    public enum FloorTilesType {
        BASIC,
        MANHOLE,
        VENTRAD,
        BLOOD1,
        RAD,
        VENT,
        TILES,
        BLOOD2,
        WATER,
        WIRE;

        int index;

        public int getIndex() {
            return index;
        }

        /*FloorTilesType(int index){
            this.index = index;
        }*/
    }

    public enum WallTilesType {
        BOTTOM_LEFT(1),
        BOTTOM_RIGHT(2),
        TOP_LEFT(3),
        TOP_RIGHT(4),
        NORMAL_WALL(5),
        RARE_WALL(6),
        LEFT_WALL(7),
        RIGHT_WALL(8);

        private static final Map<Integer, WallTilesType> lookup = new HashMap<Integer, WallTilesType>();

        static {
            for (WallTilesType d : WallTilesType.values()) {
                lookup.put(d.getIndex(), d);
            }
        }

        int index;

        WallTilesType(int index) {
            this.index = index;
        }

        public static WallTilesType get(int index) {
            return lookup.get(index);
        }

        public int getIndex() {
            return index;
        }

    }

}
