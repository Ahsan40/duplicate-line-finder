package dev.pages.ahsan40.dlf.utils;

import dev.pages.ahsan40.dlf.main.Configs;
import dev.pages.ahsan40.dlf.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Utils {
    private static double xOffset;
    private static double yOffset;

    public static void makeDraggable(Scene scene) {
        // Make Scene Draggable
        scene.setOnMousePressed(event -> {
            xOffset = Main.primaryStage.getX() - event.getScreenX();
            yOffset = Main.primaryStage.getY() - event.getScreenY();
        });
        scene.setOnMouseDragged(event -> {
            Main.primaryStage.setX(event.getScreenX() + xOffset);
            Main.primaryStage.setY(event.getScreenY() + yOffset);
        });
    }

    public static void changeScene(String page, int h, int w) {
        try {
            Main.root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(page)));
            Main.scene = new Scene(Main.root, w, h);

            // Set CSS
            Main.root.getStylesheets().add(Objects.requireNonNull(Main.class.getResource(Configs.css)).toExternalForm());

            // Make Stage Transparent
            Main.primaryStage.initStyle(StageStyle.TRANSPARENT);

            // Make Scene Draggable
            Utils.makeDraggable(Main.scene);

            // Make Scene Transparent
            Main.scene.setFill(Color.TRANSPARENT);

            // Make Scene Draggable
            makeDraggable(Main.scene);

            Main.primaryStage.setScene(Main.scene);
            Main.primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
