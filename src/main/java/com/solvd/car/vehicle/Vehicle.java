package com.solvd.car.vehicle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    public static Engine fromStringToEngine(String str) {
        Engine engine = null;
        for (Engine eachEngine: Engine.values()) {
            if (str.equals(eachEngine.getName())) {
                engine = eachEngine;
                break;
            }
        }
        return engine;
    }

    @JsonIgnore
    public abstract String getShortInfo();

    @JsonProperty("car_model")
    public abstract String getCarModel();

}
