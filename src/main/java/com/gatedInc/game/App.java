package com.gatedInc.game;


import com.gatedInc.game.view.JavaFXView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class App extends Application {

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;

    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane anchorPane = new AnchorPane();
            Group root = new Group();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gated Adventure");
            primaryStage.setResizable(false);
            Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
            root.getChildren().add(anchorPane);
            anchorPane.getChildren().add(canvas);
            JavaFXView view = new JavaFXView();
            view.initialize(canvas, anchorPane);
            view.initializeInputHandler(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
