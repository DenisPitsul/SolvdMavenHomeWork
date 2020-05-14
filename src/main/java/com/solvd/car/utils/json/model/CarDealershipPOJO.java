package com.solvd.car.utils.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CarDealershipPOJO {
    @JsonProperty("count")
    private int countOfCarsInCarDealership;
    @JsonProperty("selling_cars")
    private List<CarPOJO> sellingCars;

    public int getCountOfCarsInCarDealership() {
        return countOfCarsInCarDealership;
    }

    public void setCountOfCarsInCarDealership(int countOfCarsInCarDealership) {
        this.countOfCarsInCarDealership = countOfCarsInCarDealership;
    }

    public List<CarPOJO> getSellingCars() {
        return sellingCars;
    }

    public void setSellingCars(List<CarPOJO> sellingCars) {
        this.sellingCars = sellingCars;
    }

    @Override
    public String toString() {
        return "CarDealershipPOJO{" +
                "countOfCarsInCarDealership=" + countOfCarsInCarDealership +
                ", sellingCars=" + sellingCars +
                '}';
    }
}
