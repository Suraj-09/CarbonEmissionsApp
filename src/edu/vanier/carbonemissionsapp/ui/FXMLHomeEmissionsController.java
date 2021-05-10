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
 * @author KP
 */
public class FXMLHomeEmissionsController implements Initializable {
    final double FOSSIL_EF = 0.7;
    final double HYDRO_EF = 0.015;
    final double NATURAL_GAS_EF = 6.6;
    final double HEATING_EF = 11.6;
    @FXML
    Button btnCalculate;
    @FXML
    Button btnGraph;
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
    
    @FXML
    private void handleCalculate(ActionEvent event) throws IOException {
        double naturalGas;
        double heating;
        double totalEmissions;
        double result;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if(txtHeating.getText() == null || txtNaturalGas.getText() == null || txtElectricity.getText() == null){
            lblFinalResult.setText("ERROR: One of the fields are empty");
            txtFinalResult.setText("0");
        }
        else{
            double userHeating = Double.parseDouble(txtHeating.getText());
            double userNaturalGas = Double.parseDouble(txtNaturalGas.getText());
            double electricity = Double.parseDouble(txtElectricity.getText());

                if(cmbFuel.getSelectionModel().getSelectedItem() == "Hydro Electricity"){
                    lblTotalElec.setText(electricity * HYDRO_EF + "");
                }
                else{
                    lblTotalElec.setText(electricity * FOSSIL_EF + "");
                }
                naturalGas = userNaturalGas * NATURAL_GAS_EF;
                heating = userHeating * HEATING_EF;
                totalEmissions = Double.parseDouble(lblTotalElec.getText()) 
                        + naturalGas + heating;
                lblFinalResult.setText("Your Total Household Emissions:");
                txtFinalResult.setText(decimalFormat.format(totalEmissions) + "");
                result = Double.parseDouble(txtFinalResult.getText());
                if(event.getSource() == btnGraph){
                    passData(event);
                }
        }
    }
    
    @FXML
    public void setData(TextField txtFinalResult, TextField txtHeating, 
            TextField txtElectricity, TextField txtNaturalGas) {
        this.txtFinalResult = txtFinalResult;
        this.txtHeating = txtHeating;
        this.txtElectricity = txtElectricity;
        this.txtNaturalGas = txtNaturalGas;
    } 
    
    @FXML
    private void passData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLHomeEmissionsResult.fxml"));
            
        SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
        pane.getItems().set(1, loader.load());
        
        FXMLHomeEmissionsResultController controller = loader.<FXMLHomeEmissionsResultController>getController();
        controller.setData(txtFinalResult.getText(),txtHeating.getText(),txtElectricity.getText(),txtNaturalGas.getText());
        controller.setBarChart();
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
