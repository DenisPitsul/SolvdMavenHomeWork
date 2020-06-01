package com.solvd.car.factory_method.creator;

import com.solvd.car.odb.entity.Car;
import com.solvd.car.odb.entity.CarDetail;
import com.solvd.car.odb.entity.Engine;
import com.solvd.car.helper.EngineInstance;

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
    public Car createCar(String color, String number, int maxSpeed, int year) {
        Car car = new Car();
        car.setModel("Tesla Semi");
        car.setColor(color);
        car.setNumber(number);
        car.setMaxSpeed(maxSpeed);
        car.setYear(year);

        Engine engine = new Engine();
        engine.setName(EngineInstance.ELECTRIC.getName());
        engine.setType(EngineInstance.ELECTRIC.getType());
        car.setEngine(engine);

        CarDetail carDetail = new CarDetail();
        carDetail.setLiftingCapacity(LIFTING_CAPACITY);
        carDetail.setBatteryPowerReserve(BATTERY_POWER_RESERVE);
        car.setCarDetail(carDetail);

        return car;
    }

}
