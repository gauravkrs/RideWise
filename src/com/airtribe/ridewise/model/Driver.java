package com.airtribe.ridewise.model;

public class Driver {

    private int driverId;
    private String driverName;
    private double currentLocation;
    private Boolean isAvailable;
    private int ridesCompleted;

    public Driver(int driverId, String driverName, double currentLocation, Boolean isAvailable) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.currentLocation = currentLocation;
        this.isAvailable = isAvailable;
        this.ridesCompleted = 0;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public double getCurrentLocation() {
        return currentLocation;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public int getRidesCompleted() {
        return ridesCompleted;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setCurrentLocation(double currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setRidesCompleted(int ridesCompleted) {
        this.ridesCompleted = ridesCompleted;
    }
}
