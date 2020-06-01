package com.solvd.car.factory_method.creator;

import com.solvd.car.odb.entity.Car;
import com.solvd.car.odb.entity.CarDetail;
import com.solvd.car.odb.entity.Engine;
import com.solvd.car.helper.EngineInstance;

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
    public Car createCar(String color, String number, int maxSpeed, int year) {
        Car car = new Car();
        car.setModel("Mercedes Vito");
        car.setColor(color);
        car.setNumber(number);
        car.setMaxSpeed(maxSpeed);
        car.setYear(year);

        Engine engine = new Engine();
        engine.setName(EngineInstance.DIESEL.getName());
        engine.setType(EngineInstance.DIESEL.getType());
        car.setEngine(engine);

        CarDetail carDetail = new CarDetail();
        carDetail.setPassenger(true);
        carDetail.setThereBackWindows(true);
        carDetail.setPassengerSeatsCount(PASSENGER_SEATS_COUNT);
        car.setCarDetail(carDetail);

        return car;
    }

}
