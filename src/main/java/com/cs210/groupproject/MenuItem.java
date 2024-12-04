package com.cs210.groupproject;
import java.math.BigDecimal;

public class MenuItem {

    private String itemName;
    private BigDecimal itemPrice;

    public MenuItem(String itemName, BigDecimal itemPrice) {

        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getName(){
        return itemName;
    }

    public BigDecimal getCost(){
        return itemPrice;
    }

    public String itemToString(){
        String info = itemName + "\n" + itemPrice;
        return info;
    }

}
