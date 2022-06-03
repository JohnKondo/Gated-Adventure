package com.gatedInc.game.view.stateView;

import com.gatedInc.game.controller.GameStateManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

public class GameStateViewManager {

    private StateView stateView;
    private final GameStateManager gsm;
    private final GraphicsContext context;
    private AnchorPane anchorPane;

    public GameStateViewManager(GameStateManager gameStateManager, GraphicsContext context, AnchorPane anchorPane) {
        gsm = gameStateManager;
        this.context = context;
        this.anchorPane = anchorPane;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void updateGameStateView() {
        if(stateView instanceof FightStateView){
            anchorPane.getChildren().remove(((FightStateView) stateView).getFightText());
        }
        switch (gsm.getState().getGameState()) {
            case PLAY:
                stateView = new PlaySateView(this);
                break;
            case PAUSE:
                stateView = new PauseStateView();
                break;
            case GAME_OVER:
                stateView = new GameOverStateView();
                break;
            case FIGHT:
                stateView = new FightStateView(this);
                break;
        }
    }

    public void render() {
        stateView.render(context);
    }

    public GraphicsContext getContext() {
        return context;
    }

    public GameStateManager getGsm() {
        return gsm;
    }

    public StateView getStateView() {
        return stateView;
    }

    public void setStateView(StateView stateView) {
        this.stateView = stateView;
    }
}
