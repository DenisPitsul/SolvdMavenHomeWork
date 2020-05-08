package com.solvd.car.vehicle.final_car;

import com.solvd.car.vehicle.Truck;
import com.solvd.car.vehicle.helper.Engine;
import com.solvd.car.vehicle.interfaces.Car;
import com.solvd.car.vehicle.interfaces.CarModel;
import org.apache.log4j.Logger;

public final class TeslaSemi extends Truck implements CarModel, Car {
    private static final Logger LOGGER = Logger.getLogger(TeslaSemi.class);

    private int batteryPowerReserve; // battery power in kilometers of autonomous driving

    public TeslaSemi(int maxSpeed, int year) {
        super(Engine.ELECTRIC, maxSpeed, year);
    }

    public TeslaSemi(String color, String number, int maxSpeed, int year, int liftingCapacity, int batteryPowerReserve) {
        super(Engine.ELECTRIC, color, number, maxSpeed, year, liftingCapacity);
        this.batteryPowerReserve = batteryPowerReserve;
    }

    public TeslaSemi() {
        setEngine(Engine.V6_TURBO);
        setLiftingCapacity(20);
        this.batteryPowerReserve = 800;
    }

    public int getBatteryPowerReserve() {
        return batteryPowerReserve;
    }

    public void setBatteryPowerReserve(int batteryPowerReserve) {
        this.batteryPowerReserve = batteryPowerReserve;
    }

    @Override
    public String toString() {
        return "TeslaSemi{" +
                "model='" + getCarModel() + '\'' +
                ", engine='" + getEngine() + '\'' +
                ", color='" + getColor() + '\'' +
                ", number='" + getNumber() + '\'' +
                ", maxSpeed=" + getMaxSpeed() +
                ", year=" + getYear() +
                ", liftingCapacity=" + getLiftingCapacity() +
                ", batteryPowerReserve=" + batteryPowerReserve +
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
