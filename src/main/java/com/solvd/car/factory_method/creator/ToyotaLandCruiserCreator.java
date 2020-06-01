package com.solvd.car.factory_method.creator;

import com.solvd.car.odb.entity.Car;
import com.solvd.car.odb.entity.CarDetail;
import com.solvd.car.odb.entity.Engine;
import com.solvd.car.helper.EngineInstance;

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
    public Car createCar(String color, String number, int maxSpeed, int year) {
        Car car = new Car();
        car.setModel("Toyota Land Cruiser");
        car.setColor(color);
        car.setNumber(number);
        car.setMaxSpeed(maxSpeed);
        car.setYear(year);

        Engine engine = new Engine();
        engine.setName(EngineInstance.V8.getName());
        engine.setType(EngineInstance.V8.getType());
        car.setEngine(engine);

        CarDetail carDetail = new CarDetail();
        carDetail.setThereTopTrunk(true);
        carDetail.setClearanceLength(CLEARANCE_LENGTH);
        carDetail.setThereBackViewCamera(true);
        car.setCarDetail(carDetail);

        return car;
    }

}
