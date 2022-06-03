package com.gatedInc.game.view;

import com.gatedInc.game.controller.GameStateManager;
import com.gatedInc.game.view.stateView.GameStateViewManager;
import com.gatedInc.game.view.tiles.TilesImgManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;


public class JavaFXView {

    private Timer timer;
    private InputHandler inputHandler;
    private GameStateManager gsm;
    private GameStateViewManager gameStateViewManager;

    public void initialize(Canvas canvas, AnchorPane anchorPane) {
        TilesImgManager.loadTiles();
        gsm = new GameStateManager(canvas.getGraphicsContext2D(), anchorPane);
        gsm.setState(GameStateManager.GAME_STATE.PLAY);
        timer = new Timer(this);
        timer.restart();
    }

    public void initializeInputHandler(Scene scene) {
        this.inputHandler = new InputHandler();
        scene.setOnKeyPressed(inputHandler);
        scene.setOnKeyReleased(inputHandler);
    }

    public void tick(double dt) {
        gsm.tick(dt);
    }
}