package com.solvd.car.place;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solvd.car.odb.entity.CarInGarage;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GarageOfHome {
    private static final Logger LOGGER = Logger.getLogger(GarageOfHome.class);

    private boolean isBig;
    private List<CarInGarage> carsInGarage;

    private boolean isBigGarageValueSetted = false;

    public GarageOfHome() {
        isBig = false;
        carsInGarage = new ArrayList<>();
    }

    public boolean isBig() {
        return isBig;
    }

    /**
     * if isBig value already setted you can not set it again
     * @param isBig -> if garage big isBig = true else if garage small isBig = false
     */
    public void setBig(boolean isBig) {
        if (!isBigGarageValueSetted) {
            this.isBig = isBig;
            isBigGarageValueSetted = true;
        }
    }

    public List<CarInGarage> getCarsInGarage() {
        return carsInGarage;
    }

    public void setCarsInGarage(List<CarInGarage> carsInGarage) {
        this.carsInGarage = carsInGarage;
    }

    @JsonIgnore
    public boolean isBigGarageValueSetted() {
        return isBigGarageValueSetted;
    }

    /**
     * add car to garage
     * @param car -> will added to this garage
     */
    public void add(CarInGarage car) {
        carsInGarage.add(car);
    }

    /**
     * Leave car from the car dealership
     * @param carInGarage -> car which have to delete from this garage
     * @return deleted car or null if selectedCar weren't in this garage
     */
    public CarInGarage leaveThePlace(CarInGarage carInGarage) {
        CarInGarage car = null;
        boolean isCarExist = false;
        for (CarInGarage eachCar : carsInGarage) {
            if (eachCar == carInGarage) {
                car = carInGarage;
                isCarExist = true;
                break;
            }
        }
        if (!isCarExist) {
            return null;
        }
        carsInGarage.remove(car);
        return car;
    }

    /**
     * Show all cars in the garage on the screen
     */
    public void showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Garage {").append(System.lineSeparator());
        sb.append("\tGarage is ").append(isBig ? "big" : "small").append(System.lineSeparator());
        int i = 0;
        for (CarInGarage carInGarage : carsInGarage) {
            sb.append("\tCar №").append(i).append(": ").append(carInGarage.getCar().getShortInfo())
                    .append(System.lineSeparator());
            i++;
        }
        sb.append("}");
        LOGGER.debug(sb.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\tGarage {").append(System.lineSeparator());
        sb.append("\t\t\tGarage is ").append(isBig ? "big" : "small").append(System.lineSeparator());
        int i = 0;
        for (CarInGarage carInGarage : carsInGarage) {
            sb.append("\t\t\tCar #").append(i).append(": ").append(carInGarage.getCar().getShortInfo())
                    .append(System.lineSeparator());
            i++;
        }
        sb.append("\t\t}");
        return sb.toString();
    }
}
