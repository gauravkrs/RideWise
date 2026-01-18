package com.airtribe.ridewise.strategy.impl;

import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.Rider;
import com.airtribe.ridewise.strategy.RideMatchingStrategy;

import java.util.List;

public class LeastActiveDriverStrategy implements RideMatchingStrategy {

    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        Driver leastActiveDriver = null;
        int minRides = Integer.MAX_VALUE;

        for (Driver driver : drivers) {
            if (driver.getAvailable()) {
                if (driver.getRidesCompleted() < minRides) {
                    minRides = driver.getRidesCompleted();
                    leastActiveDriver = driver;
                }
            }
        }

        return leastActiveDriver;
    }
}
