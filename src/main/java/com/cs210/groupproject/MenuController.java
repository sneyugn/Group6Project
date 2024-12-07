package com.cs210.groupproject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.util.Objects;


public class MenuController extends Application {

    private Scene scene1;
    @Override

    //UI Initial Screen
    public void start (Stage primaryStage) {
        Label businessLabel = new Label("Six Sushi");
        Label groupMembersLabel = new Label("by Simon, Vlad, and Matthew");
        Button cartButton = new Button("CART");
        Button orderButton = new Button("ORDER");
        Button salesButton = new Button("CHECK SALES");

        //Image Insert
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/6sushi.png"))); // Relative path
        Image cart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/shoppingcart.png")));


        //Icon Insert
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/6sushi.png"))));
        // Display the image using ImageView
        ImageView logoView = new ImageView(logo);
        ImageView cartView = new ImageView(cart);
        //Set image properties
        logoView.setFitWidth(250);  // Resize width
        logoView.setFitHeight(250); // Resize height
        logoView.setPreserveRatio(true);

        cartView.setFitWidth(35);  // Resize width
        cartView.setFitHeight(35); // Resize height
        cartView.setPreserveRatio(true);

        //Set Cart Icon to Button
//        cartButton.setGraphic(cartView);
//        cartButton.setOnAction(e -> System.out.println("Order clicked!"));

        // Styles

        orderButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 0; " +
                "-fx-background-radius: 0; -fx-font-size:14px; -fx-font-family: Courier New; -fx-border-color: #000000; -fx-border-width: 3px;");

        salesButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 0; " +
                "-fx-background-radius: 0; -fx-font-size:14px; -fx-font-family: Courier New; -fx-border-color: #000000; -fx-border-width: 3px;");
        cartButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 0; " +
                "-fx-background-radius: 0; -fx-font-size:14px; -fx-font-family: Courier New; -fx-border-color: #000000; -fx-border-width: 3px;");

        businessLabel.setStyle("-fx-font-size: 72px; -fx-font-weight: bold; -fx-text-fill: black; -fx-font-family: Courier New; -fx-font-style: italic;");
        groupMembersLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: black; -fx-font-family: Courier New; -fx-font-style: italic;");


        HBox cartBox = new HBox(0, cartButton);
        HBox labelBox = new HBox(0, businessLabel);
        HBox groupLabel = new HBox(0, groupMembersLabel);
        HBox imageBox = new HBox(0, logoView);

        cartBox.setStyle("-fx-alignment: top-right;");
        cartBox.setPadding(new Insets(10, 10, 0, 0));

        labelBox.setStyle("-fx-alignment: center;");
        labelBox.setPadding(new Insets(0, 0, 0, 0));
        imageBox.setStyle("-fx-alignment: center;");

        groupLabel.setStyle("-fx-alignment: center;");
        groupLabel.setPadding(new Insets(-10, 0, 0, 0));


        VBox layout = new VBox(0);

        layout.setStyle("-fx-background-color: BEIGE; -fx-border-radius: 20; -fx-background-radius: 0;");
        HBox buttonBox = new HBox(15, orderButton, salesButton);

        buttonBox.setSpacing(50);
        buttonBox.setPadding(new Insets(0, 0, 0, 20));
        buttonBox.setStyle("-fx-alignment: center;");

        layout.getChildren().addAll(
                cartBox,
                labelBox,
                groupLabel,
                imageBox,
                buttonBox
        );


        Scene scene1 = new Scene(layout, 400, 500);

        scene1.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Six Sushi Kiosk");
        primaryStage.show();


        //Control what buttons do

        cartButton.setOnAction(e -> {

            CartController cartController = new CartController(primaryStage);
            primaryStage.setScene(cartController.getScene());
        });


        orderButton.setOnAction(e -> {
            OrderController orderController = new OrderController(primaryStage);
            primaryStage.setScene(orderController.getScene());
        });

        salesButton.setOnAction(e -> {
            SalesController salesController = new SalesController(primaryStage);
            primaryStage.setScene(salesController.getScene());
        });
    }
        public Scene getScene(Stage stage) {
            return scene1;
        }

        public static void main(String[] args) {
            launch(args);
        }

}