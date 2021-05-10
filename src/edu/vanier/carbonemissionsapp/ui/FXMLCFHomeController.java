/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.ui;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Suraj
 */
public class FXMLCFHomeController implements Initializable {

    final double FOSSIL_EF = 0.7;
    final double HYDRO_EF = 0.015;
    final double NATURAL_GAS_EF = 6.6;
    final double HEATING_EF = 11.6;
    @FXML
    Button btnNext;
    @FXML
    Button btnSkip;
    @FXML
    Label lblHeating;
    @FXML
    Label lblFinal;
    @FXML
    Label lblThermsUnit;
    @FXML
    Label lblHeatingUnit;
    @FXML
    Label lblElecUnit;
    @FXML
    Label lblElectricity;
    @FXML
    Label lblTotalElec;
    @FXML
    Label lblTotal;
    @FXML
    Label lblTotalNat;
    @FXML
    Label lblTotalHeat;
    @FXML
    Label lblFinalResult;
    @FXML
    TextField txtFinalResult;
    @FXML
    TextField txtHeating;
    @FXML
    TextField txtElectricity;
    @FXML
    TextField txtNaturalGas;
    @FXML
    ComboBox cmbFuel;
    
    private Double totalHomeEmissions = 0.0;
    private Double naturalGas;
    private Double heating;
    private Double totalElectric;
    
    
    public Date dateFrom;
    public Date dateTo;
    public double carEmissions;
    public double flightEmissions;
    
    
    public void setData(Date dateFrom, Date dateTo, double carEmissions, double flightEmissions) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.carEmissions = carEmissions;
        this.flightEmissions = flightEmissions;
    }
    
    @FXML
    private void handleButtons(ActionEvent event) throws IOException {
        if (event.getSource() == btnSkip) {
            passData(event);
        }
        else if (event.getSource() == btnNext) {
            if(txtHeating.getText() == null || txtNaturalGas.getText() == null || txtElectricity.getText() == null){
                lblFinalResult.setText("ERROR: One of the fields are empty");
                txtFinalResult.setText("0");
            }
            else{
                Double userHeating = Double.parseDouble(txtHeating.getText());
                Double userNaturalGas = Double.parseDouble(txtNaturalGas.getText());
                Double electricity = Double.parseDouble(txtElectricity.getText());

                    if(cmbFuel.getSelectionModel().getSelectedItem() == "Hydro Electricity"){
                        totalElectric = electricity * HYDRO_EF;
                    }
                    else{
                        totalElectric = electricity * FOSSIL_EF;
                    }
                    naturalGas = userNaturalGas * NATURAL_GAS_EF;
                    heating = userHeating * HEATING_EF;
                    totalHomeEmissions = totalElectric + naturalGas + heating;

                passData(event);
            }   
        }
    }
    
    private void passData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCFResults.fxml"));
            
        SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
        pane.getItems().set(1, loader.load());  

        FXMLCFResultsController controller = loader.<FXMLCFResultsController>getController();
        controller.setData(dateFrom, dateTo, carEmissions, flightEmissions, totalHomeEmissions);
        controller.setBarChart(carEmissions, flightEmissions, totalHomeEmissions);
    }
    
    public double getHomeEmissions() {
        return totalHomeEmissions;
    }
    
    @FXML
    private void handleSelection(ActionEvent event) throws IOException {
        cmbFuel.getItems().add(1, "Hydro Electricity");
        cmbFuel.getItems().add(2,"Electricity");
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbFuel.getItems().setAll("Hydro Electricity", "Electricity");
    }    
    
}

