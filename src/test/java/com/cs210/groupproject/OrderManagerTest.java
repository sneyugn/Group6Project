package com.cs210.groupproject;

// Author: Vlad German

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {

    private OrderManager orderManager;

    @BeforeEach
    void setUp() {
        // Reset the singleton instance for testing
        orderManager = OrderManager.getInstance();
        orderManager.clearOrders();
    }

    @Test
    void testAddOrder() {

        MenuItem item1 = new MenuItem("Sushi", 10.0, "/images/sushi.png");
        MenuItem item2 = new MenuItem("Tempura", 8.0, "/images/tempura.png");
        Order order = new Order(List.of(item1, item2));


        orderManager.addOrder(order);


        assertEquals(1, orderManager.getOrders().size());
        assertEquals("Sushi", orderManager.getOrders().get(0).getItems().get(0).getName());
    }

    @Test
    void testClearOrders() {

        Order order = new Order(List.of(new MenuItem("Sushi", 10.0, "/images/sushi.png")));
        orderManager.addOrder(order);


        orderManager.clearOrders();


        assertTrue(orderManager.getOrders().isEmpty());
    }

    @Test
    void testGetOrders() {

        MenuItem item = new MenuItem("Sushi", 10.0, "/images/sushi.png");
        Order order = new Order(List.of(item));
        orderManager.addOrder(order);


        List<Order> orders = orderManager.getOrders();


        assertNotNull(orders);
        assertEquals(1, orders.size());
    }
}
