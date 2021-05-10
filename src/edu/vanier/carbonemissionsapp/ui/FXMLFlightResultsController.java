/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.ui;

import edu.vanier.carbonemissionsapp.models.OutputModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author mjj
 */
public class FXMLFlightResultsController implements Initializable {

    @FXML
    private Text txtTitle;
    @FXML
    private Label lblDistance;
    @FXML
    private Label lblCO2;
    @FXML
    private Label lblNitrousOxide;
    @FXML
    private Label lblMethane;
    @FXML
    private Label lblTotalDirect;
    @FXML
    private Label lblIndirect;
    @FXML
    private Label lblTotal;
    @FXML
    private TextField txtDistance;
    @FXML
    private TextField txtCO2;
    @FXML
    private TextField txtNitrousOxide;
    @FXML
    private TextField txtMethane;
    @FXML
    private TextField txtTotalDirect;
    @FXML
    private TextField txtIndirect;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnBack;
    @FXML
    private BarChart<String, Double> barChart;
    
    
    @FXML
    private void handleBtnBack(ActionEvent event) throws IOException {
        if (event.getSource() == btnBack) {
            SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFlightEmissions.fxml"));
            pane.getItems().set(1, loader.load());
        }
        
    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void setTxtTitle(String title) {
        txtTitle.setText(title);
    }

    public void setTxtDistance(String distance) {
        txtDistance.setText(distance);
    }

    public void setTxtCO2(String co2) {
        txtCO2.setText(co2);
    }

    public void setTxtNitrousOxide(String nitrousOxide) {
        txtNitrousOxide.setText(nitrousOxide);
    }

    public void setTxtMethane(String methane) {
        txtMethane.setText(methane);
    }

    public void setTxtTotalDirect(String totalDirect) {
        txtTotalDirect.setText(totalDirect);
    }

    public void setTxtIndirect(String indirect) {
        txtIndirect.setText(indirect);
    }

    public void setTxtTotal(String total) {
        txtTotal.setText(total);
    }

    public void setBarChart(OutputModel output) {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("CO2", output.getAmounts().get("CO2").getValue()));
        series.getData().add(new XYChart.Data("N2O", output.getAmounts().get("nitrousOxideCO2e").getValue()));
        series.getData().add(new XYChart.Data("CH4", output.getAmounts().get("methaneCO2e").getValue()));
        series.getData().add(new XYChart.Data("total direct", output.getAmounts().get("totalDirectCO2e").getValue()));
        series.getData().add(new XYChart.Data("indirect", output.getAmounts().get("indirectCO2e").getValue()));
        series.getData().add(new XYChart.Data("total", output.getAmounts().get("lifeCycleCO2e").getValue()));
        barChart.getData().add(series);
    }
    
    
}
