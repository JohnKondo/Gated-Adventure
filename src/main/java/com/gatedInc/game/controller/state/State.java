package com.gatedInc.game.controller.state;
import com.gatedInc.game.controller.GameStateManager;

public abstract class State {

    private GameStateManager.GAME_STATE gameState;
    protected final GameStateManager gsm;
    public State(GameStateManager gsm, GameStateManager.GAME_STATE gs){
        this.gsm = gsm;
        this.gameState = gs;
    }

    public  GameStateManager.GAME_STATE getGameState(){
        return this.gameState;
    }


    public abstract void update(double dt);
    public abstract void processInput();


}
