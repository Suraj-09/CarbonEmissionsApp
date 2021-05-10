/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.models;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author cstuser
 */
public class OutputModel {
    private Map<String, AmountModel> amounts;
    private Map<String, FlightEmissionsNoteModel> notes;

    public OutputModel() {
        amounts = new LinkedHashMap<>();
        notes = new LinkedHashMap<>();
    }
    
    public void addAmount(AmountModel amount) {
        amounts.put(amount.getType(), amount);
    }
    
    public void addNote(FlightEmissionsNoteModel note) {
        notes.put(note.getType(), note);
    }

    public Map<String, AmountModel> getAmounts() {
        return amounts;
    }

    public Map<String, FlightEmissionsNoteModel> getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "OutputModel{" + 
                "amounts=" + amounts + 
                ", notes=" + notes + 
                '}';
    }
    
}
