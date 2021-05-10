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
public class FlightEmissionsNoteModel {
    private String value;
    private String type;

    public FlightEmissionsNoteModel() {
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FlightEmissionsNoteModel{" + 
                "value=" + value + 
                ", type=" + type + 
                '}';
    }
    
}
