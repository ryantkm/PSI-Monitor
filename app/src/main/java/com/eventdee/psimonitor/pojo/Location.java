package com.eventdee.psimonitor.pojo;

public class Location {

    private String locationName;
    private double latitude;
    private double longitude;
    private int image;

    public Location(String locationName, double latitude, double longitude, int image) {
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
