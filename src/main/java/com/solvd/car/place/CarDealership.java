package com.solvd.car.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.car.vehicle.Vehicle;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CarDealership<T extends Vehicle> implements CarPlace<T> {
    private static final Logger LOGGER = Logger.getLogger(CarDealership.class);

    @JsonProperty("count")
    private int countOfCarsInCarDealership;
    @JsonProperty("selling_cars")
    private List<T> sellingCars;

    public CarDealership() {
        sellingCars = new ArrayList<>();
    }

    public List<T> getSellingCars() {
        return sellingCars;
    }

    public void setSellingCars(List<T> sellingCars) {
        this.sellingCars = sellingCars;
    }

    public int getCountOfCarsInCarDealership() {
        return countOfCarsInCarDealership;
    }

    /**
     * add car to car dealership
     * @param car -> will added to this car dealership
     */
    @Override
    public void add(T car) {
        sellingCars.add(car);
        countOfCarsInCarDealership++;
    }

    /**
     * Leave car from the car dealership
     * @param selectedCar -> car which have to delete from this car dealership
     * @return deleted car or null if selectedCar weren't in this car dealership
     */
    @Override
    public T leaveThePlace(T selectedCar) {
        T car = null;
        boolean isCarExist = false;
        for (T eachCar : sellingCars) {
            if (eachCar == selectedCar) {
                car = selectedCar;
                isCarExist = true;
                break;
            }
        }
        if (!isCarExist) {
            return null;
        }
        sellingCars.remove(car);
        countOfCarsInCarDealership--;
        return car;
    }

    /**
     * Leave car from the car dealership by index
     * @param carPlaceIndex -> index of car in car in car dealer ship which have to delete from this car dealership
     * @return deleted car or null if there is not any cars in the car dealership by index
     */
    public T leaveTheCarDealership(int carPlaceIndex) {
        if (sellingCars.get(carPlaceIndex) == null) {
            return null;
        }
        countOfCarsInCarDealership--;
        return sellingCars.remove(carPlaceIndex);
    }

    /**
     * Show all cars in the car dealership on the screen
     */
    @Override
    public void showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Car dealership {").append(System.lineSeparator());
        sb.append("\tCount of cars in car dealership: ").append(countOfCarsInCarDealership).append(System.lineSeparator());
        for (int i = 0; i < sellingCars.size(); i++) {
            sb.append("\tCar #").append(i).append(": ").append(sellingCars.get(i).getShortInfo()).append(System.lineSeparator());
        }
        sb.append("}");
        LOGGER.debug(sb.toString());
    }
}
