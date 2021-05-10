/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.models;

/**
 *
 * @author cstuser
 */
public class AmountModel {
    private String unit;
    private boolean booleanDefault;
    private double value;
    private String type;

    public AmountModel() {
    }

    public String getUnit() {
        return unit;
    }

    public boolean isBooleanDefault() {
        return booleanDefault;
    }

    public double getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setBooleanDefault(boolean booleanDefault) {
        this.booleanDefault = booleanDefault;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AmountModel{" + 
                "unit=" + unit + 
                ", booleanDefault=" + booleanDefault + 
                ", value=" + value + 
                ", type=" + type + 
                '}';
    }
    
    
}
