package com.solvd.car.utils.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarPOJO {
    @JsonProperty("car_model")
    private String carModel;
    private String engine;
    private String color;
    private String number;
    @JsonProperty("max_speed")
    private int maxSpeed;
    private int year;
    @JsonProperty("wheel_radius")
    private int wheelRadius;
    private String salon;
    @JsonProperty("there_back_view_camera")
    private boolean isThereBackViewCamera;
    @JsonProperty("passenger_seats_count")
    private int passengerSeatsCount;
    @JsonProperty("passenger")
    private boolean isPassenger;
    @JsonProperty("there_back_windows")
    private boolean isThereBackWindows;
    @JsonProperty("clearance_length")
    private int clearanceLength;
    @JsonProperty("there_top_trunk")
    private boolean isThereTopTrunk;
    @JsonProperty("lifting_capacity")
    private int liftingCapacity;
    @JsonProperty("battery_power_reserve")
    private int batteryPowerReserve;

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWheelRadius() {
        return wheelRadius;
    }

    public void setWheelRadius(int wheelRadius) {
        this.wheelRadius = wheelRadius;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public boolean isThereBackViewCamera() {
        return isThereBackViewCamera;
    }

    public void setThereBackViewCamera(boolean thereBackViewCamera) {
        isThereBackViewCamera = thereBackViewCamera;
    }

    public int getPassengerSeatsCount() {
        return passengerSeatsCount;
    }

    public void setPassengerSeatsCount(int passengerSeatsCount) {
        this.passengerSeatsCount = passengerSeatsCount;
    }

    public boolean isPassenger() {
        return isPassenger;
    }

    public void setPassenger(boolean passenger) {
        isPassenger = passenger;
    }

    public boolean isThereBackWindows() {
        return isThereBackWindows;
    }

    public void setThereBackWindows(boolean thereBackWindows) {
        isThereBackWindows = thereBackWindows;
    }

    public int getClearanceLength() {
        return clearanceLength;
    }

    public void setClearanceLength(int clearanceLength) {
        this.clearanceLength = clearanceLength;
    }

    public boolean isThereTopTrunk() {
        return isThereTopTrunk;
    }

    public void setThereTopTrunk(boolean thereTopTrunk) {
        isThereTopTrunk = thereTopTrunk;
    }

    public int getLiftingCapacity() {
        return liftingCapacity;
    }

    public void setLiftingCapacity(int liftingCapacity) {
        this.liftingCapacity = liftingCapacity;
    }

    public int getBatteryPowerReserve() {
        return batteryPowerReserve;
    }

    public void setBatteryPowerReserve(int batteryPowerReserve) {
        this.batteryPowerReserve = batteryPowerReserve;
    }

    @Override
    public String toString() {
        return "CarPOJO{" +
                "carModel='" + carModel + '\'' +
                ", engine='" + engine + '\'' +
                ", color='" + color + '\'' +
                ", number='" + number + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", year=" + year +
                ", wheelRadius=" + wheelRadius +
                ", salon='" + salon + '\'' +
                ", isThereBackViewCamera='" + isThereBackViewCamera + '\'' +
                ", passengerSeatsCount=" + passengerSeatsCount +
                ", passenger=" + isPassenger +
                ", isThereBackWindows=" + isThereBackWindows +
                ", clearanceLength=" + clearanceLength +
                ", isThereTopTrunk=" + isThereTopTrunk +
                ", liftingCapacity=" + liftingCapacity +
                ", batteryPowerReserve=" + batteryPowerReserve +
                '}';
    }
}
