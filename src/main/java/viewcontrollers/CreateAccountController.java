/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontrollers;

import ist311.forestfiremapper.Location;
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
 * @author simonstroh
 */
public class CreateAccountController extends ViewController {

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField, nameField, latitudeField, longitudeField;
    @FXML
    private Button createAccountBtn, goBackBtn;

    public void initialize() {
        initializeCreateAccountBtn();
        initializeGoBackBtn();
    }

    private void initializeCreateAccountBtn() {
        createAccountBtn.setOnAction((ActionEvent e) -> {
            try {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String name = nameField.getText();
                Double latitude = Double.parseDouble(latitudeField.getText());
                Double longitude = Double.parseDouble(longitudeField.getText());
                if (!sessionInfo.getUserHashMap().containsKey(username)) {
                    sessionInfo.getUserHashMap().put(username, new User(username, password, name, new Location(latitude, longitude)));
                    try {
                        setFXMLView("/fxml/LoginView.fxml");
                    } catch (IOException ex) {
                        Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            } catch (NumberFormatException numberFormatException) {
                createErrorPopup("Error - make sure all input is correct");
            }
        });
    }

    private void initializeGoBackBtn() {
        goBackBtn.setOnAction((ActionEvent e) -> {
            try {
                setFXMLView("/fxml/LoginView.fxml");
            } catch (IOException ex) {
                Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void finishInitialization() {
    }

}
