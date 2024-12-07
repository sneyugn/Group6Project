package com.cs210.groupproject;

import java.util.List;

public class Order {
    private List<MenuItem> items;

    public Order(List<MenuItem> items) {
        this.items = items;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }
}
