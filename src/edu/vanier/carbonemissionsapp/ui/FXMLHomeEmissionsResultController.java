/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kevin Patel
 */
public class FXMLHomeEmissionsResultController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnBack;
    @FXML
    TextField txtFinalResult;
    @FXML
    TextField txtHeating;
    @FXML
    TextField txtElectricity;
    @FXML
    TextField txtNaturalGas;
    @FXML
    TextField txtTotal;
    @FXML
    TextField txtHeat;
    @FXML
    TextField txtElec;
    @FXML
    TextField txtGas;
    @FXML
    private BarChart<String, Double> homeEmissionsChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    double result;
    final double AVERAGE_EMISSIONS = 24494;
    
    @FXML
    private void handleBtnBack(ActionEvent event) throws IOException {
        if (event.getSource() == btnBack) {
            SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLHomeEmissions.fxml"));
            pane.getItems().set(1, loader.load());
        }
    }
    
    public void setBarChart() {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Your Home", result));
        series.getData().add(new XYChart.Data("Average", AVERAGE_EMISSIONS));
        homeEmissionsChart.getData().add(series);
    }
    
    public void setData(String finalResult, String heating, 
            String electricity, String naturalGas) {
        result = Double.parseDouble(finalResult);
        txtFinalResult.setText(finalResult + " kg");
        txtHeating.setText(heating + " Liters");
        txtElectricity.setText(electricity + " kwh");
        txtNaturalGas.setText(naturalGas + " Therms");
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeEmissionsChart.setLegendVisible(false);
    }    
    
}
