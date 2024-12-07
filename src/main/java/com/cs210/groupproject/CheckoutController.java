package com.cs210.groupproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CheckoutController {
    private final Stage stage;
    private final List<MenuItem> orderItems;
    private Scene scene;

    public CheckoutController(Stage stage, List<MenuItem> orderItems) {
        this.stage = stage;
        this.orderItems = orderItems;
        setupCheckoutScene();
    }

    private void setupCheckoutScene() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        Image sushi = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/sushibg.png")));

        Label title = new Label("Checkout");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;-fx-text-fill: white;");

        VBox orderSummaryBox = new VBox(10);
        orderSummaryBox.setAlignment(Pos.CENTER_LEFT);
        orderSummaryBox.setPadding(new Insets(10, 10, 10, 10));


        final double[] totalCost = {0.0};

        for (MenuItem item : orderItems) {
            Label itemLabel = new Label(item.getName() + " x " + item.getQuantity() +
                    " - $" + String.format("%.2f", item.getPrice() * item.getQuantity()));
            itemLabel.setStyle("-fx-font-size: 14px;");
            orderSummaryBox.getChildren().add(itemLabel);

            totalCost[0] += item.getPrice() * item.getQuantity();
        }

        Label totalCostLabel = new Label("Total Cost: $" + String.format("%.2f", totalCost[0]));
        totalCostLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;-fx-text-fill: white;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(orderSummaryBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefHeight(250);

        // Name Input
        HBox nameBox = new HBox(10);
        nameBox.setAlignment(Pos.CENTER_LEFT);
        Label nameLabel = new Label("Name: ");
        nameLabel.setStyle("-fx-font-size: 14px;-fx-text-fill: white;-fx-font-weight: bold;");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");
        nameField.setPrefWidth(200);

        nameBox.getChildren().addAll(nameLabel, nameField);

        Button backButton = new Button("Back to Order");
        Button confirmButton = new Button("Confirm");
        confirmButton.setDisable(true); // Initially disabled

        // Button Styles
        backButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 25px; " +
                "-fx-background-radius: 25px; -fx-font-size:12px; -fx-font-family: Courier New; " +
                "-fx-border-color: transparent; -fx-border-width: 0; -fx-padding: 10px 20px;");
        confirmButton.setStyle("-fx-font-weight: bold; -fx-border-radius: 25px; " +
                "-fx-background-radius: 25px; -fx-font-size:12px; -fx-font-family: Courier New; " +
                "-fx-border-color: transparent; -fx-border-width: 0; -fx-padding: 10px 20px;");

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(newValue.trim().isEmpty());
        });

        HBox buttonBox = new HBox(20);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.getChildren().addAll(backButton, confirmButton);

        backButton.setOnAction(e -> {
            OrderController orderController = new OrderController(stage);
            orderController.start();
        });

        confirmButton.setOnAction(e -> {
            String customerName = nameField.getText();
            System.out.println("Order confirmed by: " + customerName);
            System.out.println("Order Details:");
            for (MenuItem item : orderItems) {
                System.out.println(item.getName() + " x " + item.getQuantity());
            }
            System.out.println("Your order is on its way. Thank you for supporting Six Sushi!");


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/orders/orders.txt", true))) {
                writer.write("Order by: " + customerName + "\n");
                writer.write("Order Details:\n");
                for (MenuItem item : orderItems) {
                    writer.write(item.getName() + " x " + item.getQuantity() + " - $" +
                            String.format("%.2f", item.getPrice() * item.getQuantity()) + "\n");
                }
                writer.write("Total Cost: $" + String.format("%.2f", totalCost[0]) + "\n");
                writer.write("=======================================================\n");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            // Clear the order after confirmation
            orderItems.clear();

            ThankYouController thankYouController = new ThankYouController(stage);
            thankYouController.displayThankYouPage(customerName);
        });

        layout.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundImage(sushi,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundPosition.CENTER,
                        javafx.scene.layout.BackgroundSize.DEFAULT)
        ));

        layout.getChildren().addAll(title, scrollPane, totalCostLabel, nameBox, buttonBox);

        scene = new Scene(layout, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }

    public void displayCheckoutPage() {
        stage.setScene(scene);
        stage.show();
    }
}
