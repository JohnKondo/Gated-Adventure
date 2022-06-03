package com.gatedInc.game.view.tiles;

import com.gatedInc.game.utils.Camera;
import com.gatedInc.game.utils.Vector;
import com.gatedInc.game.view.map.Map;
import com.gatedInc.game.view.tiles.tilesBlocks.Tile;
import com.gatedInc.game.view.tiles.tilesBlocks.Wall;
import com.gatedInc.game.view.tiles.tilesBlocks.doors.Doors;
import javafx.scene.canvas.GraphicsContext;

public class TileManager {
    TileMap tm;
    int seedCount = 0;
    Camera cam;

    public TileManager(Camera cam) {
        this.cam = cam;
        mapGen(true);
    }

    public void update(double dt) {
        for (ObjectView o : tm.getObjects()) {
            o.update(dt);
        }

        tm.getEnemy().update(dt);
        for (Tile t : tm.getTile()) {
            if (t instanceof Wall || t instanceof Doors) {
                if (tm.getEnemy().getRectangle().isCollide(((Wall) t).getBounds())) {
                    tm.getEnemy().restorePosition();
                }
            }
        }
    }

    public void mapGen(boolean top) {
        tm = new Map(seedCount, top);

    }

    public Vector getPosStartPlayer() {
        return tm.getPosStartPlayer();
    }

    public TileMap getTm() {
        return tm;
    }

    public void render(GraphicsContext g, Camera cam) {
        tm.render(g, cam);
    }

    public void prevMap() {
        seedCount--;
        mapGen(false);
    }

    public void nextMap() {
        seedCount++;
        mapGen(true);
    }
}
