package edu.vanier.carbonemissionsapp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SplitPane;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cstuser
 */
public class FXMLSidebarController implements Initializable {
    
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane changingAnchorPane;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnCarbonFootprint;
    @FXML
    private Button btnVehicleEmissions;
    @FXML
    private Button btnFlightEmissions;
    @FXML
    private Button btnHomeEmissions;
    @FXML
    private Button btnReduceEmissions;
//    @FXML
//    private Button btnMyFootprint;
    @FXML
    private Button btnMenu;
    
    private double pos = 0.2;
    
    @FXML
    private void handleSwitchScene(ActionEvent event) throws IOException {
        if (event.getSource() == btnHome) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLHomepage.fxml"));
            splitPane.getItems().set(1, loader.load());
            RestrictMenuSize();
            resizeWindow();
        }
        else if (event.getSource() == btnCarbonFootprint) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCarbonFootprintCalculator.fxml"));
            splitPane.getItems().set(1, loader.load());
            RestrictMenuSize();
            resizeWindow();
        }
        else if (event.getSource() == btnVehicleEmissions) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLVehicleEmissions.fxml"));
            splitPane.getItems().set(1, loader.load());
            RestrictMenuSize();
            resizeWindow();
        }
        else if (event.getSource() == btnFlightEmissions) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFlightEmissions.fxml"));
            splitPane.getItems().set(1, loader.load());
            RestrictMenuSize();
            resizeWindow();
        }
        else if (event.getSource() == btnHomeEmissions) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLHomeEmissions.fxml"));
            splitPane.getItems().set(1, loader.load());
            RestrictMenuSize();
            resizeWindow();
        }
        else if (event.getSource() == btnReduceEmissions) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLReduceEmissions.fxml"));
            splitPane.getItems().set(1, loader.load());
            RestrictMenuSize();
            resizeWindow();
        }
//        else if (event.getSource() == btnMyFootprint) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMyFootprint.fxml"));
//            splitPane.getItems().set(1, loader.load());
//            RestrictMenuSize();
//            resizeWindow();
//        }
    }
    
    @FXML
    private void resizeWindow() {
        Stage stage = (Stage)splitPane.getScene().getWindow();
        Divider divider = splitPane.getDividers().get(0);
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override 
            public void changed( ObservableValue<? extends Number> observable, Number oldvalue, Number newvalue) {
                double currentPos = divider.getPosition();
                if (currentPos > 0.1) {
                    largeMenu();
                }
                else if (currentPos < 0.1) {
                    smallMenu();
                }
                divider.setPosition(pos);
            }
        });
    }
    
    @FXML
    private void smallMenu() {
        pos = 35/splitPane.getWidth();
        btnMenu.setAlignment(Pos.BASELINE_LEFT);
        btnMenu.setContentDisplay(ContentDisplay.CENTER);

        btnHome.setText("");
        btnCarbonFootprint.setText("");
        btnVehicleEmissions.setText("");
        btnFlightEmissions.setText("");
        btnHomeEmissions.setText("");
        btnReduceEmissions.setText("");
        //btnMyFootprint.setText("");
    }
    
    @FXML
    private void largeMenu() {
        pos = 200/splitPane.getWidth();
        btnMenu.setAlignment(Pos.BASELINE_RIGHT);
        btnMenu.setContentDisplay(ContentDisplay.RIGHT);

        btnHome.setText("    Home");
        btnCarbonFootprint.setText("    Carbon Footprint Calculator");
        btnVehicleEmissions.setText("     Vehicle emissions");
        btnFlightEmissions.setText("     Flight emissions");
        btnHomeEmissions.setText("    Home heating emissions");
        btnReduceEmissions.setText("    How to reduce emissions");
        //btnMyFootprint.setText("     My Carbon Emissions");
    }
    
    @FXML
    private void handleMenuButton(ActionEvent event) {
        if (event.getSource() == btnMenu) {
            if (pos == 200/splitPane.getWidth()) {
                smallMenu();
            }
            else if (pos != 200/splitPane.getWidth()) {
                largeMenu();
            }
            splitPane.setDividerPosition(0, pos);
        }
    }
    
    @FXML
    private void RestrictMenuSize() {
         Divider divider = splitPane.getDividers().get(0);
         divider.positionProperty().addListener(new ChangeListener<Number>() {             
            @Override 
            public void changed( ObservableValue<? extends Number> observable, Number oldvalue, Number newvalue) {
                divider.setPosition(pos);
            }
         }); 
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            splitPane.getItems().set(1, FXMLLoader.load(getClass().getResource("FXMLHomepage.fxml")));
            splitPane.setDividerPosition(0, pos);
            RestrictMenuSize();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLSidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }    
}
