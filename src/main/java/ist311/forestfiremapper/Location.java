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
public class Location {
    private String location;
    public double latitude;
    public double longitude;
    
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public Location() {
        
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }
    
    public double distanceTo(double latitude, double longitude) {
        Distance distance = new Distance(this, new Location(latitude, longitude));
        return distance.getDistance();
    }
}
