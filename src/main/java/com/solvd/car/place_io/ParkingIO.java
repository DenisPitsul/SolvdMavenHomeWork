package com.solvd.car.place_io;

import com.solvd.car.place.Parking;
import com.solvd.car.utils.FileHelper;
import com.solvd.car.utils.FileIO;
import com.solvd.car.vehicle.Vehicle;

public class ParkingIO extends VehiclePlaceIO<Parking<Vehicle>> {
    public static final String PARKING_FILE_PATH = "src" + FileHelper.SEPARATOR + "main" + FileHelper.SEPARATOR + "resources"
            + FileHelper.SEPARATOR + "data" + FileHelper.SEPARATOR + "text_files" + FileHelper.SEPARATOR + "parking.txt";

    public ParkingIO(String filePath) {
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
    public void writeAllToFile(Parking<Vehicle> parking) {
        StringBuilder sb = new StringBuilder();

        for (Vehicle vehicle: parking.getParkingCars()) {
            if (vehicle != null) {
                sb.append(fromVehicleToString(vehicle));
            }
            sb.append(System.lineSeparator());
        }

        FileIO.writeToFile(getFilePath(), sb.toString());
    }

    @Override
    public Parking<Vehicle> readAllFromFile() {
        Parking<Vehicle> parking = new Parking<>();
        String stringFromFile = FileIO.readFromFile(getFilePath());
        String[] lines = stringFromFile.split(System.getProperty("line.separator"));
        for (String line : lines) {
            Vehicle vehicle = fromStringToVehicle(line);
            parking.add(vehicle);
        }
        return parking;
    }

    @Override
    public void clearFile() {
        FileIO.clearFile(getFilePath());
    }
}
