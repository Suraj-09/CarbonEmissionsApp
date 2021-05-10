package edu.vanier.carbonemissionsapp.controllers;

import edu.vanier.carbonemissionsapp.models.CarModel;
import edu.vanier.carbonemissionsapp.helpers.ConnectionProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Suraj
 */
public class CarsController {

    public CarsController() {
    }

    public LinkedHashMap<Integer, CarModel> getAllCars(String tableName) {
        Connection dbConnection;
        Statement statement;
        ResultSet resultSet;
        LinkedHashMap<Integer, CarModel> carList = new LinkedHashMap<>();

        try {
            dbConnection = ConnectionProvider.getConnection("cars.db");
            statement = dbConnection.createStatement();

            String query = String.format("select * from %s", tableName);
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                CarModel car = new CarModel();

                car.setCarId(resultSet.getInt(1));
                car.setYear(resultSet.getInt(2));
                car.setMake(resultSet.getString(3));
                car.setModel(resultSet.getString(4));
                car.setVehicleClass(resultSet.getString(5));
                car.setEngineSize(resultSet.getString(6));
                car.setCylinders(resultSet.getString(7));
                car.setTransmission(resultSet.getString(8));
                car.setFuelType(resultSet.getString(9));
                car.setCityFuelConsumption(resultSet.getDouble(10));
                car.setHighwayFuelConsumption(resultSet.getDouble(11));
                car.setCarbonEmissions(resultSet.getInt(12));
                car.setCarbonRating(resultSet.getInt(13));

                carList.put(car.getCarId(), car);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carList;
    }

    public LinkedHashMap<Integer, CarModel> toResults(String tableName, ComboBox cmbYear, ComboBox cmbMake) {
        Connection dbConnection;
        Statement statement;
        ResultSet resultSet;
        LinkedHashMap<Integer, CarModel> carList = new LinkedHashMap<>();

        String year = cmbYear.getSelectionModel().getSelectedItem().toString();
        String make = cmbMake.getSelectionModel().getSelectedItem().toString();

        try {
            dbConnection = ConnectionProvider.getConnection("cars.db");
            statement = dbConnection.createStatement();

            String sql = "INSERT INTO " + tableName + " SELECT * FROM cars" + year + " WHERE make = '" + make + "'";
            statement.executeUpdate(sql);

            String query = String.format("select * from %s", tableName);
            resultSet = statement.executeQuery(query);
            int i = 0;
            while (resultSet.next()) {
                CarModel car = new CarModel();

                car.setCarId(resultSet.getInt(1));
                car.setYear(resultSet.getInt(2));
                car.setMake(resultSet.getString(3));
                car.setModel(resultSet.getString(4));
                car.setVehicleClass(resultSet.getString(5));
                car.setEngineSize(resultSet.getString(6));
                car.setCylinders(resultSet.getString(7));
                car.setTransmission(resultSet.getString(8));
                car.setFuelType(resultSet.getString(9));
                car.setCityFuelConsumption(resultSet.getDouble(10));
                car.setHighwayFuelConsumption(resultSet.getDouble(11));
                car.setCarbonEmissions(resultSet.getInt(12));
                car.setCarbonRating(resultSet.getInt(13));

                carList.put(i++, car);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carList;
    }

    public LinkedHashMap<Integer, CarModel> refine(String tableName, String columnValue, ComboBox cmbValue) {
        Connection dbConnection;
        Statement statement;
        ResultSet resultSet;
        LinkedHashMap<Integer, CarModel> carList = new LinkedHashMap<>();

        String value = cmbValue.getSelectionModel().getSelectedItem().toString();

        try {
            dbConnection = ConnectionProvider.getConnection("cars.db");
            statement = dbConnection.createStatement();

            String sql = "DELETE FROM " + tableName + " WHERE " + columnValue + " != '" + value + "'";
            statement.executeUpdate(sql);

            String query = String.format("select * from %s", tableName);
            resultSet = statement.executeQuery(query);
            int i = 0;
            while (resultSet.next()) {
                CarModel car = new CarModel();

                car.setCarId(resultSet.getInt(1));
                car.setYear(resultSet.getInt(2));
                car.setMake(resultSet.getString(3));
                car.setModel(resultSet.getString(4));
                car.setVehicleClass(resultSet.getString(5));
                car.setEngineSize(resultSet.getString(6));
                car.setCylinders(resultSet.getString(7));
                car.setTransmission(resultSet.getString(8));
                car.setFuelType(resultSet.getString(9));
                car.setCityFuelConsumption(resultSet.getDouble(10));
                car.setHighwayFuelConsumption(resultSet.getDouble(11));
                car.setCarbonEmissions(resultSet.getInt(12));
                car.setCarbonRating(resultSet.getInt(13));

                carList.put(i++, car);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carList;
    }

    public void emptyResults(String tableName) {
        Connection dbConnection;
        Statement statement;

        try {
            dbConnection = ConnectionProvider.getConnection("cars.db");
            statement = dbConnection.createStatement();

            String sql = String.format("DELETE FROM %s", tableName);
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public LinkedHashMap<Integer, CarModel> toCompareResults(ComboBox cmbYear) {
        Connection dbConnection;
        Statement statement;
        ResultSet resultSet;
        LinkedHashMap<Integer, CarModel> carList = new LinkedHashMap<>();

        String year = cmbYear.getSelectionModel().getSelectedItem().toString();
        
        try {
            dbConnection = ConnectionProvider.getConnection("cars.db");
            statement = dbConnection.createStatement();

            String sql = String.format("INSERT INTO compareResults SELECT * FROM results ", year);
            statement.executeUpdate(sql);
            
            resultSet = statement.executeQuery("select * from compareResults");
            int i = 0;
            while (resultSet.next()) {
                CarModel car = new CarModel();

                car.setCarId(resultSet.getInt(1));
                car.setYear(resultSet.getInt(2));
                car.setMake(resultSet.getString(3));
                car.setModel(resultSet.getString(4));
                car.setVehicleClass(resultSet.getString(5));
                car.setEngineSize(resultSet.getString(6));
                car.setCylinders(resultSet.getString(7));
                car.setTransmission(resultSet.getString(8));
                car.setFuelType(resultSet.getString(9));
                car.setCityFuelConsumption(resultSet.getDouble(10));
                car.setHighwayFuelConsumption(resultSet.getDouble(11));
                car.setCarbonEmissions(resultSet.getInt(12));
                car.setCarbonRating(resultSet.getInt(13));

                carList.put(i++, car);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carList;
    }
    
    public int getCompareResultsSize() {
        int size = 0;
        Connection dbConnection;
        Statement statement;
        ResultSet resultSet;
        try {
            dbConnection = ConnectionProvider.getConnection("cars.db");
            statement = dbConnection.createStatement();

            resultSet = statement.executeQuery("select * from compareResults");
            while (resultSet.next()) {
                size++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return size;
    }
    
}
