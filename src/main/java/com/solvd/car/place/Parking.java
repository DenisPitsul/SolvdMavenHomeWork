package com.solvd.car.place;

import com.solvd.car.odb.entity.ParkedCar;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class Parking {
    private static final Logger LOGGER = Logger.getLogger(Parking.class);

    private int countOfParkedCars;
    private List<ParkedCar> parkedCars;

    public Parking() {
        parkedCars = new LinkedList<>();
    }

    public List<ParkedCar> getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(List<ParkedCar> parkedCars) {
        this.parkedCars = parkedCars;
        this.countOfParkedCars = parkedCars.size();
    }

    public int getCountOfParkedCars() {
        return countOfParkedCars;
    }

    /**
     * add car to garage
     * @param car -> will added to this parking
     */
    public void add(ParkedCar car) {
        parkedCars.add(car);
        countOfParkedCars++;
    }

    /**
     * Leave car from the parking
     * @param parkedCar -> car which have to delete from this parking
     * @return deleted car or null if parkedCar weren't in this parking
     */
    public ParkedCar leaveThePlace(ParkedCar parkedCar) {
        ParkedCar car = null;
        boolean isCarExist = false;
        for (ParkedCar eachCar : parkedCars) {
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
    public ParkedCar leaveTheParking(int carPlaceIndex) {
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
    public void showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parking {").append(System.lineSeparator());
        sb.append("\tCount of parked cars: ").append(countOfParkedCars).append(System.lineSeparator());
        for (int i = 0; i < parkedCars.size(); i++) {
            sb.append("\tPlace #").append(i).append(": ").append(parkedCars.get(i).getCar().getShortInfo()).append(System.lineSeparator());
        }
        sb.append("}");
        LOGGER.debug(sb.toString());
    }

}
