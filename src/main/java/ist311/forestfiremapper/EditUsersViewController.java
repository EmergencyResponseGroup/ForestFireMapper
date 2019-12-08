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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author dging
 */
public class EditUsersViewController extends ViewController{
    
    @FXML
    TableView<User> userTableView;
    @FXML
    TableColumn usernameTableColumn, nameTableColumn;
    @FXML
    Button saveChangesBtn, menuBtn;
    @FXML
    CheckBox isAdminCheckBox;
    @FXML
    TextField nameTextField, usernameTextField, passwordTextField, locationTextField;
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        
        saveChangesBtn.setOnAction((ActionEvent j) -> {
            
            User selectedUser = (User) sessionInfo.getUserHashMap().get(usernameTextField.getText());
            
            selectedUser.setName(nameTextField.getText());
            selectedUser.setUsername(usernameTextField.getText());
            selectedUser.setPassword(passwordTextField.getText());
            selectedUser.setLocation(new Location(locationTextField.getText()));
            selectedUser.setAdmin(isAdminCheckBox.isSelected());
            
            sessionInfo.getUserHashMap().replace(usernameTextField.getText(), selectedUser);
            
        });
        
        menuBtn.setOnAction((ActionEvent e) -> {
            try {
                setFXMLView("/fxml/NavigationView.fxml");
            } catch (IOException ex) {
                Logger.getLogger(EditUsersViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }    

    private void showSelectedUserInfo() {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        nameTextField.setText(selectedUser.getName());
        usernameTextField.setText(selectedUser.getUsername());
        passwordTextField.setText(selectedUser.getPassword());
        locationTextField.setText(selectedUser.getLocation().getLocation());
        isAdminCheckBox.setSelected(selectedUser.isAdmin());
    }
    
    @Override
    public void finishInitialization() {
        usernameTableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        userTableView.getColumns().add(usernameTableColumn);
        userTableView.getColumns().add(nameTableColumn);
        
        if (sessionInfo.getCurrentUser().isAdmin()) {
            // Shows all user information
            sessionInfo.getUserHashMap().forEach((username, user) -> userTableView.getItems().add((User) user));
        } else {
            // Shows only user info
            userTableView.getItems().add(sessionInfo.getCurrentUser());
        }
        
        userTableView.setOnMouseClicked(e -> {
            showSelectedUserInfo();
        });
        
    }
    
}
