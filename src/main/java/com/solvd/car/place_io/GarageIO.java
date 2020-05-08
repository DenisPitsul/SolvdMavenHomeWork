package com.solvd.car.place_io;

import com.solvd.car.place.Garage;
import com.solvd.car.utils.FileIO;
import com.solvd.car.vehicle.Vehicle;

public class GarageIO extends VehiclePlaceIO<Garage<Vehicle>> {

    public GarageIO(String filePath) {
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
    public void writeAllToFile(Garage<Vehicle> garage) {
        StringBuilder sb = new StringBuilder();

        for (Vehicle vehicle: garage.getCarsInGarage()) {
            if (vehicle != null) {
                sb.append(fromVehicleToString(vehicle));
            }
            sb.append(System.lineSeparator());
        }

        FileIO.writeToFile(getFilePath(), sb.toString());
    }

    @Override
    public Garage<Vehicle> readAllFromFile() {
        Garage<Vehicle> garage = new Garage<>();
        String stringFromFile = FileIO.readFromFile(getFilePath());
        String[] lines = stringFromFile.split(System.getProperty("line.separator"));
        for (String line : lines) {
            garage.add(fromStringToVehicle(line));
        }
        return garage;
    }

    @Override
    public void clearFile() {
        FileIO.clearFile(getFilePath());
    }
}
