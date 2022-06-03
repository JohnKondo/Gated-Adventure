package com.gatedInc.game.controller.state;

import com.gatedInc.game.controller.GameStateManager;
import com.gatedInc.game.view.InputHandler;
import javafx.scene.input.KeyCode;

public class GameOverState extends State {

    public GameOverState(GameStateManager gsm, GameStateManager.GAME_STATE gs) {
        super(gsm, gs);
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void processInput() {
        if (InputHandler.getActiveKeys().contains(KeyCode.N)) {
            gsm.setState(GameStateManager.GAME_STATE.PLAY);
        }
    }
}
