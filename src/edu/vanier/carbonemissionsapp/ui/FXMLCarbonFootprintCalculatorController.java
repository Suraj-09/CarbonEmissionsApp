/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import sun.util.calendar.CalendarUtils;

/**
 * FXML Controller class
 *
 * @author cstuser
 */
public class FXMLCarbonFootprintCalculatorController implements Initializable {

    @FXML
    DatePicker dtpFrom;
    @FXML
    DatePicker dtpEnd;
    @FXML
    Label lblTitle;
    @FXML
    TextField txtHeating;
    @FXML
    TextField txtVehicles;
    @FXML
    TextField txtFlights;
    @FXML
    Button btnCalculate;
    
    public Date dateFrom;
    public Date dateTo;
    
    public void dateHandler(ActionEvent event) throws IOException {
        if (event.getSource() == dtpFrom || event.getSource() == dtpEnd) {
            if (dtpFrom.getValue() != null && dtpEnd.getValue() != null) {
                btnCalculate.setDisable(false);
            }
        }
        
        if (event.getSource() == btnCalculate) {
            LocalDate localDate1 = dtpFrom.getValue();
            dateFrom = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            LocalDate localDate2 = dtpEnd.getValue();
            dateTo = Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCFVehicle.fxml"));
            
            SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
            pane.getItems().set(1, loader.load());
            
            FXMLCFVehicleController controller = loader.<FXMLCFVehicleController>getController();
            controller.setData(dateFrom, dateTo);
        }
    }
    
    public Date getDateFrom() {
        return dateFrom;
    }
    
    public Date getDateTo() {
        return dateTo;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCalculate.setDisable(true);
        
    }    
    
}
