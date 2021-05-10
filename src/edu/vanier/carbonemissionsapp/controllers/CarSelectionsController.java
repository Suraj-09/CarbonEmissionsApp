package edu.vanier.carbonemissionsapp.controllers;

import edu.vanier.carbonemissionsapp.models.CarModel;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author cstuser
 */
public class CarSelectionsController {

    private CarsController carController = new CarsController();
    
    public CarSelectionsController() {
    }
    
    public LinkedHashMap<Integer, CarModel> yearSelection(ComboBox cmbYear, ComboBox cmbMake, ComboBox cmbModel,ComboBox cmbEngine,ComboBox cmbTransmission, LinkedHashMap<Integer, CarModel> carList, String tablename) {
        carController.emptyResults(tablename);
        cmbMake.getItems().clear();
        cmbModel.getItems().clear();
        cmbEngine.getItems().clear();
        cmbTransmission.getItems().clear();

        String year = cmbYear.getSelectionModel().getSelectedItem().toString();

        carList = carController.getAllCars("cars" + year);
        LinkedHashSet<String> carMakeList = new LinkedHashSet<>();

        for (Integer key : carList.keySet()) 
            carMakeList.add(carList.get(key).getMake());

        cmbMake.setDisable(false);
        cmbMake.getItems().addAll(carMakeList);
        
        return carList;
    }
    
    public LinkedHashMap<Integer, CarModel> makeSelection(ComboBox cmbYear, ComboBox cmbMake, ComboBox cmbModel,ComboBox cmbEngine,ComboBox cmbTransmission, LinkedHashMap<Integer, CarModel> carList, String tablename) {
        cmbModel.getItems().clear();
        cmbEngine.getItems().clear();
        cmbTransmission.getItems().clear();
        try {
            carList = carController.toResults(tablename, cmbYear, cmbMake);
        } catch (Exception e) {
        }
        LinkedHashSet<String> carModelList = new LinkedHashSet<>();

        for (Integer key : carList.keySet())
            carModelList.add(carList.get(key).getModel());

        cmbModel.setDisable(false);
        cmbModel.getItems().addAll(carModelList);
        
        return carList;
    }
    
    public LinkedHashMap<Integer, CarModel> modelSelection(ComboBox cmbYear, ComboBox cmbMake, ComboBox cmbModel,ComboBox cmbEngine,ComboBox cmbTransmission, LinkedHashMap<Integer, CarModel> carList, String tablename) {
        cmbEngine.getItems().clear();
        cmbTransmission.getItems().clear();

        carController.emptyResults("results");
        try {
            carController.toResults(tablename, cmbYear, cmbMake);
            carList = carController.refine(tablename, "model", cmbModel);
        } catch (Exception e) {
        }
        LinkedHashSet<String> carEngineList = new LinkedHashSet<>();

        for (Integer key : carList.keySet()) 
             carEngineList.add(carList.get(key).getEngineSize());

        cmbEngine.setDisable(false);
        cmbEngine.getItems().addAll(carEngineList);
        
        return carList;
    }
    
    public LinkedHashMap<Integer, CarModel> engineSelection(ComboBox cmbYear, ComboBox cmbMake, ComboBox cmbModel,ComboBox cmbEngine,ComboBox cmbTransmission, LinkedHashMap<Integer, CarModel> carList, String tablename) {
        cmbTransmission.getItems().clear();
            
        carController.emptyResults("results");
        try {
            carController.toResults(tablename, cmbYear, cmbMake);
            carController.refine(tablename, "model", cmbModel);
            carList = carController.refine(tablename, "engine_size", cmbEngine);
        } catch (Exception e) {
        }
        LinkedHashSet<String> carTransmissionList = new LinkedHashSet<>();

        for (Integer key : carList.keySet())
            carTransmissionList.add(carList.get(key).getTransmission());

        cmbTransmission.setDisable(false);
        cmbTransmission.getItems().addAll(carTransmissionList);
        
        return carList;
    }
    
    public LinkedHashMap<Integer, CarModel> transmissionSelection(ComboBox cmbYear, ComboBox cmbMake, ComboBox cmbModel,ComboBox cmbEngine,ComboBox cmbTransmission, LinkedHashMap<Integer, CarModel> carList, String tablename) {
        carController.emptyResults(tablename);
        try {
            carController.toResults(tablename, cmbYear, cmbMake);
            carController.refine(tablename, "model", cmbModel);
            carController.refine(tablename, "engine_size", cmbEngine);
            carList = carController.refine(tablename, "transmission", cmbTransmission);
        } catch (Exception e) {
        }
        
        return carList;
    }
    
    public void validation(ComboBox cmbYear, ComboBox cmbMake, ComboBox cmbModel,ComboBox cmbEngine,ComboBox cmbTransmission, TextField txtMileage){
        if (!allFiedsValid(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission) || !isDistanceFieldValid(txtMileage)) {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR);
                String message;
                
                if (!allFiedsValid(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission))
                    message = "All fields must be filled";
                else
                    message = "Distance must be a valid number";
                
                errorMessage.setContentText(message);
                errorMessage.showAndWait();
            }
    }
    
    public void validationComp(ComboBox cmbYear, ComboBox cmbMake, ComboBox cmbModel,ComboBox cmbEngine,ComboBox cmbTransmission, ComboBox cmbYearComp, ComboBox cmbMakeComp, ComboBox cmbModelComp, ComboBox cmbEngineComp, ComboBox cmbTransmissionComp, TextField txtMileage){
        if (!allFiedsValid(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission) || 
                !allFiedsValid(cmbYearComp, cmbMakeComp, cmbModelComp, cmbEngineComp, cmbTransmissionComp) || 
                !isDistanceFieldValid(txtMileage)) {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR);
                String message;
                
                if (!allFiedsValid(cmbYear, cmbMake, cmbModel, cmbEngine, cmbTransmission))
                    message = "All fields must be filled";
                else
                    message = "Distance must be a valid number";
                
                errorMessage.setContentText(message);
                errorMessage.showAndWait();
            }
    }
    
    private boolean allFiedsValid(ComboBox cmbYear, ComboBox cmbMake, ComboBox cmbModel,ComboBox cmbEngine,ComboBox cmbTransmission) {
        Boolean isValid = true;
        if (cmbYear.getItems().isEmpty() || cmbMake.getItems().isEmpty() || 
                cmbModel.getItems().isEmpty() || cmbEngine.getItems().isEmpty() ||
                cmbTransmission.getItems().isEmpty()) {
            isValid = false;
        }        
        return isValid;
    }
    
    private boolean isDistanceFieldValid(TextField txtMileage) {
        Boolean isValid = true;
        try {
            Double.parseDouble(txtMileage.getText());
        } catch (NumberFormatException e) {
            isValid = false;
        }        
        return isValid;
    }
    
}
    
