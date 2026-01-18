package com.airtribe.ridewise.service;

import com.airtribe.ridewise.model.Rider;

import java.util.HashMap;
import java.util.Map;

public class RiderService {

    private Map<Integer, Rider> riders;

    public RiderService() {
        this.riders = new HashMap<>();
    }

    public void registerRider(Rider rider) {
        riders.put(rider.getRiderId(), rider);
    }

    public Rider getRiderById(int riderId) {
        return riders.get(riderId);
    }

    public Map<Integer, Rider> getAllRiders() {
        return riders;
    }
}
