/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist311.forestfiremapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewcontrollers.LoginController;

/**
 *
 * @author dging
 */
public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        SessionInfo sessionInfo = new SessionInfo(stage);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        
        loginController.setSessionInfo(sessionInfo);
        
        sessionInfo.getStage().setScene(new Scene(root));
        
        sessionInfo.getStage().show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
