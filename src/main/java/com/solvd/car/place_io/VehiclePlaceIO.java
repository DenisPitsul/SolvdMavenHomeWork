package com.solvd.car.place_io;

import com.solvd.car.place.CarPlace;
import com.solvd.car.utils.text_file.FileIO;
import com.solvd.car.utils.json.JsonConverter;
import com.solvd.car.place_io.helper.JsonOperation;
import com.solvd.car.vehicle.Vehicle;
import com.solvd.car.vehicle.final_car.AudiA6;
import com.solvd.car.vehicle.final_car.MercedesVito;
import com.solvd.car.vehicle.final_car.TeslaSemi;
import com.solvd.car.vehicle.final_car.ToyotaLandCruiser;
import com.solvd.car.vehicle.helper.Engine;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Arrays;

public abstract class VehiclePlaceIO<G> implements JsonOperation<CarPlace<Vehicle>> {
    private static final Logger LOGGER = Logger.getLogger(VehiclePlaceIO.class);

    private File file;

    public VehiclePlaceIO(String filePath) {
        file = new File(filePath);
    }

    public File getFile() {
        return file;
    }

    public String getFilePath() {
        return file.getPath();
    }

    public abstract void writeToFile(Vehicle vehicle);

    @Override
    public void writeToJsonFile(CarPlace<Vehicle> carPlace, String filePath) {
        FileIO.createFileIfItDoesNotExist(filePath);
        JsonConverter.convertJavaToJsonFile(carPlace, filePath);
    }

    protected String fromVehicleToString(Vehicle vehicle) {
        StringBuilder sb = new StringBuilder();

        if (vehicle instanceof AudiA6) {
            AudiA6 audiA6 = (AudiA6) vehicle;
            sb.append(fromAudiA6ToString(audiA6));
        }
        else if (vehicle instanceof MercedesVito) {
            MercedesVito mercedesVito = (MercedesVito) vehicle;
            sb.append(fromMercedesVitoToString(mercedesVito));
        }
        else if (vehicle instanceof ToyotaLandCruiser) {
            ToyotaLandCruiser toyotaLandCruiser = (ToyotaLandCruiser) vehicle;
            sb.append(fromToyotaLandCruiserToString(toyotaLandCruiser));
        }
        else if (vehicle instanceof TeslaSemi) {
            TeslaSemi teslaSemi = (TeslaSemi) vehicle;
            sb.append(fromTeslaSemiToString(teslaSemi));
        }

        return sb.toString();
    }

    protected String fromAudiA6ToString(AudiA6 audiA6) {
        StringBuilder sb = new StringBuilder();

        sb.append(audiA6.getCarModel()).append(",");
        sb.append(audiA6.getEngine()).append(",");
        sb.append(audiA6.getColor()).append(",");
        sb.append(audiA6.getNumber()).append(",");
        sb.append(audiA6.getMaxSpeed()).append(",");
        sb.append(audiA6.getYear()).append(",");
        sb.append(audiA6.getWheelRadius()).append(",");
        sb.append(audiA6.getSalon()).append(",");
        sb.append(audiA6.isThereBackViewCamera()).append(",");

        return sb.toString();
    }

    protected String fromMercedesVitoToString(MercedesVito mercedesVito) {
        StringBuilder sb = new StringBuilder();

        sb.append(mercedesVito.getCarModel()).append(",");
        sb.append(mercedesVito.getEngine()).append(",");
        sb.append(mercedesVito.getColor()).append(",");
        sb.append(mercedesVito.getNumber()).append(",");
        sb.append(mercedesVito.getMaxSpeed()).append(",");
        sb.append(mercedesVito.getYear()).append(",");
        sb.append(mercedesVito.isPassenger()).append(",");
        sb.append(mercedesVito.isThereBackWindows()).append(",");
        sb.append(mercedesVito.getPassengerSeatsCount()).append(",");

        return sb.toString();
    }

    protected String fromToyotaLandCruiserToString(ToyotaLandCruiser toyotaLandCruiser) {
        StringBuilder sb = new StringBuilder();

        sb.append(toyotaLandCruiser.getCarModel()).append(",");
        sb.append(toyotaLandCruiser.getEngine()).append(",");
        sb.append(toyotaLandCruiser.getColor()).append(",");
        sb.append(toyotaLandCruiser.getNumber()).append(",");
        sb.append(toyotaLandCruiser.getMaxSpeed()).append(",");
        sb.append(toyotaLandCruiser.getYear()).append(",");
        sb.append(toyotaLandCruiser.isThereTopTrunk()).append(",");
        sb.append(toyotaLandCruiser.getClearanceLength()).append(",");
        sb.append(toyotaLandCruiser.isThereBackViewCamera()).append(",");

        return sb.toString();
    }

    protected String fromTeslaSemiToString(TeslaSemi teslaSemi) {
        StringBuilder sb = new StringBuilder();

        sb.append(teslaSemi.getCarModel()).append(",");
        sb.append(teslaSemi.getEngine()).append(",");
        sb.append(teslaSemi.getColor()).append(",");
        sb.append(teslaSemi.getNumber()).append(",");
        sb.append(teslaSemi.getMaxSpeed()).append(",");
        sb.append(teslaSemi.getYear()).append(",");
        sb.append(teslaSemi.getLiftingCapacity()).append(",");
        sb.append(teslaSemi.getBatteryPowerReserve()).append(",");

        return sb.toString();
    }

    protected Vehicle fromStringToVehicle(String line) {
        if (line.equals(""))
            return null;

        Vehicle vehicle = null;
        String[] vehicleToString = line.split(",");
        String carModel = vehicleToString[0];
        String[] newVehicleToString = Arrays.copyOfRange(vehicleToString, 1, vehicleToString.length);
        switch (carModel) {
            case "AudiA6":
                vehicle = fromStringToAudiA6(newVehicleToString);
                break;
            case "MercedesVito":
                vehicle = fromStringToMercedesVito(newVehicleToString);
                break;
            case "ToyotaLandCruiser":
                vehicle = fromStringToToyotaLandCruiser(newVehicleToString);
                break;
            case "TeslaSemi":
                vehicle = fromStringToTeslaSemi(newVehicleToString);
                break;
        }

        return vehicle;
    }

    protected AudiA6 fromStringToAudiA6(String[] audiA6ArrayValues) {
        AudiA6 audiA6 = new AudiA6();
        audiA6.setEngine(Vehicle.fromStringToEngine(audiA6ArrayValues[0]));
        audiA6.setColor(audiA6ArrayValues[1]);
        audiA6.setNumber(audiA6ArrayValues[2]);
        audiA6.setSalon(audiA6ArrayValues[6]);
        audiA6.setThereBackViewCamera(Boolean.parseBoolean(audiA6ArrayValues[7]));
        try {
            audiA6.setMaxSpeed(Integer.parseInt(audiA6ArrayValues[3]));
            audiA6.setYear(Integer.parseInt(audiA6ArrayValues[4]));
            audiA6.setWheelRadius(Integer.parseInt(audiA6ArrayValues[5]));
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException. Error adding Audi A6 to file.");
            return null;
        }

        return audiA6;
    }

    protected MercedesVito fromStringToMercedesVito(String[] mercedesVitoArrayValues) {
        MercedesVito mercedesVito = new MercedesVito();
        mercedesVito.setEngine(Vehicle.fromStringToEngine(mercedesVitoArrayValues[0]));
        mercedesVito.setColor(mercedesVitoArrayValues[1]);
        mercedesVito.setNumber(mercedesVitoArrayValues[2]);
        mercedesVito.setPassenger(Boolean.parseBoolean(mercedesVitoArrayValues[5]));
        mercedesVito.setThereBackWindows(Boolean.parseBoolean(mercedesVitoArrayValues[6]));
        try {
            mercedesVito.setMaxSpeed(Integer.parseInt(mercedesVitoArrayValues[3]));
            mercedesVito.setYear(Integer.parseInt(mercedesVitoArrayValues[4]));
            mercedesVito.setPassengerSeatsCount(Integer.parseInt(mercedesVitoArrayValues[7]));
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException. Error adding Mercedes Vito to file.");
            return null;
        }

        return mercedesVito;
    }

    protected ToyotaLandCruiser fromStringToToyotaLandCruiser(String[] toyotaLandCruiserArrayValues) {
        ToyotaLandCruiser toyotaLandCruiser = new ToyotaLandCruiser();
        toyotaLandCruiser.setEngine(Vehicle.fromStringToEngine(toyotaLandCruiserArrayValues[0]));
        toyotaLandCruiser.setColor(toyotaLandCruiserArrayValues[1]);
        toyotaLandCruiser.setNumber(toyotaLandCruiserArrayValues[2]);
        toyotaLandCruiser.setThereTopTrunk(Boolean.parseBoolean(toyotaLandCruiserArrayValues[5]));
        toyotaLandCruiser.setThereBackViewCamera(Boolean.parseBoolean(toyotaLandCruiserArrayValues[7]));
        try {
            toyotaLandCruiser.setMaxSpeed(Integer.parseInt(toyotaLandCruiserArrayValues[3]));
            toyotaLandCruiser.setYear(Integer.parseInt(toyotaLandCruiserArrayValues[4]));
            toyotaLandCruiser.setClearanceLength(Integer.parseInt(toyotaLandCruiserArrayValues[6]));
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException. Error adding Toyota Land Cruiser to file.");
            return null;
        }

        return toyotaLandCruiser;
    }

    protected TeslaSemi fromStringToTeslaSemi(String[] teslaSemiArrayValues) {
        TeslaSemi teslaSemi = new TeslaSemi();
        teslaSemi.setEngine(Vehicle.fromStringToEngine(teslaSemiArrayValues[0]));
        teslaSemi.setColor(teslaSemiArrayValues[1]);
        teslaSemi.setNumber(teslaSemiArrayValues[2]);
        try {
            teslaSemi.setMaxSpeed(Integer.parseInt(teslaSemiArrayValues[3]));
            teslaSemi.setYear(Integer.parseInt(teslaSemiArrayValues[4]));
            teslaSemi.setLiftingCapacity(Integer.parseInt(teslaSemiArrayValues[5]));
            teslaSemi.setBatteryPowerReserve(Integer.parseInt(teslaSemiArrayValues[6]));
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException. Error adding Tesla Semi to file.");
            return null;
        }

        return teslaSemi;
    }
}
