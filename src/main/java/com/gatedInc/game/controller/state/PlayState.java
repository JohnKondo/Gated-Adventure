package com.gatedInc.game.controller.state;

import com.gatedInc.game.controller.GameStateManager;
import com.gatedInc.game.graphic.FPS;
import com.gatedInc.game.model.Items.Chest;
import com.gatedInc.game.model.Player;
import com.gatedInc.game.utils.Camera;
import com.gatedInc.game.view.InputHandler;
import com.gatedInc.game.view.tiles.ObjectView;
import com.gatedInc.game.view.tiles.TileManager;
import com.gatedInc.game.view.tiles.TileMap;
import com.gatedInc.game.view.tiles.tilesBlocks.Container;
import com.gatedInc.game.view.tiles.tilesBlocks.Tile;
import com.gatedInc.game.view.tiles.tilesBlocks.Wall;
import com.gatedInc.game.view.tiles.tilesBlocks.doors.Doors;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.util.Set;

public class PlayState extends State {

    private final Player player;
    private final TileManager tm;
    private final FPS fps;
    private final Camera cam;


    public PlayState(GameStateManager gsm, AnchorPane anchorPane) {
        super(gsm, GameStateManager.GAME_STATE.PLAY);
        this.cam = new Camera();
        tm = new TileManager(cam);
        player = new Player((int) tm.getTm().getPosStartPlayer().x, (int) tm.getTm().getPosStartPlayer().y, 4f, anchorPane);
        cam.setTarget(player);

        fps = new FPS();
    }

    @Override
    public void update(double dt) {
        cam.update(dt);
        tm.update(dt);
        player.update(dt);
        checkCollision();
        fps.update(dt);


    }

    public Camera getCam() {
        return cam;
    }

    private void nextMap() {
        tm.nextMap();
        player.setPosition(tm.getPosStartPlayer());
        cam.setTarget(player);
    }

    private void prevMap() {
        tm.prevMap();
        player.setPosition(tm.getPosStartPlayer());
        cam.setTarget(player);
    }


    private void checkCollision() {
        TileMap t = tm.getTm();
        if (t.getEnemy().isAlive()) {
            if (player.getRectangle().isCollide(t.getEnemy().getRectangle())) {
                gsm.setState(GameStateManager.GAME_STATE.FIGHT);
            }
        }

        for (Tile tile : t.getTile()) {
            if (tile instanceof Wall) {
                if (player.getRectangle().isCollide(((Wall) tile).getBounds())) {
                    player.restorePosition();

                    return;
                }

            }
        }
        for (ObjectView objectView : t.getObjects()) {
            if (objectView instanceof Container) {
                if (player.getRectangle().isCollide(((Container) objectView).getRectangle())) {
                    player.restorePosition();
                    return;
                }
            }
        }
        for (Doors o : t.getTopDoors()) {
            if (player.getRectangle().isCollide(o.getRectangle())) {
                for (Doors ob : t.getTopDoors()) {
                    if (ob.setOpenAnim()) {
                        prevMap();
                        break;
                    }
                    player.restorePosition();
                }
                break;
            }
        }
        for (Doors o : t.getBotDoors()) {
            if (player.getRectangle().isCollide(o.getRectangle())) {
                for (Doors ob : t.getBotDoors()) {
                    if (ob.setOpenAnim()) {
                        nextMap();
                        break;
                    }
                    player.restorePosition();
                }
                break;
            }
        }
        for (Chest o : t.getChest()) {
            if (player.getRectangle().isCollide(o.getRectangle())) {
                player.addItemsInInventory(o);
                player.restorePosition();
            }
        }

    }


    public Player getPlayer() {
        return player;
    }

    public TileManager getTm() {
        return tm;
    }

    public FPS getFps() {
        return fps;
    }

    @Override
    public void processInput() {
        if (InputHandler.isInventoryOpen()) {
            player.stand();
            return;
        }
        Set<KeyCode> activeKeys = InputHandler.getActiveKeys();
        if (activeKeys.contains(KeyCode.SHIFT)) {
            player.setSprint();
        } else player.unsetSprint();
        if (activeKeys.contains(KeyCode.Z)) {
            player.goNorth();
        } else if (activeKeys.contains(KeyCode.S)) {
            player.goSouth();
        } else if (activeKeys.contains(KeyCode.Q)) {
            player.goWest();
        } else if (activeKeys.contains(KeyCode.D)) {
            player.goEast();
        } else {
            player.stand();
        }
    }
}
