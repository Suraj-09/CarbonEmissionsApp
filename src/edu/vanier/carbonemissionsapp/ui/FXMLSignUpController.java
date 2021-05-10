package edu.vanier.carbonemissionsapp.ui;

import edu.vanier.carbonemissionsapp.controllers.UsersController;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author mjj
 */
public class FXMLSignUpController implements Initializable {
    UsersController user = new UsersController();
    @FXML
    private Button btnSignUp;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField pwfPassword;
    @FXML
    private PasswordField pwfConfirm;
    @FXML
    private Label lblError;
    
    @FXML
    private void handleBtnSignUp(ActionEvent event) throws IOException {
        if(user.setUsers(txtName, pwfPassword, pwfConfirm)){
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Parent parentHomePage = FXMLLoader.load(getClass().getResource("FXMLSidebar.fxml"));

            Scene sceneHomePage = new Scene(parentHomePage);
            window.setScene(sceneHomePage);
        }
        else{
            lblError.setText("Passwords do not match");
        }
    }
    
    @FXML
    private void handleBtnCancel(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent parentWelcomePage = FXMLLoader.load(getClass().getResource("FXMLWelcomePage.fxml"));
        
        Scene sceneWelcome = new Scene(parentWelcomePage);
        window.setScene(sceneWelcome);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
