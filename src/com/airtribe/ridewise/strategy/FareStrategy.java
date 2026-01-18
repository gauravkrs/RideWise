package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.FareReceipt;
import com.airtribe.ridewise.model.Ride;

public interface FareStrategy {
    FareReceipt calculateFare(Ride ride);
}

