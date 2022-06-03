package com.gatedInc.game.view.stateView;

import com.gatedInc.game.App;
import com.gatedInc.game.controller.state.PlayState;
import com.gatedInc.game.utils.Camera;
import com.gatedInc.game.view.HealthView;
import com.gatedInc.game.view.InventoryView;
import com.gatedInc.game.view.tiles.TilesImgManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class PlaySateView implements StateView {

    private final Camera camera;
    private final PlayState playState;
    private final HealthView healthView;
    private final InventoryView inventoryView;
    private Label dialogText;

    public PlaySateView(GameStateViewManager gameStateViewManager) {
        playState = (PlayState) gameStateViewManager.getGsm().getState();
        camera = playState.getCam();
        healthView = new HealthView(playState.getPlayer().getHealth());
        inventoryView = new InventoryView(playState.getPlayer().getInventory(), playState.getPlayer().getAnchorPane());
        initDialog();
    }


    @Override
    public void render(GraphicsContext context) {
        clear(context);
        renderBackground(context);
        renderTm(context);
        renderPlayer(context);
        renderFps(context);
        inventoryView.render(context);
        renderHealthView(context);
        renderDialog(context);
    }

    private void renderFps(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.fillText(playState.getFps().getFps() + " fps", 1200, 700, 100);
    }

    private void renderPlayer(GraphicsContext context) {
        context.drawImage(playState.getPlayer().getAnimation().getSprite(), playState.getPlayer().getPosition().getX()
                - camera.getPos().getX(), playState.getPlayer().getPosition().getY() - camera.getPos().getY(), 53, 53);
        //context.setFill(Color.GRAY);
        //context.fillRect(playState.getPlayer().getRectangle().getPos().x - camera.getPos().x, playState.getPlayer().getRectangle().getPos().y - camera.getPos().y ,playState.getPlayer().getRectangle().getWidth(), playState.getPlayer().getRectangle().getHeight());
    }

    private void renderTm(GraphicsContext context) {
        playState.getTm().render(context, camera);
    }

    private void renderBackground(GraphicsContext context) {
        context.setFill(Color.rgb(78, 96, 104));
        context.fillRect(0, 0, App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
    }

    private void clear(GraphicsContext context) {
        context.clearRect(0, 0, App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
    }

    private void renderHealthView(GraphicsContext context) {
        healthView.render(context);
        healthView.update();
    }

    private void initDialog() {
        dialogText = new Label();
        dialogText.setLayoutX(520);
        dialogText.setLayoutY(549);
        dialogText.setWrapText(true);
        dialogText.setMaxSize(242, 92);
        dialogText.setTextFill(Color.web("#94F3E4"));
        playState.getPlayer().getAnchorPane().getChildren().add(dialogText);
    }

    private void renderDialog(GraphicsContext context) {
        if (playState.getPlayer().getPlayerMessage() != null) {
            dialogText.setText(playState.getPlayer().getPlayerMessage());
            context.drawImage(TilesImgManager.getDialog(), 493, 450, 294, 294);
        } else
            dialogText.setText(null);
    }
}
