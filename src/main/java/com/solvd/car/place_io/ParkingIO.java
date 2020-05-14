package com.solvd.car.place_io;

import com.solvd.car.place.Parking;
import com.solvd.car.utils.json.model.CarPOJO;
import com.solvd.car.utils.json.JsonConverter;
import com.solvd.car.utils.json.model.ParkingPOJO;
import com.solvd.car.utils.text_file.FileHelper;
import com.solvd.car.utils.text_file.FileIO;
import com.solvd.car.place_io.helper.FileOperation;
import com.solvd.car.vehicle.Vehicle;
import com.solvd.car.vehicle.final_car.AudiA6;
import com.solvd.car.vehicle.final_car.MercedesVito;
import com.solvd.car.vehicle.final_car.TeslaSemi;
import com.solvd.car.vehicle.final_car.ToyotaLandCruiser;

public class ParkingIO extends VehiclePlaceIO<Parking<Vehicle>> implements FileOperation<Parking<Vehicle>> {
    public static final String PARKING_FILE_PATH = "src"
            + FileHelper.SEPARATOR + "main"
            + FileHelper.SEPARATOR + "resources"
            + FileHelper.SEPARATOR + "data"
            + FileHelper.SEPARATOR + "text"
            + FileHelper.SEPARATOR + "parking.txt";
    public static final String PARKING_JSON_FILE_PATH = "src"
            + FileHelper.SEPARATOR + "main"
            + FileHelper.SEPARATOR + "resources"
            + FileHelper.SEPARATOR + "data"
            + FileHelper.SEPARATOR + "json"
            + FileHelper.SEPARATOR + "parking.json";

    public ParkingIO(String filePath) {
        super(filePath);
    }

    @Override
    public Parking<Vehicle> readJsonFile(String filePath) {
        ParkingPOJO parkingPOJO = JsonConverter.convertJsonFileToParkingPOJO(filePath);
        Parking<Vehicle> parking = new Parking<>();
        for (CarPOJO carPOJO: parkingPOJO.getParkedCars()) {
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
            parking.add(vehicle);
        }
        return parking;
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

        for (Vehicle vehicle: parking.getParkedCars()) {
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
