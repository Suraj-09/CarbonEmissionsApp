/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.ui;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Suraj
 */
public class FXMLCFResultsController implements Initializable {


    @FXML
    private Label lblVehicleEmissions;

    @FXML
    private Label lblFlightEmissions;

    @FXML
    private Label lblHomeEmissions;

    @FXML
    private Label lblOverallEmissions;

    @FXML
    private Button btnQuit;
    
    @FXML
    private ComboBox cmbTime;
    
    @FXML
    private BarChart<String, Double> barChart;
    
    public Date dateFrom;
    public Date dateTo;
    public double carEmissions;
    public double flightEmissions;
    public double homeEmissions;
    public double totalEmissions;
    
    private int days = 1;
    
    final private String [] TIME_PERIODS = {"overall", "daily", "weekly", "monthly", "yearly"};
    final private int WEEK = 7;
    final private int MONTH = 30;
    final private int YEAR = 365;
    
    
    public void setData(Date dateFrom, Date dateTo, double carEmissions, double flightEmissions, double homeEmissions) {
        DecimalFormat df = new DecimalFormat("#.##");
        
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.carEmissions = carEmissions;
        this.flightEmissions = flightEmissions;
        this.homeEmissions = homeEmissions;
        
        lblVehicleEmissions.setText(df.format(this.carEmissions));
        lblFlightEmissions.setText(df.format(this.flightEmissions));
        lblHomeEmissions.setText(df.format(this.homeEmissions));
        
        totalEmissions = this.carEmissions + this.flightEmissions + this.homeEmissions;
        lblOverallEmissions.setText(df.format(totalEmissions));
    }
    
    public void handleSelections(ActionEvent event) {
        if (event.getSource() == cmbTime) {
            String time = cmbTime.getSelectionModel().getSelectedItem().toString();
            DecimalFormat df = new DecimalFormat("#.##");
            
            long diff = Math.abs(dateFrom.getTime() - dateTo.getTime());
            days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
            
            if (time.equals(TIME_PERIODS[0])) {
                lblVehicleEmissions.setText(df.format(carEmissions));
                lblFlightEmissions.setText(df.format(flightEmissions));
                lblHomeEmissions.setText(df.format(homeEmissions));
                lblOverallEmissions.setText(df.format(totalEmissions));
                updateBarChart();
            }
            else if (time.equals(TIME_PERIODS[1])) {
                lblVehicleEmissions.setText(df.format(carEmissions / days));
                lblFlightEmissions.setText(df.format(flightEmissions / days));
                lblHomeEmissions.setText(df.format(homeEmissions / days));
                lblOverallEmissions.setText(df.format(totalEmissions / days));
                updateBarChart();
            }
            else if (time.equals(TIME_PERIODS[2])) {
                lblVehicleEmissions.setText(df.format(carEmissions / days * WEEK));
                lblFlightEmissions.setText(df.format(flightEmissions / days * WEEK));
                lblHomeEmissions.setText(df.format(homeEmissions / days * WEEK));
                lblOverallEmissions.setText(df.format(totalEmissions / days * WEEK));
                updateBarChart();
            }
            else if (time.equals(TIME_PERIODS[3])) {
                lblVehicleEmissions.setText(df.format(carEmissions / days * MONTH));
                lblFlightEmissions.setText(df.format(flightEmissions / days * MONTH));
                lblHomeEmissions.setText(df.format(homeEmissions / days * MONTH));
                lblOverallEmissions.setText(df.format(totalEmissions / days * MONTH));
                updateBarChart();
            }
            else if (time.equals(TIME_PERIODS[4])) {
                lblVehicleEmissions.setText(df.format(carEmissions / days * YEAR));
                lblFlightEmissions.setText(df.format(flightEmissions / days * YEAR));
                lblHomeEmissions.setText(df.format(homeEmissions / days * YEAR));
                lblOverallEmissions.setText(df.format(totalEmissions / days * YEAR));
                updateBarChart();
            }
        }
        
        if (event.getSource() == btnQuit)
                Platform.exit();
    }
    
    public void setBarChart(double carEmissions, double flightEmissions, double homeEmission) {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Vehicle", carEmissions));
        series.getData().add(new XYChart.Data("Flights", flightEmissions));
        series.getData().add(new XYChart.Data("Home", homeEmission));
        barChart.getData().add(series);
    }
    
    public void updateBarChart() {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Vehicle", Double.parseDouble(lblVehicleEmissions.getText())));
        series.getData().add(new XYChart.Data("Flights", Double.parseDouble(lblFlightEmissions.getText())));
        series.getData().add(new XYChart.Data("Home", Double.parseDouble(lblHomeEmissions.getText())));
        barChart.getData().clear();
        barChart.getData().add(series);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        for (String TIME_PERIOD : TIME_PERIODS) {
            cmbTime.getItems().add(TIME_PERIOD);
        }
        
        cmbTime.getSelectionModel().selectFirst();
        
    }    
    
}
