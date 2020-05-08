package com.solvd.car.factory_method.creator;

import com.solvd.car.vehicle.Vehicle;
import com.solvd.car.vehicle.final_car.ToyotaLandCruiser;

public class ToyotaLandCruiserCreator implements CarCreator {
    private static final int CLEARANCE_LENGTH = 55;

    /**
     * create Toyota Land Cruiser instance
     * @param color -> parameter of the car
     * @param number -> parameter of the car
     * @param maxSpeed -> parameter of the car
     * @param year -> parameter of the car
     * @return Toyota Land Cruiser instance
     */
    @Override
    public Vehicle createCar(String color, String number, int maxSpeed, int year) {
        return new ToyotaLandCruiser(color, number, maxSpeed, year, CLEARANCE_LENGTH);
    }

}
