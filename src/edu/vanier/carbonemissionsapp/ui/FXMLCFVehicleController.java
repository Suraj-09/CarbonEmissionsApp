/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.ui;


import edu.vanier.carbonemissionsapp.controllers.CarSelectionsController;
import edu.vanier.carbonemissionsapp.models.CarModel;
import edu.vanier.carbonemissionsapp.controllers.UsersController;
import edu.vanier.carbonemissionsapp.helpers.ConnectionProvider;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Suraj
 */
public class FXMLCFVehicleController implements Initializable {
        
    @FXML
    private ComboBox cmbYear;
    
    @FXML
    private ComboBox cmbMake;
    
    @FXML
    private ComboBox cmbModel;
    
    @FXML
    private ComboBox cmbEngine;
    
    @FXML
    private ComboBox cmbTransmission;
    
    @FXML
    private Button btnNext;
    
    @FXML
    private Button btnSkip;
    
    @FXML
    private TextField txtDistance;
    
    public double carEmissions = 0;
    
    LinkedHashMap<Integer, CarModel> carList = new LinkedHashMap<>();
    
    public Date dateFrom;
    public Date dateTo;
    
    public void setData(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        if(!UsersController.currentUser.equals("")) {
            Connection dbConnection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            boolean flag = false;
            try{
                dbConnection = ConnectionProvider.getConnection("users.db");
                statement = dbConnection.createStatement();
                String sql = "INSERT INTO vehiclesdata (username,year,make,model,engine,transmission,distance)" + "values('" 
                        + UsersController.currentUser + "','"
                        + cmbYear.getSelectionModel().getSelectedItem().toString() + "','" + cmbMake.getSelectionModel().getSelectedItem().toString()+ "','" 
                        + cmbModel.getSelectionModel().getSelectedItem().toString() + cmbEngine.getSelectionModel().getSelectedItem().toString()+ "','" 
                        + cmbTransmission.getSelectionModel().getSelectedItem().toString() + "','" + txtDistance.getText() + "')";
                int status = statement.executeUpdate(sql);
                if(status > 0)
                    flag = true;
                else
                    flag = false;


            } catch (SQLException e) {
                Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    @FXML
    private void handleSelections(ActionEvent event) throws IOException {
        String tablename = "results";
        
        CarSelectionsController carSelection = new CarSelectionsController();
        
        if (event.getSource() == btnSkip) {
            passData(event);
        }
        
        if (event.getSource() == cmbYear) {
            carList = carSelection.yearSelection(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission, carList, tablename);
        }
        
        if (event.getSource() == cmbMake) {
            carList = carSelection.makeSelection(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission, carList, tablename);
        }
        
        if (event.getSource() == cmbModel) {
            carList = carSelection.modelSelection(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission, carList, tablename);
        }
        
        if (event.getSource() == cmbEngine) {
            carList = carSelection.engineSelection(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission, carList, tablename);
        }
        
        if (event.getSource() == cmbTransmission) {
            carList = carSelection.transmissionSelection(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission, carList, tablename);
        }
        
        if (event.getSource() == btnNext) {
            carSelection.validation(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission, txtDistance);
            
            CarModel car = carList.get(0);
            
            double distance = Double.parseDouble(txtDistance.getText());
            carEmissions = car.getCarbonEmissions() * 0.001 * distance;
            
            passData(event);
        }
    }
    
    private void passData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCFFlight.fxml"));
            
        SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
        pane.getItems().set(1, loader.load());
        
        FXMLCFFlightController controller = loader.<FXMLCFFlightController>getController();
        controller.setData(dateFrom, dateTo, carEmissions);
    }
    
    public double getCarEmissions() {
        return carEmissions;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 2000; i < 2020; i++) {
            cmbYear.getItems().add(i);
        }
        cmbMake.setDisable(true);
        cmbModel.setDisable(true);
        cmbEngine.setDisable(true);
        cmbTransmission.setDisable(true);
    }
}
