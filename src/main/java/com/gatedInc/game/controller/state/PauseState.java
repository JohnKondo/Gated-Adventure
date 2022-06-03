package com.gatedInc.game.controller.state;

import com.gatedInc.game.controller.GameStateManager;

public class PauseState extends State {

    private final State playState;

    public PauseState(GameStateManager gameStateManager, State state) {
        super(gameStateManager, GameStateManager.GAME_STATE.PAUSE);
        this.playState = state;
    }

    public State resetPlayState() {
        return playState;
    }


    @Override
    public void update(double dt) {
    }

    @Override
    public void processInput() {

    }
}
