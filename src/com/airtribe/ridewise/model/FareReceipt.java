package com.airtribe.ridewise.model;

import java.time.LocalDateTime;

public class FareReceipt {

    private int receiptId;
    private int rideId;
    private double baseFare;
    private double distanceFare;
    private double timeFare;
    private double totalAmount;
    private LocalDateTime generatedAt;

    public FareReceipt(int receiptId, int rideId, double baseFare, double distanceFare, double timeFare) {
        this.receiptId = receiptId;
        this.rideId = rideId;
        this.baseFare = baseFare;
        this.distanceFare = distanceFare;
        this.timeFare = timeFare;
        this.totalAmount = baseFare + distanceFare + timeFare;
        this.generatedAt = LocalDateTime.now();
    }

    public int getReceiptId() {
        return receiptId;
    }

    public int getRideId() {
        return rideId;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public double getDistanceFare() {
        return distanceFare;
    }

    public double getTimeFare() {
        return timeFare;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
}
