package com.airtribe.ridewise.service;

import com.airtribe.ridewise.enums.RideStatus;
import com.airtribe.ridewise.exception.NoAvailableDriverException;
import com.airtribe.ridewise.exception.RideNotFoundException;
import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.FareReceipt;
import com.airtribe.ridewise.model.Ride;
import com.airtribe.ridewise.model.Rider;
import com.airtribe.ridewise.strategy.FareStrategy;
import com.airtribe.ridewise.strategy.RideMatchingStrategy;
import com.airtribe.ridewise.util.IdGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideService {

    private final RideMatchingStrategy matchingStrategy;
    private final FareStrategy fareStrategy;
    private final DriverService driverService;
    private Map<Integer, Ride> rides;

    public RideService(RideMatchingStrategy matchingStrategy, 
                      FareStrategy fareStrategy,
                      DriverService driverService) {
        this.matchingStrategy = matchingStrategy;
        this.fareStrategy = fareStrategy;
        this.driverService = driverService;
        this.rides = new HashMap<>();
    }

    public Ride requestRide(Rider rider, double distance, double estimatedTime) throws NoAvailableDriverException {
        List<Driver> availableDrivers = driverService.getAvailableDrivers();
        
        if (availableDrivers.isEmpty()) {
            throw new NoAvailableDriverException();
        }

        Driver assignedDriver = matchingStrategy.findDriver(rider, availableDrivers);
        
        if (assignedDriver == null) {
            throw new NoAvailableDriverException();
        }

        int rideId = IdGenerator.generateId();
        Ride ride = new Ride(rideId, rider, distance, estimatedTime);
        ride.setDriver(assignedDriver);
        ride.setStatus(RideStatus.ASSIGNED);

        assignedDriver.setAvailable(false);
        rides.put(rideId, ride);

        return ride;
    }

    public FareReceipt completeRide(int rideId) throws RideNotFoundException {
        Ride ride = rides.get(rideId);
        
        if (ride == null) {
            throw new RideNotFoundException(rideId);
        }

        ride.setStatus(RideStatus.COMPLETED);
        
        Driver driver = ride.getDriver();
        driver.setAvailable(true);
        driver.setRidesCompleted(driver.getRidesCompleted() + 1);

        FareReceipt receipt = fareStrategy.calculateFare(ride);
        return receipt;
    }

    public void cancelRide(int rideId) throws RideNotFoundException {
        Ride ride = rides.get(rideId);
        
        if (ride == null) {
            throw new RideNotFoundException(rideId);
        }

        ride.setStatus(RideStatus.CANCELLED);
        
        if (ride.getDriver() != null) {
            ride.getDriver().setAvailable(true);
        }
    }

    public Ride getRideById(int rideId) {
        return rides.get(rideId);
    }

    public Map<Integer, Ride> getAllRides() {
        return rides;
    }
}
