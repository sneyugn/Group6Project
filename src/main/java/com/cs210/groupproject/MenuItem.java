package com.cs210.groupproject;

public class MenuItem {
    private String name;
    private double price;
    private int quantity;
    private String imagePath;  // Add imagePath to hold the image file path

    public MenuItem(String name, double price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        if (this.quantity > 0) this.quantity--;
    }

    public String getImagePath() {
        return imagePath;
    }
}
