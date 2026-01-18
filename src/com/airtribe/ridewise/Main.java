package com.airtribe.ridewise;

import com.airtribe.ridewise.exception.NoAvailableDriverException;
import com.airtribe.ridewise.exception.RideNotFoundException;
import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.FareReceipt;
import com.airtribe.ridewise.model.Ride;
import com.airtribe.ridewise.model.Rider;
import com.airtribe.ridewise.service.DriverService;
import com.airtribe.ridewise.service.RideService;
import com.airtribe.ridewise.service.RiderService;
import com.airtribe.ridewise.strategy.impl.DefaultFareStrategy;
import com.airtribe.ridewise.strategy.impl.NearestDriverStrategy;

import java.util.Scanner;

public class Main {

    private static RiderService riderService = new RiderService();
    private static DriverService driverService = new DriverService();
    private static RideService rideService;
    private static Scanner scanner = new Scanner(System.in);
    private static int riderIdCounter = 1;
    private static int driverIdCounter = 1;

    public static void main(String[] args) {
        rideService = new RideService(
                new NearestDriverStrategy(),
                new DefaultFareStrategy(50.0, 12.0, 2.0),
                driverService
        );

        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addRider();
                    break;
                case 2:
                    addDriver();
                    break;
                case 3:
                    viewAvailableDrivers();
                    break;
                case 4:
                    requestRide();
                    break;
                case 5:
                    completeRide();
                    break;
                case 6:
                    viewRides();
                    break;
                case 7:
                    System.out.println("Exiting RideWise. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private static void displayMenu() {
        System.out.println("========== RideWise Menu ==========");
        System.out.println("1. Add Rider");
        System.out.println("2. Add Driver");
        System.out.println("3. View Available Drivers");
        System.out.println("4. Request Ride");
        System.out.println("5. Complete Ride");
        System.out.println("6. View Rides");
        System.out.println("7. Exit");
        System.out.println("===================================");
    }

    private static void addRider() {
        System.out.println("\n--- Add Rider ---");
        String name = getStringInput("Enter rider name: ");
        double location = getDoubleInput("Enter rider location (km): ");

        Rider rider = new Rider(riderIdCounter++, name, location);
        riderService.registerRider(rider);

        System.out.println("Rider added successfully! Rider ID: " + rider.getRiderId());
    }

    private static void addDriver() {
        System.out.println("\n--- Add Driver ---");
        String name = getStringInput("Enter driver name: ");
        double location = getDoubleInput("Enter driver location (km): ");

        Driver driver = new Driver(driverIdCounter++, name, location, true);
        driverService.registerDriver(driver);

        System.out.println("Driver added successfully! Driver ID: " + driver.getDriverId());
    }

    private static void viewAvailableDrivers() {
        System.out.println("\n--- Available Drivers ---");
        var drivers = driverService.getAvailableDrivers();

        if (drivers.isEmpty()) {
            System.out.println("No available drivers at the moment.");
            return;
        }

        for (Driver driver : drivers) {
            System.out.println("ID: " + driver.getDriverId() +
                    " | Name: " + driver.getDriverName() +
                    " | Location: " + driver.getCurrentLocation() + " km" +
                    " | Rides Completed: " + driver.getRidesCompleted());
        }
    }

    private static void requestRide() {
        System.out.println("\n--- Request Ride ---");
        int riderId = getIntInput("Enter rider ID: ");
        Rider rider = riderService.getRiderById(riderId);

        if (rider == null) {
            System.out.println("Rider not found!");
            return;
        }

        double distance = getDoubleInput("Enter ride distance (km): ");
        double estimatedTime = getDoubleInput("Enter estimated time (minutes): ");

        try {
            Ride ride = rideService.requestRide(rider, distance, estimatedTime);
            System.out.println("Ride requested successfully!");
            System.out.println("Ride ID: " + ride.getRideId());
            System.out.println("Assigned Driver: " + ride.getDriver().getDriverName());
            System.out.println("Status: " + ride.getStatus());
        } catch (NoAvailableDriverException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void completeRide() {
        System.out.println("\n--- Complete Ride ---");
        int rideId = getIntInput("Enter ride ID: ");

        try {
            FareReceipt receipt = rideService.completeRide(rideId);
            System.out.println("Ride completed successfully!");
            System.out.println("\n--- Fare Receipt ---");
            System.out.println("Receipt ID: " + receipt.getReceiptId());
            System.out.println("Ride ID: " + receipt.getRideId());
            System.out.println("Base Fare: Rs. " + receipt.getBaseFare());
            System.out.println("Distance Fare: Rs. " + receipt.getDistanceFare());
            System.out.println("Time Fare: Rs. " + receipt.getTimeFare());
            System.out.println("Total Amount: Rs. " + receipt.getTotalAmount());
            System.out.println("Generated At: " + receipt.getGeneratedAt());
        } catch (RideNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewRides() {
        System.out.println("\n--- All Rides ---");
        var rides = rideService.getAllRides();

        if (rides.isEmpty()) {
            System.out.println("No rides found.");
            return;
        }

        for (Ride ride : rides.values()) {
            System.out.println("Ride ID: " + ride.getRideId() +
                    " | Rider: " + ride.getRider().getRiderName() +
                    " | Driver: " + (ride.getDriver() != null ? ride.getDriver().getDriverName() : "N/A") +
                    " | Distance: " + ride.getDistance() + " km" +
                    " | Status: " + ride.getStatus());
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
