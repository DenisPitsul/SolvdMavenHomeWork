package com.solvd.car.vehicle;

import com.solvd.car.vehicle.helper.Engine;

public abstract class Vehicle {
    private Engine engine;

    public Vehicle(Engine engine) {
        this.engine = engine;
    }

    public Vehicle() {
    }

    public String getEngine() {
        return engine.getName();
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public abstract void honk();

    public abstract String getShortInfo();

    public abstract String getCarModel();

}
