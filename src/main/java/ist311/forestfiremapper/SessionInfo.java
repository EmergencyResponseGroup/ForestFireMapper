/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist311.forestfiremapper;

import javafx.stage.Stage;

/**
 *
 * @author dging
 */
public class SessionInfo {
    
    private Stage stage;
    private UserHashMap<String, User> userHashMap;
    private User currentUser;
    
    public SessionInfo(Stage stage) {
        
        this.stage = stage;
        initializeUserHashMap();
        
    }
    
    private void initializeUserHashMap() {
        userHashMap = new UserHashMap<>();
        // To add a user, put a new user in UserHashMap
        // Key: Username (usernameField.getText())
        // Value: new User(...)
        
        // Creates admin user
        User admin = new User("Admin", "Admin", "Admin", new Location("Admin"));
        admin.setAdmin(true);
        getUserHashMap().put(admin.getUsername(), admin);
        
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public Stage getStage() {
        return stage;
    }

    public UserHashMap<String, User> getUserHashMap() {
        return userHashMap;
    }
    
}
