package com.solvd.car.utils.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ParkingPOJO {
    @JsonProperty("count")
    private int countOfParkedCars;
    @JsonProperty("parked_cars")
    private List<CarPOJO> parkedCars;

    public List<CarPOJO> getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(List<CarPOJO> parkedCars) {
        this.parkedCars = parkedCars;
    }

    public int getCountOfParkedCars() {
        return countOfParkedCars;
    }

    public void setCountOfParkedCars(int countOfParkedCars) {
        this.countOfParkedCars = countOfParkedCars;
    }

    @Override
    public String toString() {
        return "ParkingPOJO{" +
                "countOfParkedCars=" + countOfParkedCars +
                ", parkedCars=" + parkedCars +
                '}';
    }
}
