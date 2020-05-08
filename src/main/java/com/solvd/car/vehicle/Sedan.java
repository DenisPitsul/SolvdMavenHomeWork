package com.solvd.car.vehicle;

import com.solvd.car.vehicle.helper.Engine;

public class Sedan extends Car {
    private int wheelRadius;

    public Sedan(Engine engine, int maxSpeed, int year) {
        super(engine, maxSpeed, year);
    }

    public Sedan(Engine engine, String color, String number, int maxSpeed, int year, int wheelRadius) {
        super(engine, color, number, maxSpeed, year);
        this.wheelRadius = wheelRadius;
    }

    public Sedan() {
    }

    public int getWheelRadius() {
        return wheelRadius;
    }

    public void setWheelRadius(int wheelRadius) {
        this.wheelRadius = wheelRadius;
    }

    @Override
    public String toString() {
        return "Sedan{" +
                "engine='" + getEngine() + '\'' +
                ", color='" + getColor() + '\'' +
                ", number='" + getNumber() + '\'' +
                ", maxSpeed=" + getMaxSpeed() +
                ", year=" + getYear() +
                ", wheelRadius=" + wheelRadius +
                '}';
    }
}
