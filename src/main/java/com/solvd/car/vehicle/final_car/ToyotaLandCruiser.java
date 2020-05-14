package com.solvd.car.vehicle.final_car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.car.utils.json.model.CarPOJO;
import com.solvd.car.vehicle.SUV;
import com.solvd.car.vehicle.Vehicle;
import com.solvd.car.vehicle.helper.Engine;
import com.solvd.car.vehicle.interfaces.Car;
import com.solvd.car.vehicle.interfaces.CarModel;
import org.apache.log4j.Logger;

public final class ToyotaLandCruiser extends SUV implements CarModel, Car {
    private static final Logger LOGGER = Logger.getLogger(ToyotaLandCruiser.class);

    @JsonProperty("there_back_view_camera")
    private boolean isThereBackViewCamera;

    public ToyotaLandCruiser(int maxSpeed, int year) {
        super(Engine.V8, maxSpeed, year);
        this.isThereBackViewCamera = true;
        setThereTopTrunk(true);
    }

    public ToyotaLandCruiser(String color, String number, int maxSpeed, int year, int clearanceLength) {
        super(Engine.V8, color, number, maxSpeed, year, true, clearanceLength + 5);
        this.isThereBackViewCamera = true;
    }

    public ToyotaLandCruiser() {
        setEngine(Engine.V6_TURBO);
        setClearanceLength(50);
        setThereTopTrunk(true);
        this.isThereBackViewCamera = true;
    }

    @JsonIgnore
    public boolean isThereBackViewCamera() {
        return isThereBackViewCamera;
    }

    public void setThereBackViewCamera(boolean isThereBackViewCamera) {
        this.isThereBackViewCamera = isThereBackViewCamera;
    }

    @Override
    public void setClearanceLength(int clearanceLength) {
        super.setClearanceLength(clearanceLength + 5);
    }

    public static ToyotaLandCruiser initToyotaLandCruiserFromCarPOJO(CarPOJO carPOJO) {
        ToyotaLandCruiser toyotaLandCruiser = new ToyotaLandCruiser();

        toyotaLandCruiser.setEngine(Vehicle.fromStringToEngine(carPOJO.getEngine()));
        toyotaLandCruiser.setColor(carPOJO.getColor());
        toyotaLandCruiser.setNumber(carPOJO.getNumber());
        toyotaLandCruiser.setMaxSpeed(carPOJO.getMaxSpeed());
        toyotaLandCruiser.setYear(carPOJO.getYear());
        toyotaLandCruiser.setThereTopTrunk(carPOJO.isThereTopTrunk());
        toyotaLandCruiser.setClearanceLength(carPOJO.getClearanceLength());
        toyotaLandCruiser.setThereBackViewCamera(carPOJO.isThereBackViewCamera());

        return toyotaLandCruiser;
    }

    @Override
    public String toString() {
        return "ToyotaLandCruiser{" +
                "model='" + getCarModel() + '\'' +
                ", engine='" + getEngine() + '\'' +
                ", color='" + getColor() + '\'' +
                ", number='" + getNumber() + '\'' +
                ", maxSpeed=" + getMaxSpeed() +
                ", year=" + getYear() +
                ", isThereTopTrunk=" + isThereTopTrunk() +
                ", clearanceLength=" + getClearanceLength() +
                ", isThereBackViewCamera=" + isThereBackViewCamera +
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
