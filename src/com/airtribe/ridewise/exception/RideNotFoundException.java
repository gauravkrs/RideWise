package com.airtribe.ridewise.exception;

public class RideNotFoundException extends Exception {

    public RideNotFoundException(String message) {
        super(message);
    }

    public RideNotFoundException(int rideId) {
        super("Ride with ID " + rideId + " not found");
    }
}
