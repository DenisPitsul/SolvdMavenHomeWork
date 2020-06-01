package com.solvd.car.factory_method.creator;

import com.solvd.car.odb.entity.Car;
import com.solvd.car.odb.entity.CarDetail;
import com.solvd.car.odb.entity.Engine;
import com.solvd.car.helper.EngineInstance;

public class AudiA6Creator implements CarCreator {
    private static final int WHEEL_RADIUS = 20;

    /**
     * create Audi A6 instance
     * @param color -> parameter of the car
     * @param number -> parameter of the car
     * @param maxSpeed -> parameter of the car
     * @param year -> parameter of the car
     * @return Audi A6 instance
     */
    @Override
    public Car createCar(String color, String number, int maxSpeed, int year) {
        Car car = new Car();
        car.setModel("Audi A6");
        car.setColor(color);
        car.setNumber(number);
        car.setMaxSpeed(maxSpeed);
        car.setYear(year);

        Engine engine = new Engine();
        engine.setName(EngineInstance.V6_TURBO.getName());
        engine.setType(EngineInstance.V6_TURBO.getType());
        car.setEngine(engine);

        CarDetail carDetail = new CarDetail();
        carDetail.setWheelRadius(WHEEL_RADIUS);
        carDetail.setSalon(SALON);
        carDetail.setThereBackViewCamera(true);
        car.setCarDetail(carDetail);

        return car;
    }

}
