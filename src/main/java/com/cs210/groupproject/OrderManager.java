package com.cs210.groupproject;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private final List<Order> orders;

    // Private constructor to prevent instantiation
    private OrderManager() {
        orders = new ArrayList<>();
    }

    // Static method to get the single instance of the class
    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    // Add an order to the list
    public void addOrder(Order order) {
        orders.add(order);
    }

    // Get all orders
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    // Clear all orders
    public void clearOrders() {
        orders.clear();
    }
}


