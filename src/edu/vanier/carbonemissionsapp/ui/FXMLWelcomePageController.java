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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Mr. Popo
 */
public class FXMLWelcomePageController implements Initializable {
    
    @FXML
    private Label lblWelcome;
    
    @FXML
    private Button btnSignUp;
    
    @FXML
    private Button btnSignIn;
    
    @FXML
    private Button btnGuest;

    @FXML
    private void handleSignUpButton(ActionEvent event) throws IOException {
        Parent signUpParent = FXMLLoader.load(getClass().getResource("FXMLSignUp.fxml"));
        Scene signUpScene = new Scene(signUpParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.show();
    }
    @FXML
    private void handleSignInButton(ActionEvent event) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("FXMLSignIn.fxml"));
        Scene signInScene = new Scene(signInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }
    @FXML
    private void handleGuestButton(ActionEvent event) throws IOException {
        Parent guestParent = FXMLLoader.load(getClass().getResource("FXMLSidebar.fxml"));
        Scene guestScene = new Scene(guestParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(guestScene);
        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
