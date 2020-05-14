package com.solvd.car.place;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.car.utils.json.model.CarPOJO;
import com.solvd.car.utils.json.model.GaragePOJO;
import com.solvd.car.vehicle.Vehicle;
import com.solvd.car.vehicle.final_car.AudiA6;
import com.solvd.car.vehicle.final_car.MercedesVito;
import com.solvd.car.vehicle.final_car.TeslaSemi;
import com.solvd.car.vehicle.final_car.ToyotaLandCruiser;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Garage<T extends Vehicle> implements CarPlace<T> {
    private static final Logger LOGGER = Logger.getLogger(Garage.class);

    @JsonProperty("big")
    private boolean isBig;
    @JsonProperty("cars")
    private Set<T> carsInGarage;

    @JsonIgnore
    private boolean isBigGarageValueSetted = false;

    public Garage() {
        isBig = false;
        carsInGarage = new HashSet<>();
    }

    @JsonIgnore
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

    public Set<T> getCarsInGarage() {
        return carsInGarage;
    }

    public void setCarsInGarage(Set<T> carsInGarage) {
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
    @Override
    public void add(T car) {
        carsInGarage.add(car);
    }

    /**
     * Leave car from the car dealership
     * @param carInGarage -> car which have to delete from this garage
     * @return deleted car or null if selectedCar weren't in this garage
     */
    @Override
    public T leaveThePlace(T carInGarage) {
        T car = null;
        boolean isCarExist = false;
        for (T eachCar : carsInGarage) {
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
        return (T) car;
    }

    /**
     * Show all cars in the garage on the screen
     */
    @Override
    public void showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Garage {").append(System.lineSeparator());
        sb.append("\tGarage is ").append(isBig ? "big" : "small").append(System.lineSeparator());
        int i = 0;
        for (T car : carsInGarage) {
            sb.append("\tCar №").append(i).append(": ").append(car.getShortInfo()).append(System.lineSeparator());
            i++;
        }
        sb.append("}");
        LOGGER.debug(sb.toString());
    }

    public static Garage<Vehicle> getGarageFromGaragePOJO(GaragePOJO garagePOJO) {
        Garage<Vehicle> garage = new Garage<>();
        garage.setBig(garagePOJO.isBig());
        for (CarPOJO carPOJO: garagePOJO.getCarsInGarage()) {
            Vehicle vehicle;
            switch (carPOJO.getCarModel()) {
                case "AudiA6":
                    vehicle = AudiA6.initAudiA6FromCarPOJO(carPOJO);
                    break;
                case "MercedesVito":
                    vehicle = MercedesVito.initMercedesVitoFromCarPOJO(carPOJO);
                    break;
                case "ToyotaLandCruiser":
                    vehicle = ToyotaLandCruiser.initToyotaLandCruiserFromCarPOJO(carPOJO);
                    break;
                case "TeslaSemi":
                    vehicle = TeslaSemi.initTeslaSemiFromCarPOJO(carPOJO);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + carPOJO.getCarModel());
            }
            garage.add(vehicle);
        }
        return garage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\tGarage {").append(System.lineSeparator());
        sb.append("\t\t\tGarage is ").append(isBig ? "big" : "small").append(System.lineSeparator());
        int i = 0;
        for (T car : carsInGarage) {
            sb.append("\t\t\tCar #").append(i).append(": ").append(car.getShortInfo()).append(System.lineSeparator());
            i++;
        }
        sb.append("\t\t}");
        return sb.toString();
    }
}
