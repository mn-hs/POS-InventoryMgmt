package com.InventoryMgmt.POSInventoryManagement.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class InventoryItem {
    private int ingredientId;
    private String ingredientName;
    private String ingredientDescription;
    private String unitMeasurement;
    private int units;
    private BigDecimal pricePerUnit;
    private LocalDate dateReceived;

    public InventoryItem(){}

    public InventoryItem(int ingredientId, String ingredientName, String ingredientDescription, String unitMeasurement,
                         int units, BigDecimal pricePerUnit, LocalDate dateReceived) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientDescription = ingredientDescription;
        this.unitMeasurement = unitMeasurement;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.dateReceived = dateReceived;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public void setIngredientDescription(String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
    }

    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit.setScale(2, RoundingMode.HALF_UP);
    }

    public LocalDate getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDate dateReceived) {
        this.dateReceived = dateReceived;
    }
}
