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
import javafx.scene.layout.Region;  // Import Region for blank space
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private final Stage stage; // Reference to the main stage
    private Scene scene;

    // List of menu items
    private final List<MenuItem> menuItems = new ArrayList<>();

    public OrderController(Stage stage) {
        this.stage = stage;

        // Create sample menu items with image paths
        menuItems.add(new MenuItem("California Roll", 8.99, "/images/california_roll.png"));
        menuItems.add(new MenuItem("Rainbow Roll", 10.99, "images/rainbow_roll.png"));
        menuItems.add(new MenuItem("Dragon Roll", 12.99, "images/dragon_roll.png"));
        menuItems.add(new MenuItem("Sashimi", 14.99, "images/sashimi.png"));
        menuItems.add(new MenuItem("Maki", 6.99, "images/maki.png"));
        menuItems.add(new MenuItem("Spicy Tuna Roll", 9.99, "images/spicy_tuna_roll.png"));
        menuItems.add(new MenuItem("Tempura Roll", 11.99, "images/tempura_roll.png"));
        menuItems.add(new MenuItem("Veggie Roll", 7.99, "images/veggie_roll.png"));
        menuItems.add(new MenuItem("Salmon Sushi", 15.99, "images/salmon_sushi.png"));
        menuItems.add(new MenuItem("Tuna Sushi", 14.99, "images/tuna_sushi.png"));




        // Create layout and components for the order scene
        VBox layout = new VBox(20); // 20px spacing between elements
        layout.setPadding(new Insets(20)); // Padding around the VBox
        layout.setStyle("-fx-background-color: beige;");

        // Create a back button and cart button at the top
        Button backButton = new Button("Back to Menu");
        Button cartButton = new Button("Cart");

        // Style buttons
        backButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 25px; " +
                "-fx-background-radius: 25px; -fx-font-size:12px; -fx-font-family: Courier New; " +
                "-fx-border-color: transparent; -fx-border-width: 0; -fx-padding: 10px 20px;");
        cartButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 25px; " +
                "-fx-background-radius: 25px; -fx-font-size:16px; -fx-font-family: Courier New; " +
                "-fx-border-color: transparent; -fx-border-width: 0; -fx-padding: 10px 30px;");

        // Create an HBox to arrange buttons at the top left and top right
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(10, 10, 10, 10)); // Add padding around the HBox
        topBar.getChildren().addAll(backButton); // Add back button to left
        topBar.setStyle("-fx-alignment: top-left;");

        // Add Cart button to the right
        HBox cartBox = new HBox(10, cartButton);
        cartBox.setStyle("-fx-alignment: top-right;");
        cartBox.setPadding(new Insets(10, 10, 10, 10));


        // Loop through menu items and create UI components for each item
        for (MenuItem item : menuItems) {
            // Create a label for the item
            Label itemLabel = new Label(item.getName() + " - $" + item.getPrice());
            itemLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold");

            // Load the image for the item (using the image path from the MenuItem)
            //Image image = new Image("file:src/main/resources/images/" + item.getImagePath());

            String imagePath = "file:src/main/resources/" + item.getImagePath();

            //System.out.println(imagePath);
            Image image = new Image(imagePath);
//// Update the path

            if (image.isError()) {
                System.out.println("Error loading image: " + image);  // Image load failed
            } else {
                System.out.println("Image loaded successfully: " + image);  // Image loaded
            }

            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(120);  // Set the height of the image
            imageView.setFitWidth(180);   // Set the width of the image

            Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
            clip.setArcWidth(20);  // Set the roundness (adjust to your preference)
            clip.setArcHeight(20); // Set the roundness (adjust to your preference)

// Apply the clip to the imageView
            imageView.setClip(clip);

// Ensure the image does not overflow outside the rounded edges
            imageView.setPreserveRatio(true);
            // Create buttons and other components for quantity adjustment
            Button increaseButton = new Button("+");
            Button decreaseButton = new Button("-");

            // Apply new styles to the buttons to make them smaller and circular
            increaseButton.getStyleClass().add("button-circle");
            decreaseButton.getStyleClass().add("button-circle");
            increaseButton.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
            decreaseButton.setStyle("-fx-font-weight: bold;-fx-text-fill: black;");

            // Create a label to display the current quantity (initially 0)
            Label quantityLabel = new Label("0");
            quantityLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            increaseButton.setOnAction(e -> {
                item.increaseQuantity();
                quantityLabel.setText(String.valueOf(item.getQuantity()));  // Update the quantity label
            });
            decreaseButton.setOnAction(e -> {
                item.decreaseQuantity();
                quantityLabel.setText(String.valueOf(item.getQuantity()));  // Update the quantity label
            });

            // Create an HBox to align the image, buttons, and the quantity label horizontally
            HBox quantityBox = new HBox(10);
            quantityBox.setSpacing(20);
            quantityBox.setAlignment(Pos.CENTER_RIGHT);
            quantityBox.getChildren().addAll(decreaseButton, quantityLabel, increaseButton, imageView);// Image, Buttons and quantity label in one line

            // Create an HBox to align the price and image horizontally (image to the right of price)
            HBox priceBox = new HBox(10);
            priceBox.setAlignment(Pos.CENTER_LEFT);
            priceBox.getChildren().addAll(itemLabel);  // Add price label and image next to each other

            // Create a VBox for the item name, price, and quantity buttons
            VBox itemBox = new VBox(10);
            itemBox.setPadding(new Insets(10));
            itemBox.getChildren().addAll(priceBox, quantityBox);  // Add price and quantity control
            itemBox.setStyle("-fx-alignment: center-left;");

            // Add the item box to the layout
            layout.getChildren().add(itemBox);
        }


        // Add extra blank space after the last item (like Tuna Sushi)
        Region blankSpace = new Region(); // Create a Region to add extra blank space
        blankSpace.setPrefHeight(50);
        VBox.setVgrow(blankSpace, javafx.scene.layout.Priority.ALWAYS); // Allow the blank space to take up available space
        layout.getChildren().add(blankSpace); // Add the blank space at the bottom

        // Add the top bar to the layout (buttons at the top)
        layout.getChildren().add(0, topBar); // Adding at the top (index 0)

        // Create an "Add to Cart" button that stays at the bottom
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 25px; " +
                "-fx-background-radius: 25px; -fx-font-size:16px; -fx-font-family: Courier New; " +
                "-fx-border-color: transparent; -fx-border-width: 0; -fx-padding: 10px 30px;");

        // Add a bit of space at the bottom
        addToCartButton.setPadding(new Insets(10));

        // StackPane to hold the ScrollPane and the Add to Cart button at the bottom
        StackPane stackPane = new StackPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true); // Ensure it fits the width of the ScrollPane
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // Add the ScrollPane and the "Add to Cart" button to the StackPane
        stackPane.getChildren().addAll(scrollPane, addToCartButton);

        // Add bottom insets to the StackPane, this ensures there's space between the Add to Cart button and the bottom of the screen
        StackPane.setMargin(addToCartButton, new Insets(10, 0, 30, 0)); // Adjust the bottom margin (Insets(top, right, bottom, left))

        StackPane.setAlignment(addToCartButton, javafx.geometry.Pos.BOTTOM_CENTER);

        // Create and store the scene
        scene = new Scene(stackPane, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // Action for back button to return to MenuController
        backButton.setOnAction(e -> {
            MenuController menuController = new MenuController();
            menuController.start(stage);  // Call start() method to set up the main menu
        });

        // Action for Cart button (this can be modified to go to CartController or similar)
        cartButton.setOnAction(e -> {
            // Logic for cart button (e.g., open Cart page)
            System.out.println("Cart button clicked!");
        });

        // Action for "Add to Cart" button
        addToCartButton.setOnAction(e -> {
            System.out.println("Items added to cart!");
        });
    }

    public Scene getScene() {
        return scene;
    }
}
