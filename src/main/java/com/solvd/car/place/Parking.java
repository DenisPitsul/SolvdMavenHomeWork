package com.solvd.car.place;

import com.solvd.car.vehicle.Vehicle;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class Parking<T extends Vehicle> implements CarPlace<T> {
    private static final Logger LOGGER = Logger.getLogger(Parking.class);

    private static int countOfParkedCars;

    private List<T> parkingCars;

    public Parking() {
        parkingCars = new LinkedList<>();
    }

    public List<T> getParkingCars() {
        return parkingCars;
    }

    public void setParkingCars(List<T> parkingCars) {
        this.parkingCars = parkingCars;
    }


    public static int getCountOfParkedCars() {
        return countOfParkedCars;
    }

    /**
     * add car to garage
     * @param car -> will added to this parking
     */
    @Override
    public void add(T car) {
        parkingCars.add(car);
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
        for (T eachCar : parkingCars) {
            if (eachCar == parkingCars) {
                car = parkedCar;
                isCarExist = true;
                break;
            }
        }
        if (!isCarExist) {
            return null;
        }
        parkingCars.remove(car);
        countOfParkedCars--;
        return car;
    }

    /**
     * Leave car from the parking by index
     * @param carPlaceIndex -> index of car in car on the parking which have to delete from this parking
     * @return deleted car or null if there is not any cars on the parking by index
     */
    public T leaveTheParking(int carPlaceIndex) {
        if (parkingCars.get(carPlaceIndex) == null) {
            return null;
        }
        countOfParkedCars--;
        return parkingCars.remove(carPlaceIndex);
    }

    @Override
    public String toString() {
        return "Parking{" +
                "parkingCars=" + parkingCars +
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
        for (int i = 0; i < parkingCars.size(); i++) {
            sb.append("\tPlace #").append(i).append(": ").append(parkingCars.get(i).getShortInfo()).append(System.lineSeparator());
        }
        sb.append("}");
        LOGGER.debug(sb.toString());
    }

}
