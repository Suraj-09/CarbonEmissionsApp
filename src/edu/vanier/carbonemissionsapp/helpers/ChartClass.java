package edu.vanier.carbonemissionsapp.helpers;

import edu.vanier.carbonemissionsapp.models.CarModel;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Suraj
 */
public class ChartClass {
    
    public static Group vehicleConsumptionChart(CarModel car) {
        String carName = car.getYear() + " " + car.getMake() + " " + car.getModel();
         
        CategoryAxis xAxis = new CategoryAxis();  
        xAxis.setCategories(FXCollections.<String>
        observableArrayList(Arrays.asList("City Fuel Consumption", "Highway Fuel Consumption")));

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Fuel Consumption (L/100km)");

        javafx.scene.chart.BarChart<String, Number> barChart = new javafx.scene.chart.BarChart<>(xAxis, yAxis); 
        barChart.setTitle("Vehicle Fuel Consumption");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName(carName);
        series1.getData().add(new XYChart.Data<>("City Fuel Consumption", car.getCityFuelConsumption()));
        series1.getData().add(new XYChart.Data<>("Highway Fuel Consumption", car.getHighwayFuelConsumption()));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Ideal");
        series2.getData().add(new XYChart.Data<>("City Fuel Consumption", 6.0));
        series2.getData().add(new XYChart.Data<>("Highway Fuel Consumption", 5.0));

        barChart.getData().addAll(series1, series2);
        barChart.setMaxWidth(400);
        barChart.setMaxHeight(320);

        Group root = new Group(barChart);
      
      return root;
    }
    
    public static Group vehicleEmissionsChart(CarModel car) {
        String carName = car.getYear() + " " + car.getMake() + " " + car.getModel();

        CategoryAxis xaxis = new CategoryAxis();  
        xaxis.setCategories(FXCollections.<String>
        observableArrayList(Arrays.asList("Carbon Emissions")));

        NumberAxis yaxis = new NumberAxis();
        yaxis.setLabel("Carbon Emissions  (g/km)");

        javafx.scene.chart.BarChart<String, Number> barChart2 = new javafx.scene.chart.BarChart<>(xaxis, yaxis); 
        barChart2.setTitle("Vehicle Carbon Emissions");

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();

        series3.setName(carName);
        series3.getData().add(new XYChart.Data<>("Carbon Emissions", car.getCarbonEmissions()));

        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("Ideal");
        series4.getData().add(new XYChart.Data<>("Carbon Emissions", 100));

        barChart2.getData().addAll(series3, series4);
        barChart2.setMaxWidth(280);
        barChart2.setMaxHeight(320);

        Group root = new Group(barChart2);

        return root;
    }
    
    public static Group vehiclesConsumptionChart(CarModel carOne, CarModel carTwo) {
        String carOneName = carOne.getYear() + " " + carOne.getMake() + " " + carOne.getModel();
        String carTwoName = carTwo.getYear() + " " + carTwo.getMake() + " " + carTwo.getModel();
         
        CategoryAxis xAxis = new CategoryAxis();  
        xAxis.setCategories(FXCollections.<String>
        observableArrayList(Arrays.asList("City Fuel Consumption", "Highway Fuel Consumption")));

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Fuel Consumption (L/100km)");

        javafx.scene.chart.BarChart<String, Number> barChart = new javafx.scene.chart.BarChart<>(xAxis, yAxis); 
        barChart.setTitle("Vehicle Fuel Consumption");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName(carOneName);
        series1.getData().add(new XYChart.Data<>("City Fuel Consumption", carOne.getCityFuelConsumption()));
        series1.getData().add(new XYChart.Data<>("Highway Fuel Consumption", carOne.getHighwayFuelConsumption()));
        
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName(carTwoName);
        series2.getData().add(new XYChart.Data<>("City Fuel Consumption", carTwo.getCityFuelConsumption()));
        series2.getData().add(new XYChart.Data<>("Highway Fuel Consumption", carTwo.getHighwayFuelConsumption()));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Ideal");
        series3.getData().add(new XYChart.Data<>("City Fuel Consumption", 6.0));
        series3.getData().add(new XYChart.Data<>("Highway Fuel Consumption", 5.0));

        barChart.getData().addAll(series1, series2, series3);
        barChart.setMaxWidth(400);
        barChart.setMaxHeight(320);

        Group root = new Group(barChart);
      
      return root;
    }
    
    public static Group vehiclesEmissionsChart(CarModel carOne, CarModel carTwo) {
        String carOneName = carOne.getYear() + " " + carOne.getMake() + " " + carOne.getModel();
        String carTwoName = carTwo.getYear() + " " + carTwo.getMake() + " " + carTwo.getModel();

        CategoryAxis xaxis = new CategoryAxis();  
        xaxis.setCategories(FXCollections.<String>
        observableArrayList(Arrays.asList("Carbon Emissions")));

        NumberAxis yaxis = new NumberAxis();
        yaxis.setLabel("Carbon Emissions  (g/km)");

        javafx.scene.chart.BarChart<String, Number> barChart2 = new javafx.scene.chart.BarChart<>(xaxis, yaxis); 
        barChart2.setTitle("Vehicle Carbon Emissions");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName(carOneName);
        series1.getData().add(new XYChart.Data<>("Carbon Emissions", carOne.getCarbonEmissions()));
        
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName(carTwoName);
        series2.getData().add(new XYChart.Data<>("Carbon Emissions", carTwo.getCarbonEmissions()));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Ideal");
        series3.getData().add(new XYChart.Data<>("Carbon Emissions", 100));

        barChart2.getData().addAll(series1, series2, series3);
        barChart2.setMaxWidth(280);
        barChart2.setMaxHeight(320);

        Group root = new Group(barChart2);

        return root;
    }
    
}
