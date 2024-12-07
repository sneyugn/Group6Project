package com.cs210.groupproject;

public class MenuItem {
    private final String name;
    private final double price;
    private int quantity; // Quantity of the item

    // Constructor
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;  // Default quantity is 0
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Methods to increase and decrease the quantity
    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }

    // Optionally, override toString() to display the item name, price, and quantity
    @Override
    public String toString() {
        return name + " - $" + price + " \nquantity: " + quantity;
    }
}
