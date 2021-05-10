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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

/**
 *
 * @author Kevin Patel
 */
public class FXMLHomepageController implements Initializable {
    @FXML private Label lblPurpose;
    @FXML private Button btnLearnMore;
    
    
    @FXML
    private void handleLearnButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLReduceEmissions.fxml"));
            SplitPane pane = (SplitPane)((Node) event.getSource()).getScene().getRoot();
            pane.getItems().set(1, loader.load());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String Learn = "The purpose of this app is to raise awareness of the effects of carbon emissions and the \n"
                + "amount of carbon they are emitting. As well as informing the users, we provide numerous \n"
                + "features that allow the users to calculate their home emissions, vehical emissions and the \n"
                + "emissions from two destinations by plane. Ultimately, this app seeks to motivate people to reduce\n"
                + "their emissions and motivate them to help reduce climate change. ";
        
        lblPurpose.setText(Learn);
    }    
    
}
