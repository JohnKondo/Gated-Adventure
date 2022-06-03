package com.gatedInc.game.view.map;

import com.gatedInc.game.model.Enemy;
import com.gatedInc.game.model.Items.Chest;
import com.gatedInc.game.utils.Camera;
import com.gatedInc.game.utils.Rectangle;
import com.gatedInc.game.utils.Vector;
import com.gatedInc.game.view.tiles.ObjectView;
import com.gatedInc.game.view.tiles.TileMap;
import com.gatedInc.game.view.tiles.TilesImgManager;
import com.gatedInc.game.view.tiles.tilesBlocks.Container;
import com.gatedInc.game.view.tiles.tilesBlocks.FloorTile;
import com.gatedInc.game.view.tiles.tilesBlocks.Tile;
import com.gatedInc.game.view.tiles.tilesBlocks.Wall;
import com.gatedInc.game.view.tiles.tilesBlocks.doors.Doors;
import com.gatedInc.game.view.tiles.tilesBlocks.doors.LeftDoor;
import com.gatedInc.game.view.tiles.tilesBlocks.doors.RightDoor;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Random;

public class Map implements TileMap {
    private static final int SPECIAL_FLOOR_PROBA = 10;
    private static final int TILE_SIZE = 54;
    private static final int CONTAINER_PROBA = 2;
    private static final int CHESS_PROBA = 10;
    private final ArrayList<Tile> tiles;
    private final ArrayList<ObjectView> objects;
    private final ArrayList<Chest> chests;
    private final ArrayList<Doors> topDoors;
    private final ArrayList<Doors> botDoors;
    private Vector posStartPlayer;
    private final Random rd;
    private final int seed;
    private Enemy enemy;


    public Map(int seed, boolean top) {
        this.seed = seed;
        rd = new Random(seed);
        tiles = new ArrayList<>();
        objects = new ArrayList<>();
        topDoors = new ArrayList<>();
        botDoors = new ArrayList<>();
        chests = new ArrayList<>();
        creatMap(top);
    }


    public void creatMap(boolean top) {
        creatRoom(createRandomRectangle(), top);
    }


    private Rectangle createRandomRectangle() {
        int width = randomBetween(15, 30);
        int height = randomBetween(15, 15);

        if (width % 2 != 0) {
            width++; // for center doors
        }

        return new Rectangle(width, height, new Vector(0, 0));
    }

    private int randomBetween(int min, int max) {
        int mi = Math.abs(min);
        int ma = Math.abs(max);

        if (mi > ma) {
            return rd.nextInt(mi - ma) + ma;
        } else {
            return rd.nextInt(ma - mi + 1) + mi;
        }
    }

    private ArrayList<Tile> creatRoom(Rectangle room, boolean top) {
        ArrayList<Tile> provRes = new ArrayList<>();

        int chestCount = 0;

        int width = room.getWidth();
        int height = room.getHeight();

        int xPos = (int) room.getPos().x * TILE_SIZE;
        int yPos = (int) room.getPos().y * TILE_SIZE;

        int xTileNumber = width;
        int yTileNumber = height;

        if (xTileNumber < 1) xTileNumber = 1;
        if (yTileNumber < 1) yTileNumber = 1;

        if (top) {
            posStartPlayer = new Vector(TILE_SIZE * ((xTileNumber - 1) / 2), 0 + TILE_SIZE);
        } else {
            posStartPlayer = new Vector(TILE_SIZE * ((xTileNumber - 1) / 2), yTileNumber * TILE_SIZE - TILE_SIZE * 3);
        }


        for (int x = 0; x < xTileNumber * TILE_SIZE; x += TILE_SIZE) {
            for (int y = 0; y < yTileNumber * TILE_SIZE; y += TILE_SIZE) {
                boolean doorBool = x == TILE_SIZE * ((xTileNumber - 1) / 2) || x == TILE_SIZE * ((xTileNumber) / 2);

                if (x == TILE_SIZE * ((xTileNumber - 1) / 2) && (y == 0)) {
                    if (seed == 0) {
                        provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.NORMAL_WALL), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                    } else {
                        LeftDoor l = new LeftDoor(null, new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE);
                        topDoors.add(l);
                        objects.add(l);// top IN
                    }

                    //provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.RARE_WALL), new Vector(x + xPos,y + yPos), TILE_SIZE, TILE_SIZE));
                } else if (x == TILE_SIZE * ((xTileNumber) / 2) && (y == 0)) {
                    if (seed == 0) {
                        provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.NORMAL_WALL), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                    } else {
                        RightDoor l = new RightDoor(null, new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE);
                        topDoors.add(l);
                        objects.add(l);
                    }
                    // top IN
                    //provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.RARE_WALL), new Vector(x + xPos,y + yPos), TILE_SIZE, TILE_SIZE));
                } else if (x == TILE_SIZE * ((xTileNumber - 1) / 2) && (y == TILE_SIZE * (yTileNumber - 1))) { // bottom out
                    LeftDoor l = new LeftDoor(null, new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE);
                    botDoors.add(l);
                    objects.add(l);
                    //provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.LEFT_WALL), new Vector(x + xPos,y + yPos), TILE_SIZE, TILE_SIZE));
                } else if (x == TILE_SIZE * ((xTileNumber) / 2) && (y == TILE_SIZE * (yTileNumber - 1))) {
                    RightDoor l = new RightDoor(null, new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE);
                    botDoors.add(l);
                    objects.add(l);// top IN
                    //provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.RARE_WALL), new Vector(x + xPos,y + yPos), TILE_SIZE, TILE_SIZE));
                } else if (x == 0 && y != 0 && y != TILE_SIZE * (yTileNumber - 1)) {
                    provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.LEFT_WALL), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                } else if (y == 0 && x == 0) {
                    provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.TOP_LEFT), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                } else if (y == 0 && x != TILE_SIZE * (xTileNumber - 1) && x != TILE_SIZE * ((xTileNumber - 1) / 2) && x != TILE_SIZE * (xTileNumber / 2)) {
                    provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.NORMAL_WALL), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                } else if (x == TILE_SIZE * (xTileNumber - 1) && y == 0) {
                    provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.TOP_RIGHT), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                } else if (x == TILE_SIZE * (xTileNumber - 1) && y != 0 && y != TILE_SIZE * (yTileNumber - 1)) {
                    provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.RIGHT_WALL), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                } else if (x == 0 && y == TILE_SIZE * (yTileNumber - 1)) {
                    provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.BOTTOM_LEFT), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                } else if (y == TILE_SIZE * (yTileNumber - 1) && x != TILE_SIZE * (xTileNumber - 1)) {
                    provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.NORMAL_WALL), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                } else if (x == TILE_SIZE * (xTileNumber - 1) && y == TILE_SIZE * (yTileNumber - 1)) {
                    provRes.add(new Wall(TilesImgManager.getWallImg(TilesImgManager.WallTilesType.BOTTOM_RIGHT), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                } else {
                    if (rd.nextInt(100) < 100 - SPECIAL_FLOOR_PROBA) {
                        provRes.add(new FloorTile(TilesImgManager.getFloorImg(TilesImgManager.FloorTilesType.BASIC), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                    } else {
                        provRes.add(new FloorTile(TilesImgManager.getFloorImg(getSpecialFloor()), new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                    }

                    if (!(x == TILE_SIZE * ((xTileNumber - 1) / 2) || x == TILE_SIZE * ((xTileNumber) / 2))) {
                        if (rd.nextInt(100) < CONTAINER_PROBA) {
                            objects.add(new Container(null, new Vector(x + xPos, y + yPos), TILE_SIZE, TILE_SIZE));
                        } else if (rd.nextInt(100) < CHESS_PROBA && chestCount == 0) {
                            chests.add(new Chest(x + xPos, y + yPos));
                            chestCount++;
                        }
                    }

                }
            }
        }
        enemy = new Enemy(TILE_SIZE * ((xTileNumber - 1) / 2), yTileNumber * TILE_SIZE - TILE_SIZE * 3, 8f);
        tiles.addAll(provRes);
        return tiles;
    }


    private TilesImgManager.FloorTilesType getSpecialFloor() {
        switch (rd.nextInt(8)) {
            case 0:
                return TilesImgManager.FloorTilesType.BLOOD1;

            case 1:
                return TilesImgManager.FloorTilesType.BLOOD2;

            case 2:
                return TilesImgManager.FloorTilesType.MANHOLE;

            case 3:
                return TilesImgManager.FloorTilesType.RAD;

            case 4:
                return TilesImgManager.FloorTilesType.WATER;

            case 5:
                return TilesImgManager.FloorTilesType.WIRE;

            case 6:
                return TilesImgManager.FloorTilesType.VENTRAD;

            case 7:
                return TilesImgManager.FloorTilesType.VENT;

        }
        return null;
    }

    public Vector getPosStartPlayer() {
        return posStartPlayer;
    }

    @Override
    public ArrayList<Tile> getTile() {
        return tiles;
    }

    @Override
    public ArrayList<ObjectView> getObjects() {
        return objects;
    }

    @Override
    public ArrayList<Doors> getTopDoors() {
        return topDoors;
    }

    @Override
    public ArrayList<Doors> getBotDoors() {
        return botDoors;
    }

    @Override
    public ArrayList<Chest> getChest() {
        return chests;
    }

    @Override
    public void render(GraphicsContext g, Camera camera) {
        for (Tile tile : tiles) {
            tile.render(g, camera);
        }
        for (ObjectView o : objects) {
            o.render(g, camera);
        }
        for (Chest c : chests) {
            c.getChestView().render(g, camera);
        }

        g.drawImage(enemy.getAnimation().getSprite(), enemy.getPosition().x - camera.getPos().x, enemy.getPosition().y - camera.getPos().y, enemy.getWidth(), enemy.getHeight());

    }

    public Enemy getEnemy() {
        return enemy;
    }
}
