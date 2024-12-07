package com.cs210.groupproject;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckoutController {
    private final Stage stage; // Reference to the main stage
    private int totalQuantity;  // Store the total quantity passed from the OrderController

    public CheckoutController(int totalQuantity) {
        this.stage = new Stage();  // Create a new stage for Checkout
        this.totalQuantity = totalQuantity;
    }

    // Display the checkout page
    public void displayCheckoutPage() {
        // Create a label to display the total quantity
        Label totalQuantityLabel = new Label("Total Quantity: " + totalQuantity);
        totalQuantityLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Create a layout for the Checkout page
        VBox checkoutLayout = new VBox(20);
        checkoutLayout.getChildren().add(totalQuantityLabel);

        // Create a scene for the Checkout page
        Scene checkoutScene = new Scene(checkoutLayout, 400, 300);

        // Set the scene to the stage and show it
        stage.setScene(checkoutScene);
        stage.setTitle("Checkout");
        stage.show();
    }
}
