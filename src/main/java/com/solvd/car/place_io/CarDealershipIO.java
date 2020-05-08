package com.solvd.car.place_io;

import com.solvd.car.place.CarDealership;
import com.solvd.car.utils.FileHelper;
import com.solvd.car.utils.FileIO;
import com.solvd.car.vehicle.Vehicle;

public class CarDealershipIO extends VehiclePlaceIO<CarDealership<Vehicle>> {
    public static final String CAR_DEALERSHIP_FILE_PATH = "src" + FileHelper.SEPARATOR + "main" + FileHelper.SEPARATOR + "resources"
            + FileHelper.SEPARATOR + "data"+ FileHelper.SEPARATOR + "text_files" + FileHelper.SEPARATOR + "car_dealership.txt";

    public CarDealershipIO(String filePath) {
        super(filePath);
    }

    @Override
    public void writeToFile(Vehicle vehicle) {
        StringBuilder sb = new StringBuilder();

        if (vehicle != null) {
            sb.append(fromVehicleToString(vehicle));
        }
        sb.append(System.lineSeparator());

        FileIO.writeToFile(getFilePath(), sb.toString());
    }

    @Override
    public void writeAllToFile(CarDealership<Vehicle> carDealership) {
        StringBuilder sb = new StringBuilder();

        for (Vehicle vehicle: carDealership.getSellingCars()) {
            if (vehicle != null) {
                sb.append(fromVehicleToString(vehicle));
            }
            sb.append(System.lineSeparator());
        }

        FileIO.writeToFile(getFilePath(), sb.toString());
    }

    @Override
    public CarDealership<Vehicle> readAllFromFile() {
        CarDealership<Vehicle> carDealership = new CarDealership<>();
        String stringFromFile = FileIO.readFromFile(getFilePath());
        String[] lines = stringFromFile.split(System.getProperty("line.separator"));
        for (String line : lines) {
            carDealership.add(fromStringToVehicle(line));
        }
        return carDealership;
    }

    @Override
    public void clearFile() {
        FileIO.clearFile(getFilePath());
    }
}
