package com.cs210.groupproject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class ThankYouController {
    private final Stage stage;

    public ThankYouController(Stage stage) {
        this.stage = stage;
    }

    public void displayThankYouPage(String name) {
        Image sushi = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/sushibg.png")));

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        layout.setPadding(new Insets(20));

        Label thankYouLabel = new Label("Your order is on its way. \nThank you for supporting Six Sushi,\n" + name + "!");
        thankYouLabel.setStyle("-fx-font-weight: bold; -fx-border-radius: 25px; " +
                "-fx-background-radius: 25px; -fx-font-size:16px; -fx-text-fill: white; -fx-font-family: Courier New; " +
                "-fx-border-color: transparent; -fx-border-width: 0; -fx-padding: 10px 20px;-fx-alignment: center;");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close()); // Close the application
        closeButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 25px; " +
                "-fx-background-radius: 25px; -fx-font-size:16px; -fx-font-family: Courier New; " +
                "-fx-border-color: transparent; -fx-border-width: 0; -fx-padding: 10px 20px;-fx-alignment:center;");
        layout.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundImage(sushi,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundPosition.CENTER,
                        javafx.scene.layout.BackgroundSize.DEFAULT)
        ));
        layout.getChildren().addAll(thankYouLabel, closeButton);

        Scene scene = new Scene(layout, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

