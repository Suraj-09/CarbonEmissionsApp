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
 *
 * @author Suraj
 */
public class FXMLVehicleEmissionsController implements Initializable {
    
    @FXML
    private ComboBox cmbCompare;
    
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
    private Button btnCalculate;
    
    @FXML
    private TextField txtDistance;
    
    LinkedHashMap<Integer, CarModel> carList = new LinkedHashMap<>();
    
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
    private void handleBtnCompare(ActionEvent event) throws IOException {
        if (event.getSource() == cmbCompare) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLVehicleCompareEmissions.fxml"));
            SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
            pane.getItems().set(1, loader.load());
        }
    }
    
    @FXML
    private void handleSelections(ActionEvent event) throws IOException {
        String tablename = "results";
        
        CarSelectionsController carSelection = new CarSelectionsController();
        
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
        
        if (event.getSource() == btnCalculate) {
            carSelection.validation(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission, txtDistance);
            
            CarModel car = carList.get(0);
            
            double distance = Double.parseDouble(txtDistance.getText());
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLVehicleResults.fxml"));
            
            SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
            pane.getItems().set(1, loader.load());
            
            FXMLVehicleResultsController controller = loader.<FXMLVehicleResultsController>getController();
            
            controller.setData(car, distance);
        }
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
        
        cmbCompare.getItems().add("Yes");
        cmbCompare.getItems().add("No");
        cmbCompare.getSelectionModel().selectLast();
    }
}
