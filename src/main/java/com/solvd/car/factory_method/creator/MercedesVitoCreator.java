package com.solvd.car.factory_method.creator;

import com.solvd.car.vehicle.Vehicle;
import com.solvd.car.vehicle.final_car.MercedesVito;

public class MercedesVitoCreator implements CarCreator {
    private static final int PASSENGER_SEATS_COUNT = 8;

    /**
     * create Mercedes Vito instance
     * @param color -> parameter of the car
     * @param number -> parameter of the car
     * @param maxSpeed -> parameter of the car
     * @param year -> parameter of the car
     * @return Mercedes Vito instance
     */
    @Override
    public Vehicle createCar(String color, String number, int maxSpeed, int year) {
        return new MercedesVito(color, number, maxSpeed, year, PASSENGER_SEATS_COUNT);
    }

}
