package com.InventoryMgmt.POSInventoryManagement.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MenuItem {
    private int itemId;
    private String name;
    private String description;
    private BigDecimal price;
    private int itemCount;

    public MenuItem(){}

    public MenuItem(int itemId, String name, String description, BigDecimal price, int itemCount) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.itemCount = itemCount;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
