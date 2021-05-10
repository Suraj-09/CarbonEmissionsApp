package edu.vanier.carbonemissionsapp.ui;

import edu.vanier.carbonemissionsapp.controllers.CarsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mr. Popo
 */
public class CarbonEmissionsApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLWelcomePage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        CarsController carController = new CarsController();
        carController.emptyResults("compareResults");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
