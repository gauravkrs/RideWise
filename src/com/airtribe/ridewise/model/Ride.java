package com.airtribe.ridewise.model;

import com.airtribe.ridewise.enums.RideStatus;

public class Ride {

    private int rideId;
    private Rider rider;
    private Driver driver;
    private double distance;
    private double estimatedTime;
    private RideStatus status;

    public Ride(int rideId, Rider rider, double distance, double estimatedTime) {
        this.rideId = rideId;
        this.rider = rider;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.status = RideStatus.REQUESTED;
    }

    public int getRideId() {
        return rideId;
    }

    public Rider getRider() {
        return rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public double getDistance() {
        return distance;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }
}
