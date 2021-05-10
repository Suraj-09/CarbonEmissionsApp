package edu.vanier.carbonemissionsapp.ui;

import edu.vanier.carbonemissionsapp.controllers.UsersController;
import edu.vanier.carbonemissionsapp.models.userModel;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author mjj
 */
public class FXMLSignInController implements Initializable {
    UsersController user = new UsersController();
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField pwfPassword;
    
    @FXML
    private void handleBtnSignIn(ActionEvent event) throws IOException {
        if(user.login(txtName, pwfPassword)){    
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Parent parentHomePage = FXMLLoader.load(getClass().getResource("FXMLSidebar.fxml"));

            Scene sceneHomePage = new Scene(parentHomePage);
            window.setScene(sceneHomePage);
        }
        else{
            txtName.setText("Invalid");
            pwfPassword.setText("Invalid");
        }
            
    }
    
    @FXML
    private void handleBtnCancel(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent parentWelcomePage = FXMLLoader.load(getClass().getResource("FXMLWelcomePage.fxml"));
        
        Scene sceneWelcomePage = new Scene(parentWelcomePage);
        window.setScene(sceneWelcomePage);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
