package com.gatedInc.game.controller.state;

import com.gatedInc.game.controller.GameStateManager;
import com.gatedInc.game.model.Character;
import com.gatedInc.game.model.Enemy;
import com.gatedInc.game.model.Player;
import com.gatedInc.game.view.InputHandler;
import javafx.scene.input.KeyCode;

public class FightState extends State {

    Player player;
    Enemy enemy;
    Character nextToHit;
    int delay;
    String messageToDisplay;
    private final PlayState playState;
    private String m = " (press F to continue)";


    public int getPlayerLife(){
        return player.getHealth().getCurrentHealth();
    }

    public int getEnemyLife(){
        return enemy.getHealth().getCurrentHealth();
    }

    public FightState(GameStateManager gsm, GameStateManager.GAME_STATE gs, Player player, Enemy enemy, boolean playerStart, PlayState playState) {
        super(gsm, gs);
        this.playState = playState;
        this.player = player;
        this.enemy = enemy;

        if (playerStart) {
            messageToDisplay = "You're entering a turn-based fight. You're the first to attack.";
            nextToHit = player;
        } else {
            messageToDisplay = "You're entering a turn-based fight. Enemy is first to attack.";
            nextToHit = player;
        }
    }

    public State resetPlayState() {
        return playState;
    }

    public String getMessageToDisplay() {
        return messageToDisplay + m;
    }

    public void hit() {
        if (nextToHit == player) {
            enemy.hit(player.getStrength().getCurrentStrength());
            messageToDisplay = "You dealt " + player.getStrength().getCurrentStrength() + " damages to the enemy that now has " + enemy.getHealth().getCurrentHealth() + " HP.";
            nextToHit = enemy;
        } else {
            player.hit(enemy.getStrength().getCurrentStrength());
            messageToDisplay = "You took " + enemy.getStrength().getCurrentStrength() + " damages from the enemy. Your health is now " + player.getHealth().getCurrentHealth() + " HP."; // linverse d'en haut
            nextToHit = player;
        }

        if (enemy.getHealth().getCurrentHealth() <= 0) {
            enemy.kill();
            gsm.setState(GameStateManager.GAME_STATE.PLAY);

        } else if (player.getHealth().getCurrentHealth() <= 0) {

            gsm.setState(GameStateManager.GAME_STATE.GAME_OVER);

        }
    }

    @Override
    public void update(double dt) {
        delay = delay + 1;
    }

    @Override
    public void processInput() {
        if (InputHandler.getActiveKeys().contains(KeyCode.F)) {
            if (delay > 30) {
                hit();
                delay = 0;
            }
        }
    }


}
