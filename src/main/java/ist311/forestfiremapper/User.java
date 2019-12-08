/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist311.forestfiremapper;

/**
 *
 * @author simonstroh
 */
public class User {
    private String username;
    private String password;
    private String name;
    private Location location;
    private boolean admin;
    
    public User(String username, String password, String name, Location location) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.location = location;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }
    
    public boolean isAdmin() {
        return admin;
    }
}
