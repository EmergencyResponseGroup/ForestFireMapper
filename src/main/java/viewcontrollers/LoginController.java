/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontrollers;

import ist311.forestfiremapper.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author dging
 */
public class LoginController extends ViewController{
    
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML 
    private Button loginBtn;
    @FXML
    private Button createAccountBtn;
    
    public void initialize() {
        
        initializeLoginBtn();
        initializeCreateAccountBtn();
 
    }
    
    public void initializeLoginBtn() {
        
        loginBtn.setOnAction((ActionEvent e) -> {
            try {
                authenticate(usernameField.getText(), passwordField.getText());
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
    public void initializeCreateAccountBtn() {
        
        createAccountBtn.setOnAction((ActionEvent e) -> {
            try {
                setFXMLView("/fxml/CreateAccountView.fxml");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
    }
    
    private void authenticate(String username, String password) throws IOException {
        
        if (sessionInfo.getUserHashMap().containsKey(username)) {
            User user = (User) sessionInfo.getUserHashMap().get(username);
            
            if (user.getPassword().equals(password)) {
                sessionInfo.setCurrentUser(user);
                setFXMLView("/fxml/NavigationView.fxml");
            }
           
        } else {
            System.out.println("Access Denied");
        }
        
    }

    @Override
    public void finishInitialization() {
    }
    
    
}
