package com.gatedInc.game.view.stateView;

import com.gatedInc.game.controller.GameStateManager;
import com.gatedInc.game.controller.state.FightState;
import com.gatedInc.game.view.tiles.TilesImgManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class FightStateView implements StateView {

    private final FightState fightState;
    private Label fightText;
    private GameStateViewManager gameStateViewManager;


    public Label getFightText() {
        return fightText;
    }

    public FightStateView(GameStateViewManager gameStateViewManager) {
        this.gameStateViewManager = gameStateViewManager;
        fightState = ((FightState) gameStateViewManager.getGsm().getState());
        initText();

    }

    @Override
    public void render(GraphicsContext context) {
        context.drawImage(TilesImgManager.getFight(), 0, 0);
        context.drawImage(TilesImgManager.getPlayer(), 326, 397, 108, 108);
        context.drawImage(TilesImgManager.getEnemy(), 845, 215, 108, 108);
        renderText(context);
        context.setFill(Color.WHITE);
        context.fillText("Player life : "+ getCLife(fightState.getPlayerLife()), 320, 330);
        context.fillText("Enemy life : "+ getCLife(fightState.getEnemyLife()), 670, 230);
    }

    private String getCLife(int life){
        String s = "";
        for(int i = 0; i < life; i++){
            s += "*";
        }
        return s;
    }

    private void initText(){
        fightText = new Label();
        fightText.setLayoutX(521);
        fightText.setLayoutY(400);
        fightText.setWrapText(true);
        fightText.setMaxSize(430, 104);
        fightText.setTextFill(Color.web("#94F3E4"));
        gameStateViewManager.getAnchorPane().getChildren().add(fightText);
    }

    private void renderText(GraphicsContext context){
        fightText.setText(fightState.getMessageToDisplay());
    }
}
