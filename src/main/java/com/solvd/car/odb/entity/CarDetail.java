package com.solvd.car.odb.entity;

public class CarDetail {
    private long id;
    private int wheelRadius;
    private String salon;
    private boolean isThereBackViewCamera;
    private int passengerSeatsCount;
    private boolean isPassenger;
    private boolean isThereBackWindows;
    private int clearanceLength;
    private boolean isThereTopTrunk;
    private int liftingCapacity;
    private int batteryPowerReserve;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "CarDetail{" +
                "id=" + id +
                ", wheelRadius=" + wheelRadius +
                ", salon='" + salon + '\'' +
                ", isThereBackViewCamera=" + isThereBackViewCamera +
                ", passengerSeatsCount=" + passengerSeatsCount +
                ", isPassenger=" + isPassenger +
                ", isThereBackWindows=" + isThereBackWindows +
                ", clearanceLength=" + clearanceLength +
                ", isThereTopTrunk=" + isThereTopTrunk +
                ", liftingCapacity=" + liftingCapacity +
                ", batteryPowerReserve=" + batteryPowerReserve +
                '}';
    }
}
