/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.ui;

import edu.vanier.carbonemissionsapp.controllers.AirportsController;
import edu.vanier.carbonemissionsapp.controllers.FlightEmissionsJSONParser;
import edu.vanier.carbonemissionsapp.controllers.RESTAPIController;
import edu.vanier.carbonemissionsapp.models.AirportModel;
import edu.vanier.carbonemissionsapp.models.FlightEmissionsOutputModel;
import edu.vanier.carbonemissionsapp.models.OutputModel;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author cstuser
 */
public class FXMLFlightEmissionsController implements Initializable {
    
    @FXML
    private Label lblFrom;
    @FXML
    private Label lblTo;
    @FXML
    private ComboBox cmbFrom;
    @FXML
    private ComboBox cmbTo;
    @FXML
    private Button btnCalculate;
    @FXML
    private TextField txtFilterFrom;
    @FXML
    private TextField txtFilterTo;
    
    private AirportModel airportFrom;
    private AirportModel airportTo;
    private ObservableList<AirportModel> airportsObsList = FXCollections.observableArrayList();
    
    @FXML
    private void handleBtnCalculate(ActionEvent event) throws IOException {
        
        if (!isValid()) {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR);
            String message = "Please select two valid airports";
            errorMessage.setContentText(message);
            errorMessage.showAndWait();
            return;
        }
        
        airportFrom = (AirportModel) cmbFrom.getSelectionModel().getSelectedItem();
        airportTo = (AirportModel) cmbTo.getSelectionModel().getSelectedItem();
        
        String methodURL = 
                String.format("https://api.carbonkit.net/3.6/categories/Great_Circle_flight_methodology/calculation?type=great+circle+route&values.IATAcode1=%s&values.IATAcode2=%s",
                        airportFrom.getIataCode(), airportTo.getIataCode());
        
        RESTAPIController apiController = new RESTAPIController();
        
        String response = apiController.makeApiCall(methodURL);
        
        FlightEmissionsJSONParser jParser = new FlightEmissionsJSONParser();
        FlightEmissionsOutputModel output = jParser.parse(response);
        
        SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFlightResults.fxml"));
        pane.getItems().set(1, loader.load());
        
        OutputModel outputs = output.getOutput();
        FXMLFlightResultsController controller = loader.<FXMLFlightResultsController>getController();
        
        setData(outputs, controller);
    }
    
    private void filterFrom() {
        Predicate<AirportModel> filter = airport -> airport.toString().toUpperCase().contains(txtFilterFrom.getText().toUpperCase());
        FilteredList<AirportModel> filteredList = new FilteredList<>(airportsObsList, filter);
        cmbFrom.setItems(filteredList);
    }
    
    private void filterTo() {
        Predicate<AirportModel> filter = airport -> airport.toString().toUpperCase().contains(txtFilterTo.getText().toUpperCase());
        FilteredList<AirportModel> filteredList = new FilteredList<>(airportsObsList, filter);
        cmbTo.setItems(filteredList);
    }
    
    private boolean isValid(){
        if (cmbFrom.getSelectionModel().getSelectedItem() == null || 
                cmbTo.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        return cmbFrom.getSelectionModel().getSelectedItem().getClass() == AirportModel.class && 
                cmbTo.getSelectionModel().getSelectedItem().getClass() == AirportModel.class;
    }
    
    private void setData(OutputModel outputs, FXMLFlightResultsController controller) {
        
        DecimalFormat df = new DecimalFormat("#.##");
        
        controller.setTxtDistance(df.format(outputs.getAmounts().get("distance").getValue()) +
                " " +
                outputs.getAmounts().get("distance").getUnit());
        controller.setTxtCO2(df.format(outputs.getAmounts().get("CO2").getValue()) + 
                " " +
                outputs.getAmounts().get("CO2").getUnit());
        controller.setTxtNitrousOxide(df.format(outputs.getAmounts().get("nitrousOxideCO2e").getValue()) + 
                " " +
                outputs.getAmounts().get("nitrousOxideCO2e").getUnit());
        controller.setTxtMethane(df.format(outputs.getAmounts().get("methaneCO2e").getValue()) + 
                " " +
                outputs.getAmounts().get("methaneCO2e").getUnit());
        controller.setTxtTotalDirect(df.format(outputs.getAmounts().get("totalDirectCO2e").getValue()) + 
                " " +
                outputs.getAmounts().get("totalDirectCO2e").getUnit());
        controller.setTxtIndirect(df.format(outputs.getAmounts().get("indirectCO2e").getValue()) + 
                " " +
                outputs.getAmounts().get("indirectCO2e").getUnit());
        controller.setTxtTotal(df.format(outputs.getAmounts().get("lifeCycleCO2e").getValue()) + 
                " " +
                outputs.getAmounts().get("lifeCycleCO2e").getUnit());
        controller.setTxtTitle(String.format("Emissions from %s to %s", airportFrom.getName(), airportTo.getName()));
        
        controller.setBarChart(outputs);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AirportsController airportsController = new AirportsController();
        airportsObsList.addAll(airportsController.getAllAirports());
        cmbFrom.getItems().addAll(airportsObsList);
        cmbTo.getItems().addAll(airportsObsList);
        
        cmbFrom.setOnMouseClicked(event ->  filterFrom());
        cmbTo.setOnMouseClicked(event ->  filterTo());
    }    

    public AirportModel getAirportFrom() {
        return airportFrom;
    }

    public AirportModel getAirportTo() {
        return airportTo;
    }

    public void setAirportFrom(AirportModel airportFrom) {
        this.airportFrom = airportFrom;
    }

    public void setAirportTo(AirportModel airportTo) {
        this.airportTo = airportTo;
    }
    
    
}
