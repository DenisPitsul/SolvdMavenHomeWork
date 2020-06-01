package com.solvd.car.factory_method;

import com.solvd.car.factory_method.creator.*;
import com.solvd.car.odb.entity.Car;
import com.solvd.car.helper.CarModel;
import org.apache.log4j.Logger;

public class FactoryMethodCar {
    private static final Logger LOGGER = Logger.getLogger(FactoryMethodCar.class);

    /**
     * create car instance by carModel
     * @param carModel -> model of the car we have to create an instance of
     * @return created car instance
     */
    public static Car createCar(CarModel carModel) {
        Car car;

        switch (carModel) {
            case AUDI_A6:
                car = createAudiA6();
                break;
            case MERCEDES_VITO:
                car = createMercedesVito();
                break;
            case TOYOTA_LAND_CRUISER:
                car = createToyotaLandCruiser();
                break;
            case TESLA_SEMI:
                car = createTeslaSemi();
                break;
            default:
                car = createDefaultCar();
                break;
        }
        LOGGER.debug(car);

        return car;
    }

    private static Car createAudiA6() {
        CarCreator creator = new AudiA6Creator();
        return creator.createCar("red", "CE5510AA", 260, 2016);
    }

    private static Car createMercedesVito() {
        CarCreator creator = new MercedesVitoCreator();
        return creator.createCar("white", "CE2901AA", 240, 2012);
    }

    private static Car createToyotaLandCruiser() {
        CarCreator creator = new ToyotaLandCruiserCreator();
        return creator.createCar("yellow", "CE6511AA", 240, 2014);
    }

    private static Car createTeslaSemi() {
        CarCreator creator = new TeslaSemiCreator();
        return creator.createCar("grey", "CE4099AA", 200, 2017);
    }

    private static Car createDefaultCar() {
        CarCreator creator = new DefaultCreator();
        return creator.createCar("blue", "CE4100AA", 250, 2014);
    }
}
