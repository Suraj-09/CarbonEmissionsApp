package edu.vanier.carbonemissionsapp.ui;

import edu.vanier.carbonemissionsapp.controllers.AirportsController;
import edu.vanier.carbonemissionsapp.controllers.FlightEmissionsJSONParser;
import edu.vanier.carbonemissionsapp.controllers.RESTAPIController;
import edu.vanier.carbonemissionsapp.models.AirportModel;
import edu.vanier.carbonemissionsapp.models.FlightEmissionsOutputModel;
import edu.vanier.carbonemissionsapp.models.OutputModel;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Suraj
 */
public class FXMLCFFlightController implements Initializable {
    
    @FXML
    private ComboBox cmbFrom;
    @FXML
    private ComboBox cmbTo;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnSkip;
    @FXML
    private TextField txtFilterFrom;
    @FXML
    private TextField txtFilterTo;
    
    private ObservableList<AirportModel> airportsObsList = FXCollections.observableArrayList();
    
    private AirportModel airportFrom;
    private AirportModel airportTo;
    
    public double flightEmissions = 0;
    
    public Date dateFrom;
    public Date dateTo;
    public double carEmissions;
    
    public void setData(Date dateFrom, Date dateTo, double carEmissions) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.carEmissions = carEmissions;
    }   
    
    @FXML
    private void handleButtons(ActionEvent event) throws IOException {
        if (event.getSource() == btnSkip) {
            passData(event);
        }
        else if (event.getSource() == btnNext) {
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

            OutputModel outputs = output.getOutput();
            flightEmissions = outputs.getAmounts().get("lifeCycleCO2e").getValue();

            passData(event);
        }
    }
    
    private boolean isValid(){
        if (cmbFrom.getSelectionModel().getSelectedItem() == null || 
                cmbTo.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        return cmbFrom.getSelectionModel().getSelectedItem().getClass() == AirportModel.class && 
                cmbTo.getSelectionModel().getSelectedItem().getClass() == AirportModel.class;
    }
    
    public double getFlightEmissions() {
        return flightEmissions;
    }
    
    private void passData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCFHome.fxml"));
            
        SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
        pane.getItems().set(1, loader.load());
        
        FXMLCFHomeController controller = loader.<FXMLCFHomeController>getController();
        controller.setData(dateFrom, dateTo, carEmissions, flightEmissions);
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

