package com.InventoryMgmt.POSInventoryManagement.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Tab {
    private int tabId;
    private List<Sale> tabItems;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal tip;
    private BigDecimal total;
    private boolean isProcessed;
    private int employeeId;

    public Tab(){}

    public Tab(int tabId, BigDecimal subtotal, BigDecimal tax, BigDecimal tip, BigDecimal total, boolean isProcessed,
               int employeeId, List<Sale> tabItems) {
        this.tabId = tabId;
        this.subtotal = subtotal;
        this.tax = tax;
        this.tip = tip;
        this.total = total;
        this.isProcessed = isProcessed;
        this.employeeId = employeeId;
        this.tabItems = tabItems;
    }

    public int getTabId() {
        return tabId;
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }

    public BigDecimal getSubtotal() {
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTip() {
        return tip;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip;
    }

    public BigDecimal getTotal() {
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotal(BigDecimal total) {
        this.total = total.setScale(2, RoundingMode.HALF_UP);
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String toString(){
        return "Tab: " +
                "subtotal: " + subtotal + '\'' +
                "tax: " + tax +  '\'' +
                "tip: " + tip +  '\'' +
                "total: " + total;
    }
}
