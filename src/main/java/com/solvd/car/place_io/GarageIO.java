package com.solvd.car.place_io;

import com.solvd.car.place.Garage;
import com.solvd.car.utils.json.model.CarPOJO;
import com.solvd.car.utils.json.model.GaragePOJO;
import com.solvd.car.utils.json.JsonConverter;
import com.solvd.car.utils.text_file.FileIO;
import com.solvd.car.place_io.helper.FileOperation;
import com.solvd.car.vehicle.Vehicle;
import com.solvd.car.vehicle.final_car.AudiA6;
import com.solvd.car.vehicle.final_car.MercedesVito;
import com.solvd.car.vehicle.final_car.TeslaSemi;
import com.solvd.car.vehicle.final_car.ToyotaLandCruiser;

public class GarageIO extends VehiclePlaceIO<Garage<Vehicle>> implements FileOperation<Garage<Vehicle>> {

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
    public Garage<Vehicle> readJsonFile(String filePath) {
        GaragePOJO garagePOJO = JsonConverter.convertJsonFileToGaragePOJO(filePath);
        Garage<Vehicle> garage = new Garage<>();
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
