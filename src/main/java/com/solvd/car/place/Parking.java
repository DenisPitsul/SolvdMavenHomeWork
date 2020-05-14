package com.solvd.car.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.car.vehicle.Vehicle;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class Parking<T extends Vehicle> implements CarPlace<T> {
    private static final Logger LOGGER = Logger.getLogger(Parking.class);

    @JsonProperty("count")
    private int countOfParkedCars;
    @JsonProperty("parked_cars")
    private List<T> parkedCars;

    public Parking() {
        parkedCars = new LinkedList<>();
    }

    public List<T> getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(List<T> parkedCars) {
        this.parkedCars = parkedCars;
    }

    public int getCountOfParkedCars() {
        return countOfParkedCars;
    }

    /**
     * add car to garage
     * @param car -> will added to this parking
     */
    @Override
    public void add(T car) {
        parkedCars.add(car);
        countOfParkedCars++;
    }

    /**
     * Leave car from the parking
     * @param parkedCar -> car which have to delete from this parking
     * @return deleted car or null if parkedCar weren't in this parking
     */
    @Override
    public T leaveThePlace(T parkedCar) {
        T car = null;
        boolean isCarExist = false;
        for (T eachCar : parkedCars) {
            if (eachCar == parkedCars) {
                car = parkedCar;
                isCarExist = true;
                break;
            }
        }
        if (!isCarExist) {
            return null;
        }
        parkedCars.remove(car);
        countOfParkedCars--;
        return car;
    }

    /**
     * Leave car from the parking by index
     * @param carPlaceIndex -> index of car in car on the parking which have to delete from this parking
     * @return deleted car or null if there is not any cars on the parking by index
     */
    public T leaveTheParking(int carPlaceIndex) {
        if (parkedCars.get(carPlaceIndex) == null) {
            return null;
        }
        countOfParkedCars--;
        return parkedCars.remove(carPlaceIndex);
    }

    @Override
    public String toString() {
        return "Parking{" +
                "parkingCars=" + parkedCars +
                '}';
    }

    /**
     * Show all cars on the parking on the screen
     */
    @Override
    public void showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parking {").append(System.lineSeparator());
        sb.append("\tCount of parked cars: ").append(countOfParkedCars).append(System.lineSeparator());
        for (int i = 0; i < parkedCars.size(); i++) {
            sb.append("\tPlace #").append(i).append(": ").append(parkedCars.get(i).getShortInfo()).append(System.lineSeparator());
        }
        sb.append("}");
        LOGGER.debug(sb.toString());
    }

}
