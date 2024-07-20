package com.ride_share.dashboards;

public class Ride {
    private String passengerName;
    private String date;
    private String time;
    private String driverRating;
    private String vehicleType;

    public Ride(String passengerName, String date, String time, String driverRating, String vehicleType) {
        this.passengerName = passengerName;
        this.date = date;
        this.time = time;
        this.driverRating = driverRating;
        this.vehicleType = vehicleType;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDriverRating() {
        return driverRating;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}