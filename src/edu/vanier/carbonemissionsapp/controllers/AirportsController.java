/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.controllers;

import edu.vanier.carbonemissionsapp.helpers.ConnectionProvider;
import edu.vanier.carbonemissionsapp.models.AirportModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mjj
 */
public class AirportsController {
    public List<AirportModel> getAllAirports() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String tableName = "airports";
        List<AirportModel> airports = new ArrayList();
        
        try {
            connection = ConnectionProvider.getConnection("airports.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            String query = String.format("Select * from %s", tableName);
            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                AirportModel airport = new AirportModel();
                
                airport.setId(resultSet.getInt(1));
                airport.setIataCode(resultSet.getString(5));
                airport.setName(resultSet.getString(2));
                airport.setCity(resultSet.getString(3));
                airport.setCountry(resultSet.getString(4));
                
                airports.add(airport);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AirportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return airports;
    }
}
