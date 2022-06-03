package com.gatedInc.game.view;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class InputHandler implements EventHandler<javafx.scene.input.KeyEvent> {

    final private static Set<KeyCode> activeKeys = new HashSet<>();
    private static boolean PAUSE = false;
    private static boolean isInventoryOpen = false;

    @Override
    public void handle(javafx.scene.input.KeyEvent event) {
        if (javafx.scene.input.KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            if(event.getCode() == KeyCode.ESCAPE) PAUSE = !PAUSE;
            else if (event.getCode().equals(KeyCode.B)){
                isInventoryOpen = !isInventoryOpen;
            }
            else activeKeys.add(event.getCode());

        } else if (javafx.scene.input.KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            activeKeys.remove(event.getCode());
        }
    }

    public static boolean getPause(){
        return PAUSE;
    }

    public static Set<KeyCode> getActiveKeys() {
        return Collections.unmodifiableSet(activeKeys);
    }
    public static boolean isInventoryOpen() {
        return isInventoryOpen;
    }


}
