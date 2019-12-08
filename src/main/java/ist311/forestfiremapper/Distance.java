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
public class Distance {
    private double fromLatitude;
    private double fromLongitude;
    private double toLatitude;
    private double toLongitude;
    private double distanceMiles;
    private final double STATUTE_MILES_PER_NAUTICAL_MILE;
    public Distance(Location from, Location to) {
        STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
        fromLatitude = Math.toRadians(from.getLatitude());
        fromLongitude = Math.toRadians(from.getLongitude());
        toLatitude = Math.toRadians(to.getLatitude());
        toLongitude = Math.toRadians(to.getLongitude());
        double angle = Math.acos(Math.sin(fromLatitude) * Math.sin(toLatitude) + Math.cos(fromLatitude) * Math.cos(toLatitude) * Math.cos(fromLongitude - toLongitude));
        double miles = 60 * Math.toDegrees(angle) * STATUTE_MILES_PER_NAUTICAL_MILE;
        distanceMiles = miles;
    }
    public double getDistance() {
        return distanceMiles;
    }
}
