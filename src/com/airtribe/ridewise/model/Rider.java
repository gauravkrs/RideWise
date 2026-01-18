package com.airtribe.ridewise.model;

public class Rider {

    private int riderId;
    private String riderName;
    private double location;

    public Rider(int riderId, String riderName, double location) {
        this.riderId = riderId;
        this.riderName = riderName;
        this.location = location;
    }

    public int getRiderId() {
        return riderId;
    }

    public String getRiderName() {
        return riderName;
    }

    public double getLocation() {
        return location;
    }

    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public void setLocation(double location) {
        this.location = location;
    }
}
