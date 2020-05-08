package com.solvd.car.vehicle.final_car;

import com.solvd.car.vehicle.MiniBus;
import com.solvd.car.vehicle.helper.Engine;
import com.solvd.car.vehicle.interfaces.Car;
import com.solvd.car.vehicle.interfaces.CarModel;
import org.apache.log4j.Logger;

public final class MercedesVito extends MiniBus implements CarModel, Car {
    private static final Logger LOGGER = Logger.getLogger(MercedesVito.class);

    private int passengerSeatsCount;

    public MercedesVito(int maxSpeed, int year) {
        super(Engine.DIESEL, maxSpeed, year);
        setPassenger(true);
        setThereBackWindows(true);
    }

    public MercedesVito(String color, String number, int maxSpeed, int year, int passengerSeatsCount) {
        super(Engine.DIESEL, color, number, maxSpeed, year, true, true);
        this.passengerSeatsCount = passengerSeatsCount;
    }

    public MercedesVito() {
        setEngine(Engine.DIESEL);
        setPassenger(true);
        setThereBackWindows(true);
        this.passengerSeatsCount = 7;
    }

    public int getPassengerSeatsCount() {
        return passengerSeatsCount;
    }

    public void setPassengerSeatsCount(int passengerSeatsCount) {
        this.passengerSeatsCount = passengerSeatsCount;
    }

    @Override
    public String toString() {
        return "MercedesVito{" +
                "model='" + getCarModel() + '\'' +
                ", engine='" + getEngine() + '\'' +
                ", color='" + getColor() + '\'' +
                ", number='" + getNumber() + '\'' +
                ", maxSpeed=" + getMaxSpeed() +
                ", year=" + getYear() +
                ", isPassenger=" + isPassenger() +
                ", isThereBackWindows=" + isThereBackWindows() +
                ", passengerSeatsCount=" + passengerSeatsCount +
                '}';
    }

    @Override
    public String getCarModel() {
        String className = this.getClass().getName();
        return className.substring(className.lastIndexOf(".") + 1);
    }

    @Override
    public int accelerate(int a) {
        if ((this.getVelocity() + a) <= this.getMaxSpeed())
            this.setVelocity(this.getVelocity() + a);
        else
            this.setVelocity(this.getMaxSpeed());

        return this.getVelocity();
    }

    @Override
    public int decelerate(int a) {
        if ((this.getVelocity() - a) >= 0)
            this.setVelocity(this.getVelocity() - a);
        else
            this.setVelocity(0);

        return this.getVelocity();
    }

    @Override
    public String getShortInfo() {
        return "Car{model='" + this.getCarModel() + "', number='" + this.getNumber() + "'}";
    }

    @Override
    public void printInfo() {
        LOGGER.debug(this);
    }

}
