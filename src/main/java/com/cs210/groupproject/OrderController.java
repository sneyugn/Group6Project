package com.cs210.groupproject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.fxml.FXML;


public class OrderController extends Application {

    @Override

    //UI Initial Screen
    public void start (Stage primaryStage) {
        Label businessLabel = new Label("Six Sushi");
        Label groupMembersLabel = new Label ("by Simon, Vlad, and Matthew");
        Button orderButton = new Button("ORDER");
        Button salesButton = new Button("CHECK SALES");

        //Image Insert
        Image image = new Image(getClass().getResourceAsStream("/images/6sushi.png")); // Relative path

        //Icon Insert
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/6sushi.png")));
        // Display the image using ImageView
        ImageView imageView = new ImageView(image);

        //Set image properties
        imageView.setFitWidth(250);  // Resize width
        imageView.setFitHeight(250); // Resize height
        imageView.setPreserveRatio(true);


        // Styles

        orderButton.setStyle("-fx-background-color: #FFFFFF; -fx-font-weight: bold; -fx-border-radius: 0; " +
                "-fx-background-radius: 0; -fx-font-size:14px; -fx-font-family: Courier New; -fx-border-color: #000000; -fx-border-width: 3px;");

        salesButton.setStyle("-fx-background-color: #FFFFFF; -fx-font-weight: bold; -fx-border-radius: 0; " +
                "-fx-background-radius: 0; -fx-font-size:14px; -fx-font-family: Courier New; -fx-border-color: #000000; -fx-border-width: 3px;");

        businessLabel.setStyle("-fx-font-size: 72px; -fx-font-weight: bold; -fx-text-fill: black; -fx-font-family: Courier New; -fx-font-style: italic;");
        groupMembersLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: black; -fx-font-family: Courier New; -fx-font-style: italic;");

        HBox labelBox = new HBox(0, businessLabel);
        HBox groupLabel = new HBox(0, groupMembersLabel);
        HBox imageBox = new HBox(0,imageView);

        imageBox.setStyle("-fx-alignment: center;");
        labelBox.setStyle("-fx-alignment: center;");
        groupLabel.setStyle("-fx-alignment: center;");
        labelBox.setPadding(new Insets(20,0,0,0));
        VBox layout = new VBox(10);

        layout.setStyle("-fx-background-color: BEIGE; -fx-border-radius: 20; -fx-background-radius: 0;");
        HBox buttonBox = new HBox(15,orderButton,salesButton);

        buttonBox.setSpacing(50);
        buttonBox.setPadding(new Insets(10,0,0,0));
        buttonBox.setStyle("-fx-alignment: center;");

        layout.getChildren().addAll(
                labelBox,
                groupLabel,
                imageBox,
                buttonBox
        );



        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Six Sushi Kiosk");
        primaryStage.show();

        }
        public static void main(String[] args) {
            launch(args);
        }

}