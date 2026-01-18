package com.airtribe.ridewise.service;

import com.airtribe.ridewise.model.Driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverService {

    private Map<Integer, Driver> drivers;

    public DriverService() {
        this.drivers = new HashMap<>();
    }

    public void registerDriver(Driver driver) {
        drivers.put(driver.getDriverId(), driver);
    }

    public void updateAvailability(int driverId, boolean isAvailable) {
        Driver driver = drivers.get(driverId);
        if (driver != null) {
            driver.setAvailable(isAvailable);
        }
    }

    public List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : drivers.values()) {
            if (driver.getAvailable()) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    public Driver getDriverById(int driverId) {
        return drivers.get(driverId);
    }

    public Map<Integer, Driver> getAllDrivers() {
        return drivers;
    }
}
