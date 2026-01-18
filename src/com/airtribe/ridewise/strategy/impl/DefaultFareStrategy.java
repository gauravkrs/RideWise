package com.airtribe.ridewise.strategy.impl;

import com.airtribe.ridewise.model.FareReceipt;
import com.airtribe.ridewise.model.Ride;
import com.airtribe.ridewise.strategy.FareStrategy;
import com.airtribe.ridewise.util.IdGenerator;

public class DefaultFareStrategy implements FareStrategy {

    private final double baseFare;
    private final double ratePerKm;
    private final double ratePerMinute;

    public DefaultFareStrategy(double baseFare, double ratePerKm, double ratePerMinute) {
        this.baseFare = baseFare;
        this.ratePerKm = ratePerKm;
        this.ratePerMinute = ratePerMinute;
    }

    @Override
    public FareReceipt calculateFare(Ride ride) {
        double distanceFare = ride.getDistance() * ratePerKm;
        double timeFare = ride.getEstimatedTime() * ratePerMinute;

        return new FareReceipt(
                IdGenerator.generateId(),
                ride.getRideId(),
                baseFare,
                distanceFare,
                timeFare
        );
    }
}
