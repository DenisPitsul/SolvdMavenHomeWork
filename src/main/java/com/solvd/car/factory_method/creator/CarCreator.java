package com.solvd.car.factory_method.creator;

import com.solvd.car.vehicle.Vehicle;
import com.solvd.car.vehicle.final_car.AudiA6;

public interface CarCreator {
    int WHEEL_RADIUS = 21;
    String SALON = "Alcantara";

    /**
     * create Audi A6 instance
     * @param color -> parameter of the car
     * @param number -> parameter of the car
     * @param maxSpeed -> parameter of the car
     * @param year -> parameter of the car
     * @return Audi A6 instance
     */
    default Vehicle createCar(String color, String number, int maxSpeed, int year) {
        return new AudiA6(color, number, maxSpeed, year, WHEEL_RADIUS, SALON);
    }

}
