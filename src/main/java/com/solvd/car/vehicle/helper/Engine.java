package com.solvd.car.vehicle.helper;

public enum Engine {
    V6_TURBO("V6+Turbo"), V8("V8_4.5"), DIESEL("Diesel_2.2"), ELECTRIC("ELECTRIC");

    Engine(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
