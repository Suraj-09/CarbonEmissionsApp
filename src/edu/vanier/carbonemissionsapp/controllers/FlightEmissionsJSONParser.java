/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.controllers;

import edu.vanier.carbonemissionsapp.models.AmountModel;
import edu.vanier.carbonemissionsapp.models.FlightEmissionsNoteModel;
import edu.vanier.carbonemissionsapp.models.FlightEmissionsOutputModel;
import edu.vanier.carbonemissionsapp.models.OutputModel;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cstuser
 */
public class FlightEmissionsJSONParser {
    private final String TAG = "FlightEmissionsJSONParser";
    
    public FlightEmissionsOutputModel parse(String result) {
        final String STATUS_KEY = "status";
        final String OUTPUT_KEY = "output";
        final String AMOUNT_KEY = "amounts";
        final String UNIT_KEY = "unit";
        final String DEFAULT_KEY = "default";
        final String VALUE_KEY = "value";
        final String TYPE_KEY = "type";
        final String NOTE_KEY = "notes";
        final String VERSION_KEY = "version";
        
        JSONObject jresponse = null;
        FlightEmissionsOutputModel emissionsOutput = new FlightEmissionsOutputModel();
        try {
            
            jresponse = new JSONObject(result);;
            
            JSONObject jsonOutputObj = jresponse.getJSONObject(OUTPUT_KEY);
            OutputModel output = new OutputModel();
            
            JSONArray notes = jsonOutputObj.getJSONArray(NOTE_KEY);
            
            for (int i = 0; i < notes.length(); i++) {
                JSONObject jsonNoteObj = notes.getJSONObject(i);
                FlightEmissionsNoteModel note = new FlightEmissionsNoteModel();
                
                note.setType(jsonNoteObj.getString(TYPE_KEY));
                note.setValue(jsonNoteObj.getString(VALUE_KEY));
                
                output.addNote(note);
            }
            JSONArray amounts = jsonOutputObj.getJSONArray(AMOUNT_KEY);
            for (int i = 0; i < amounts.length(); i++) {
                JSONObject jsonAmountObj = amounts.getJSONObject(i);
                AmountModel amount = new AmountModel();
                
                amount.setUnit(jsonAmountObj.getString(UNIT_KEY));
                amount.setBooleanDefault(jsonAmountObj.getBoolean(DEFAULT_KEY));
                amount.setType(jsonAmountObj.getString(TYPE_KEY));
                amount.setValue(jsonAmountObj.getDouble(VALUE_KEY));
                
                output.addAmount(amount);
            }
            emissionsOutput.setOutput(output);
            emissionsOutput.setStatus(jresponse.getString(STATUS_KEY));
            emissionsOutput.setVersion(jresponse.getString(VERSION_KEY));
        } catch (JSONException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return emissionsOutput;
    }
    
}
