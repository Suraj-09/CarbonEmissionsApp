package edu.vanier.carbonemissionsapp.ui;

import edu.vanier.carbonemissionsapp.helpers.ChartClass;
import edu.vanier.carbonemissionsapp.models.CarModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Suraj
 */
public class FXMLVehicleResultsController implements Initializable {

    
    @FXML
    private Label lblCarName;
    
    @FXML
    private Label lblCarEmissions;
    
    @FXML
    private Label lblVehicleName;
    
    @FXML
    private Label lblCarVC;
    
    @FXML
    private Label lblCarES;
    
    @FXML
    private Label lblCarCylinders;
    
    @FXML
    private Label lblCarTransmission;
    
    @FXML
    private Label lblCarFT;
    
    @FXML
    private Label lblCarEm;
    
    @FXML
    private Label lblCompCarName;
    
    @FXML
    private Label lblCompCarVC;
    
    @FXML
    private Label lblCompCarES;
    
    @FXML
    private Label lblCompCarCylinders;
    
    @FXML
    private Label lblCompCarTransmission;
    
    @FXML
    private Label lblCompCarFT;
    
    @FXML
    private Label lblCompCarEm;
    
    @FXML
    private TextField txtMileage;
    
    @FXML
    private GridPane grid;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private Boolean condition;
    
    @FXML
    public void handleButton(ActionEvent event) throws IOException {
        if (event.getSource() == btnBack) {
            SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
            FXMLLoader loader;
            if (condition) {
                loader = new FXMLLoader(getClass().getResource("FXMLVehicleEmissions.fxml"));
            }
            else
                loader = new FXMLLoader(getClass().getResource("FXMLVehicleCompareEmissions.fxml"));
            
            pane.getItems().set(1, loader.load());
        }
    }
    
    @FXML
    public void setData(CarModel selectedCar, double distance) {
        String carName = selectedCar.getYear() + " " + selectedCar.getMake() + " " 
                + selectedCar.getModel();
        
        lblCarName.setText(carName);
        lblCarName.setAlignment(Pos.CENTER);
        
        double emissions = selectedCar.getCarbonEmissions() * 0.001 * distance;
        String strDistance = String.format("Carbon emissions in %.2f  km ", distance);
        
        lblCarEmissions.setText(strDistance);
        
        lblVehicleName.setText(carName);
        lblCarVC.setText(selectedCar.getVehicleClass());
        lblCarES.setText(selectedCar.getEngineSize());
        lblCarCylinders.setText(selectedCar.getCylinders());
        lblCarTransmission.setText(selectedCar.getTransmission());
        lblCarFT.setText(selectedCar.getFuelType());
        lblCarEm.setText(String.format("%.2f kg", emissions));
        
        
        Group group = ChartClass.vehicleConsumptionChart(selectedCar);
        grid.add(group, 0, 1);
        Group group2 = ChartClass.vehicleEmissionsChart(selectedCar);
        grid.add(group2, 1, 1); 
        condition = true;
    }
    
    @FXML
    public void setData(CarModel selectedCar, CarModel compareCar, double distance) {
        String selectedCarName = selectedCar.getYear() + " " + selectedCar.getMake() 
                + " " + selectedCar.getModel();
        String compareCarName = compareCar.getYear() + " " + compareCar.getMake() 
                + " " + compareCar.getModel();
        
        lblCarName.setText("Comparing " + selectedCarName + " and " + compareCarName);
        lblCarName.setAlignment(Pos.CENTER);
        
        double selectedCarEmissions = selectedCar.getCarbonEmissions() * 0.001 * distance;
        double compareCarEmissions = compareCar.getCarbonEmissions() * 0.001 * distance;
        
        String strDistance = String.format("Carbon emissions in %.2f  km:", distance);
        
        lblCarEmissions.setText(strDistance);
        
        lblVehicleName.setText(selectedCarName);
        lblCarVC.setText(selectedCar.getVehicleClass());
        lblCarES.setText(selectedCar.getEngineSize() + " L");
        lblCarCylinders.setText(selectedCar.getCylinders());
        lblCarTransmission.setText(selectedCar.getTransmission());
        lblCarFT.setText(selectedCar.getFuelType());
        lblCarEm.setText(String.format("%.2f kg", selectedCarEmissions));
        
        lblCompCarName.setText(compareCarName);
        lblCompCarVC.setText(compareCar.getVehicleClass());
        lblCompCarES.setText(compareCar.getEngineSize() + " L");
        lblCompCarCylinders.setText(compareCar.getCylinders());
        lblCompCarTransmission.setText(compareCar.getTransmission());
        lblCompCarFT.setText(compareCar.getFuelType());
        lblCompCarEm.setText(String.format("%.2f kg", compareCarEmissions));
        
        
        Group group = ChartClass.vehiclesConsumptionChart(selectedCar, compareCar);
        grid.add(group, 0, 1);
        Group group2 = ChartClass.vehiclesEmissionsChart(selectedCar, compareCar);
        grid.add(group2, 1, 1); 
        condition = false;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
