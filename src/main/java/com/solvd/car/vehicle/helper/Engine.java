package com.solvd.car.vehicle.helper;

public enum Engine {
    V6_TURBO("V6+Turbo", "petrol"),
    V8("V8_2.5", "petrol"),
    DIESEL("Diesel_2.5", "diesel"),
    ELECTRIC("Electric", "electric");

    Engine(String name, String type) {
        this.name = name;
        this.type = type;
    }

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
