package com.gatedInc.game.controller;

import com.gatedInc.game.controller.state.*;
import com.gatedInc.game.view.InputHandler;
import com.gatedInc.game.view.stateView.GameStateViewManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

public class GameStateManager {


    private State state;
    private final GameStateViewManager gameStateViewManager;


    public GameStateManager(GraphicsContext context, AnchorPane anchorPane) {
        this.gameStateViewManager = new GameStateViewManager(this, context, anchorPane);

    }


    public State getState() {
        return state;
    }

    public void setState(GAME_STATE gameState) {
        switch (gameState) {
            case PLAY: {
                if (state instanceof PauseState) {
                    PauseState p = (PauseState) state;
                    state = p.resetPlayState();
                    gameStateViewManager.updateGameStateView();
                } else if (state instanceof FightState) {
                    FightState f = (FightState) state;
                    state = f.resetPlayState();
                    gameStateViewManager.updateGameStateView();
                } else {
                    state = new PlayState(this, gameStateViewManager.getAnchorPane());
                    gameStateViewManager.updateGameStateView();
                }

            }
            break;
            case PAUSE: {
                if (state instanceof PlayState) {
                    state = new PauseState(this, state);
                    gameStateViewManager.updateGameStateView();
                }
            }
            break;
            case FIGHT: {
                PlayState p = (PlayState) state;
                state = new FightState(this, GAME_STATE.FIGHT, p.getPlayer(), p.getTm().getTm().getEnemy(), true, p);
                gameStateViewManager.updateGameStateView();
            }
            break;
            case GAME_OVER:
                state = new GameOverState(this, GAME_STATE.GAME_OVER);
                gameStateViewManager.updateGameStateView();
        }
    }

    public void tick(double dt) {
        processPause();
        state.processInput();
        state.update(dt);
        gameStateViewManager.render();
    }

    private void processPause() {
        if (InputHandler.getPause()) {
            if (!(state instanceof PauseState)) {
                setState(GAME_STATE.PAUSE);
            }

        } else if (!InputHandler.getPause() && state instanceof PauseState) setState(GAME_STATE.PLAY);
    }

    public enum GAME_STATE {
        PLAY,
        PAUSE,
        GAME_OVER,
        FIGHT;
    }
}
