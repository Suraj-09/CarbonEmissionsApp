package edu.vanier.carbonemissionsapp.ui;

import edu.vanier.carbonemissionsapp.controllers.CarSelectionsController;
import edu.vanier.carbonemissionsapp.models.CarModel;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Suraj
 */
public class FXMLVehicleCompareEmissionsController implements Initializable {

    @FXML
    private ComboBox cmbCompare;

    @FXML
    private ComboBox cmbYearComp;
    
    @FXML
    private ComboBox cmbMakeComp;
    
    @FXML
    private ComboBox cmbModelComp;
    
    @FXML
    private ComboBox cmbEngineComp;
    
    @FXML
    private ComboBox cmbTransmissionComp;
    
    @FXML
    private Button btnCalculate;
    
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
    private TextField txtDistance;
    
    LinkedHashMap<Integer, CarModel> carList = new LinkedHashMap<>();
    LinkedHashMap<Integer, CarModel> compareCarList = new LinkedHashMap<>();
    
    @FXML
    private void handleBtnCompare(ActionEvent event) throws IOException {
        if (event.getSource() == cmbCompare) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLVehicleEmissions.fxml"));
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
    }
    
    @FXML
    private void handleCompareSelections(ActionEvent event) throws IOException {       
        String tablename = "compareResults";
        CarSelectionsController carSelection = new CarSelectionsController();
        
        if (event.getSource() == cmbYearComp) {
            compareCarList = carSelection.yearSelection(cmbYearComp, cmbMakeComp, cmbModelComp, cmbEngineComp, cmbTransmissionComp, compareCarList, tablename);
        }
        
        if (event.getSource() == cmbMakeComp) {
            compareCarList = carSelection.makeSelection(cmbYearComp, cmbMakeComp, cmbModelComp, cmbEngineComp, cmbTransmissionComp, compareCarList, tablename);
        }
        
        if (event.getSource() == cmbModelComp) {
            compareCarList = carSelection.modelSelection(cmbYearComp, cmbMakeComp, cmbModelComp, cmbEngineComp, cmbTransmissionComp, compareCarList, tablename);
        }
        
        if (event.getSource() == cmbEngineComp) {
            compareCarList = carSelection.engineSelection(cmbYearComp, cmbMakeComp, cmbModelComp, cmbEngineComp, cmbTransmissionComp, compareCarList, tablename);
        }
        
        if (event.getSource() == cmbTransmissionComp) {
            compareCarList = carSelection.transmissionSelection(cmbYearComp, cmbMakeComp, cmbModelComp, cmbEngineComp, cmbTransmissionComp, compareCarList, tablename);
        }
        
        if (event.getSource() == btnCalculate) {
            carSelection.validationComp(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission, cmbYearComp, cmbMakeComp, cmbModelComp, cmbEngineComp, cmbTransmissionComp, txtDistance);

            CarModel car = carList.get(0);
            CarModel compareCar = compareCarList.get(0);
            
            double distance = Double.parseDouble(txtDistance.getText());
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLVehicleResults.fxml"));
            
            SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
            pane.getItems().set(1, loader.load());
            
            FXMLVehicleResultsController controller = loader.<FXMLVehicleResultsController>getController();
            controller.setData(car, compareCar, distance);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 2000; i < 2020; i++) {
            cmbYear.getItems().add(i);
            cmbYearComp.getItems().add(i);
        }
        
        cmbMakeComp.setDisable(true);
        cmbModelComp.setDisable(true);
        cmbEngineComp.setDisable(true);
        cmbTransmissionComp.setDisable(true);
        
        cmbMake.setDisable(true);
        cmbModel.setDisable(true);
        cmbEngine.setDisable(true);
        cmbTransmission.setDisable(true);
        
        cmbCompare.getItems().add("Yes");
        cmbCompare.getItems().add("No");
        cmbCompare.getSelectionModel().selectFirst();
    }
    
}
