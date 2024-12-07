package com.cs210.groupproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class SalesController {
    private final Stage stage; // Reference to the main stage
    private Scene scene;

    public SalesController(Stage stage) {
        this.stage = stage;


        // Create layout and components for the Sales scene
        VBox layout = new VBox(20); // 20px spacing between elements
        layout.setPadding(new Insets(20)); // Padding around the VBox
        layout.setStyle("-fx-background-color: BEIGE;");

        // Create a back button
        Button backButton = new Button("Back to Menu");
        backButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 0; " +
                "-fx-background-radius: 0; -fx-font-size:14px; -fx-font-family: Courier New; -fx-border-color: #000000; -fx-border-width: 3px;");

        // Create and store the scene
        scene = new Scene(layout, 400, 500);

        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // Add action for the back button to return to MenuController
        backButton.setOnAction(e -> {
            // Recreate MenuController and set the scene
            MenuController menuController = new MenuController();
            menuController.start(stage);  // Call start() method directly to set up the main menu
        });

        // Add the button to the layout
        layout.getChildren().add(backButton);
    }

    public Scene getScene() {
        return scene;
    }
}
