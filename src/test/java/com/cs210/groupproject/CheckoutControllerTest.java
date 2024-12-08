package com.cs210.groupproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutControllerTest {

    private List<MenuItem> orderItems;

    @BeforeEach
    void setUp() {
        orderItems = new ArrayList<>();
        orderItems.add(new MenuItem("California Roll", 8.99, "/images/california_roll.png"));
        orderItems.add(new MenuItem("Maki Roll", 6.99, "/images/maki.png"));
        orderItems.add(new MenuItem("Spicy Tuna Roll", 9.99, "images/spicy_tuna_roll.png"));
        orderItems.add(new MenuItem("Tempura Roll", 11.99, "images/tempura_roll.png"));
    }
    @Test
    void testTotalCostCalculation() {
        // Perform calculations in the test
        double expectedTotal = 37.96; //8.99+6.99+11.99+9.99
        double actualTotal = orderItems.stream()
                .mapToDouble(MenuItem::getPrice)
                .sum();
        assertEquals(expectedTotal, actualTotal, "Total cost should match expected value");
    }
}
