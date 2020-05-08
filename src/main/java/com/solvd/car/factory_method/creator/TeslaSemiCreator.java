package com.solvd.car.factory_method.creator;

import com.solvd.car.vehicle.Vehicle;
import com.solvd.car.vehicle.final_car.TeslaSemi;

public class TeslaSemiCreator implements CarCreator {
    private static final int LIFTING_CAPACITY = 21;
    private static final int BATTERY_POWER_RESERVE = 810;

    /**
     * create Tesla Semi instance
     * @param color -> parameter of the car
     * @param number -> parameter of the car
     * @param maxSpeed -> parameter of the car
     * @param year -> parameter of the car
     * @return Tesla Semi instance
     */
    @Override
    public Vehicle createCar(String color, String number, int maxSpeed, int year) {
        return new TeslaSemi(color, number, maxSpeed, year, LIFTING_CAPACITY, BATTERY_POWER_RESERVE);
    }

}
