package com.InventoryMgmt.POSInventoryManagement.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sale {
    private int saleId;
    private int tabId;
    private int employeeId;
    private int itemId;
    private BigDecimal price;
    private int units;

    public Sale(){}

    public Sale(int saleId, int tabId, int employeeId, int itemId, BigDecimal price, int units) {
        this.saleId = saleId;
        this.tabId = tabId;
        this.employeeId = employeeId;
        this.itemId = itemId;
        this.price = price;
        this.units = units;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getTabId() {
        return tabId;
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
