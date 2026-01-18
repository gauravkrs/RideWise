package com.airtribe.ridewise.strategy.impl;

import com.airtribe.ridewise.model.FareReceipt;
import com.airtribe.ridewise.model.Ride;
import com.airtribe.ridewise.strategy.FareStrategy;
import com.airtribe.ridewise.util.IdGenerator;

public class PeakHourFareStrategy implements FareStrategy {

    private final double baseFare;
    private final double ratePerKm;
    private final double ratePerMinute;
    private final double surgeMultiplier;

    public PeakHourFareStrategy(
            double baseFare,
            double ratePerKm,
            double ratePerMinute,
            double surgeMultiplier
    ) {
        this.baseFare = baseFare;
        this.ratePerKm = ratePerKm;
        this.ratePerMinute = ratePerMinute;
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public FareReceipt calculateFare(Ride ride) {

        double distanceFare =
                ride.getDistance() * ratePerKm * surgeMultiplier;

        double timeFare =
                ride.getEstimatedTime() * ratePerMinute * surgeMultiplier;

        return new FareReceipt(
                IdGenerator.generateId(),
                ride.getRideId(),
                baseFare * surgeMultiplier,
                distanceFare,
                timeFare
        );
    }
}
