package com.gatedInc.game.view.tiles;

import com.gatedInc.game.model.Enemy;
import com.gatedInc.game.model.Items.Chest;
import com.gatedInc.game.utils.Camera;
import com.gatedInc.game.utils.Vector;
import com.gatedInc.game.view.tiles.tilesBlocks.Tile;
import com.gatedInc.game.view.tiles.tilesBlocks.doors.Doors;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public interface TileMap {

    ArrayList<Tile> getTile();

    ArrayList<ObjectView> getObjects();

    ArrayList<Doors> getTopDoors();

    ArrayList<Doors> getBotDoors();

    ArrayList<Chest> getChest();

    Enemy getEnemy();

    Vector getPosStartPlayer();

    void render(GraphicsContext g, Camera camera);

}
