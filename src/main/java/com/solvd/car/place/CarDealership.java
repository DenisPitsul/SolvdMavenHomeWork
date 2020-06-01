package com.solvd.car.place;

import com.solvd.car.odb.entity.SellingCar;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CarDealership {
    private static final Logger LOGGER = Logger.getLogger(CarDealership.class);

    private int countOfCarsInCarDealership;
    private List<SellingCar> sellingCars;

    public CarDealership() {
        sellingCars = new ArrayList<>();
    }

    public List<SellingCar> getSellingCars() {
        return sellingCars;
    }

    public void setSellingCars(List<SellingCar> sellingCars) {
        this.sellingCars = sellingCars;
        this.countOfCarsInCarDealership = sellingCars.size();
    }

    public int getCountOfCarsInCarDealership() {
        return countOfCarsInCarDealership;
    }

    /**
     * add car to car dealership
     * @param car -> will added to this car dealership
     */
    public void add(SellingCar car) {
        sellingCars.add(car);
        countOfCarsInCarDealership++;
    }

    /**
     * Leave car from the car dealership
     * @param selectedCar -> car which have to delete from this car dealership
     * @return deleted car or null if selectedCar weren't in this car dealership
     */
    public SellingCar leaveThePlace(SellingCar selectedCar) {
        SellingCar car = null;
        boolean isCarExist = false;
        for (SellingCar eachCar : sellingCars) {
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
    public SellingCar leaveTheCarDealership(int carPlaceIndex) {
        if (sellingCars.get(carPlaceIndex) == null) {
            return null;
        }
        countOfCarsInCarDealership--;
        return sellingCars.remove(carPlaceIndex);
    }

    /**
     * Show all cars in the car dealership on the screen
     */
    public void showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Car dealership {").append(System.lineSeparator());
        sb.append("\tCount of cars in car dealership: ").append(countOfCarsInCarDealership).append(System.lineSeparator());
        for (int i = 0; i < sellingCars.size(); i++) {
            sb.append("\tCar #").append(i).append(": ").append(sellingCars.get(i).getCar().getShortInfo()).append(System.lineSeparator());
        }
        sb.append("}");
        LOGGER.debug(sb.toString());
    }
}
