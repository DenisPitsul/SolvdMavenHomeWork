package com.solvd.car.utils.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class GaragePOJO {
    @JsonProperty("big")
    private boolean isBig;
    @JsonProperty("cars")
    private Set<CarPOJO> carsInGarage;

    public boolean isBig() {
        return isBig;
    }

    public void setBig(boolean big) {
        isBig = big;
    }

    public Set<CarPOJO> getCarsInGarage() {
        return carsInGarage;
    }

    public void setCarsInGarage(Set<CarPOJO> carsInGarage) {
        this.carsInGarage = carsInGarage;
    }

    @Override
    public String toString() {
        return "GaragePOJO{" +
                "isBig=" + isBig +
                ", carsInGarage=" + carsInGarage +
                '}';
    }
}
