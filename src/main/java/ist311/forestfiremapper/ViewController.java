/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist311.forestfiremapper;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author dging
 */
public abstract class ViewController {
    
    public SessionInfo sessionInfo;
    
    public void setFXMLView(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        
        ViewController viewController = loader.getController();
        viewController.setSessionInfo(sessionInfo);
        viewController.finishInitialization();
        
        sessionInfo.getStage().setScene(new Scene(root));
        sessionInfo.getStage().show();
    }
    
    // Cannot call methods that use non-FXML variables in Initialize method, as
    // non-FXML variables are not instantiated in Initialize. This method will be run
    // automatically, if implemented, by View Controller classes
    public abstract void finishInitialization();
    
    public void setSessionInfo(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }
    
}
