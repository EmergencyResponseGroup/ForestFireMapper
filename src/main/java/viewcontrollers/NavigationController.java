    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist311.forestfiremapper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author dging
 */
public class NavigationController extends ViewController{
    
    @FXML
    Button editUsersBtn, logoutBtn;
    
    public void initialize() {
        
        editUsersBtn.setOnAction((ActionEvent e) -> {
            try {
                setFXMLView("/fxml/EditUsersView.fxml");
                
            } catch (IOException ex) {
                Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        logoutBtn.setOnAction((ActionEvent e) -> {
            try {
                setFXMLView("/fxml/LoginView.fxml");
                sessionInfo.setCurrentUser(null);
            } catch (IOException ex) {
                Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
    }    

    @Override
    public void finishInitialization() {}
    
}
