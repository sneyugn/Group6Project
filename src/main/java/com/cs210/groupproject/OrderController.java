package com.cs210.groupproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private final Stage stage;
    private Scene scene;

    private final List<MenuItem> menuItems = new ArrayList<>();

    public OrderController(Stage stage) {
        this.stage = stage;

        menuItems.add(new MenuItem("California Roll", 8.99, "/images/california_roll.png"));
        menuItems.add(new MenuItem("Rainbow Roll", 10.99, "images/rainbow_roll.png"));
        menuItems.add(new MenuItem("Dragon Roll", 12.99, "images/dragon_roll.png"));
        menuItems.add(new MenuItem("Sashimi", 14.99, "images/sashimi.png"));
        menuItems.add(new MenuItem("Maki Roll", 6.99, "images/maki.png"));
        menuItems.add(new MenuItem("Spicy Tuna Roll", 9.99, "images/spicy_tuna_roll.png"));
        menuItems.add(new MenuItem("Tempura Roll", 11.99, "images/tempura_roll.png"));
        menuItems.add(new MenuItem("Veggie Roll", 7.99, "images/veggie_roll.png"));
        menuItems.add(new MenuItem("Salmon Sushi", 15.99, "images/salmon_sushi.png"));
        menuItems.add(new MenuItem("Tuna Sushi", 14.99, "images/tuna_sushi.png"));

        VBox layout = new VBox(20); // 20px spacing between elements
        layout.setPadding(new Insets(20)); // Padding around the VBox
        layout.setStyle("-fx-background-color: beige;");

        Button backButton = new Button("Back to Menu");
        //Button checkoutButton = new Button("Checkout");

        // Style buttons
        backButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 25px; " +
                "-fx-background-radius: 25px; -fx-font-size:12px; -fx-font-family: Courier New; " +
                "-fx-border-color: transparent; -fx-border-width: 0; -fx-padding: 10px 20px;");
//        checkoutButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 25px; " +
//                "-fx-background-radius: 25px; -fx-font-size:12px; -fx-font-family: Courier New; " +
//                "-fx-border-color: transparent; -fx-border-width: 0; -fx-padding: 10px 20px;");


        // Create an HBox to arrange buttons at the top left and top right
        HBox topBar = new HBox(10);
        topBar.setSpacing(110);
        topBar.setPadding(new Insets(10, 10, 10, 10)); // Add padding around the HBox
        topBar.getChildren().addAll(backButton); // Add back button to left
        topBar.setStyle("-fx-alignment: top-center;");

//        // Add Cart button to the right
//        HBox cartBox = new HBox(10);
//        cartBox.setStyle("-fx-alignment: top-right;");
//        cartBox.setPadding(new Insets(10, 10, 10, 10));
//        cartBox.getChildren().addAll(checkoutButton);


        // Loop through menu items and create UI components for each item
        for (MenuItem item : menuItems) {
            // Create a label for the item
            Label itemLabel = new Label(item.getName() + " - $" + item.getPrice());
            itemLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold");

            String imagePath = "file:src/main/resources/" + item.getImagePath();

            Image image = new Image(imagePath);


            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(120);
            imageView.setFitWidth(180);

            Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
            clip.setArcWidth(20);  // Set the roundness
            clip.setArcHeight(20); // Set the roundness


            imageView.setClip(clip);


            imageView.setPreserveRatio(true);

            Button increaseButton = new Button("+");
            Button decreaseButton = new Button("-");

            // new styles to the buttons to make them circles
            increaseButton.getStyleClass().add("button-circle");
            decreaseButton.getStyleClass().add("button-circle");
            increaseButton.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
            decreaseButton.setStyle("-fx-font-weight: bold;-fx-text-fill: black;");

            // Create a label to display the current quantity
            Label quantityLabel = new Label("0");
            quantityLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            increaseButton.setOnAction(e -> {
                item.increaseQuantity();
                quantityLabel.setText(String.valueOf(item.getQuantity()));
            });
            decreaseButton.setOnAction(e -> {
                item.decreaseQuantity();
                quantityLabel.setText(String.valueOf(item.getQuantity()));
            });


            HBox quantityBox = new HBox(10);
            quantityBox.setSpacing(20);
            quantityBox.setAlignment(Pos.CENTER_RIGHT);
            quantityBox.getChildren().addAll(decreaseButton, quantityLabel, increaseButton, imageView);


            HBox priceBox = new HBox(10);
            priceBox.setAlignment(Pos.CENTER_LEFT);
            priceBox.getChildren().addAll(itemLabel);

            // Create a VBox for the item name, price, and quantity buttons
            VBox itemBox = new VBox(10);
            itemBox.setPadding(new Insets(10));
            itemBox.getChildren().addAll(priceBox, quantityBox);
            itemBox.setStyle("-fx-alignment: center-left;");

            // Add the item box to the layout
            layout.getChildren().add(itemBox);
        }

        Region blankSpace = new Region();
        blankSpace.setPrefHeight(50);
        VBox.setVgrow(blankSpace, javafx.scene.layout.Priority.ALWAYS);
        layout.getChildren().add(blankSpace); // Add the blank space at the bottom

        // Add the top bar to the layout (buttons at the top)
        layout.getChildren().add(0, topBar);

        // Create a "Checkout" button that stays at the bottom
        Button checkoutButton = new Button("Checkout");
        checkoutButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 25px; " +
                "-fx-background-radius: 25px; -fx-font-size:16px; -fx-font-family: Courier New; " +
                "-fx-border-color: transparent; -fx-border-width: 0; -fx-padding: 10px 30px;");

        // Add a bit of space at the bottom
        checkoutButton.setPadding(new Insets(10));


        StackPane stackPane = new StackPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        stackPane.getChildren().addAll(scrollPane, checkoutButton);

        StackPane.setMargin(checkoutButton, new Insets(10, 0, 30, 0));

        StackPane.setAlignment(checkoutButton, javafx.geometry.Pos.BOTTOM_CENTER);


        scene = new Scene(stackPane, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // return to MenuController
        backButton.setOnAction(e -> {
            MenuController menuController = new MenuController();
            menuController.start(stage);
        });

        checkoutButton.setOnAction(e -> {
            // Collect the items that have quantity > 0
            System.out.println("checkout clicked");
            List<MenuItem> orderItems = new ArrayList<>();
            int totalQuantity = 0;

            // Loop through menu items to find those with a quantity > 0
            for (MenuItem item : menuItems) {
                if (item.getQuantity() > 0) {
                    orderItems.add(item);
                    totalQuantity += item.getQuantity();  // Add the quantity to the total
                    System.out.println(item.getName() + " - Quantity: " + item.getQuantity());
                }
            }

            if (orderItems.isEmpty()) {
                System.out.println("No items in the order.");
                return;
            } else {
                System.out.println("Total quantity of items in the order: " + totalQuantity);
                };

            CheckoutController checkoutController = new CheckoutController(stage, orderItems);
            checkoutController.displayCheckoutPage();
        });


    }
    public void start() {
        stage.setScene(scene);
        stage.show();  // Display the stage
    }

    public Scene getScene() {
        return scene;
    }
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

}
