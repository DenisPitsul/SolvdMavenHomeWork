package com.solvd.car.vehicle;

import com.solvd.car.vehicle.helper.Engine;

public class MiniBus extends Car {
    private boolean isPassenger;
    private boolean isThereBackWindows;

    public MiniBus(Engine engine, int maxSpeed, int year) {
        super(engine, maxSpeed, year);
    }

    public MiniBus(Engine engine, String color, String number, int maxSpeed, int year, boolean isPassenger, boolean isThereBackWindows) {
        super(engine, color, number, maxSpeed, year);
        this.isPassenger = isPassenger;
        this.isThereBackWindows = isThereBackWindows;
    }

    public MiniBus() {
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

    public void setThereBackWindows(boolean isThereBackWindows) {
        this.isThereBackWindows = isThereBackWindows;
    }

    @Override
    public String toString() {
        return "MiniBus{" +
                "engine='" + getEngine() + '\'' +
                ", color='" + getColor() + '\'' +
                ", number='" + getNumber() + '\'' +
                ", maxSpeed=" + getMaxSpeed() +
                ", year=" + getYear() +
                ", isPassenger=" + isPassenger +
                ", isThereBackWindows=" + isThereBackWindows +
                '}';
    }
}
