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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dging
 */
public class EditUsersViewController extends ViewController {

    @FXML
    TableView<User> userTableView;
    @FXML
    TableColumn usernameTableColumn, nameTableColumn;
    @FXML
    Button saveChangesBtn, menuBtn;
    @FXML
    CheckBox isAdminCheckBox;
    @FXML
    TextField nameField, usernameField, passwordField, latitudeField, longitudeField;

    /**
     * Initializes the controller class.
     */
    public void initialize() {

        saveChangesBtn.setOnAction((ActionEvent j) -> {

            try {
                User selectedUser = (User) sessionInfo.getUserHashMap().get(usernameField.getText());

                selectedUser.setName(nameField.getText());
                selectedUser.setUsername(usernameField.getText());
                selectedUser.setPassword(passwordField.getText());
                selectedUser.setLocation(new Location(Double.parseDouble(latitudeField.getText()), Double.parseDouble(longitudeField.getText())));
                selectedUser.setAdmin(isAdminCheckBox.isSelected());

                sessionInfo.getUserHashMap().replace(usernameField.getText(), selectedUser);
            } catch (NumberFormatException numberFormatException) {

                createErrorPopup("Error - make sure all input is correct.");
                
            }

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

        //Sets buttons visible
        saveChangesBtn.setVisible(true);
        isAdminCheckBox.setVisible(true);
        nameField.setVisible(true);
        usernameField.setVisible(true);
        passwordField.setVisible(true);
        longitudeField.setVisible(true);
        latitudeField.setVisible(true);

        User selectedUser = userTableView.getSelectionModel().getSelectedItem();

        if (selectedUser.isAdmin()) {
            isAdminCheckBox.setVisible(true);
        } else {
            isAdminCheckBox.setVisible(false);
        }

        nameField.setText(selectedUser.getName());
        usernameField.setText(selectedUser.getUsername());
        passwordField.setText(selectedUser.getPassword());
        latitudeField.setText(String.valueOf(selectedUser.getLocation().getLatitude()));
        longitudeField.setText(String.valueOf(selectedUser.getLocation().getLongitude()));
        isAdminCheckBox.setSelected(selectedUser.isAdmin());
    }

    @Override
    public void finishInitialization() {
        usernameTableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

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
