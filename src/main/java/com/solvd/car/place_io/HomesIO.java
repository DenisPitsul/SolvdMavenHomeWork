package com.solvd.car.place_io;

import com.solvd.car.place.*;
import com.solvd.car.utils.json.*;
import com.solvd.car.utils.json.model.HomeReadPOJO;
import com.solvd.car.utils.json.model.HomeWritePOJO;
import com.solvd.car.utils.json.model.HomesReadPOJO;
import com.solvd.car.utils.json.model.HomesWritePOJO;
import com.solvd.car.utils.text_file.FileHelper;
import com.solvd.car.utils.text_file.FileIO;
import com.solvd.car.place_io.helper.FileOperation;
import com.solvd.car.place_io.helper.JsonOperation;
import com.solvd.car.vehicle.Vehicle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomesIO implements FileOperation<Homes>, JsonOperation<Homes> {
    public static final String HOMES_FILE_PATH = "src"
            + FileHelper.SEPARATOR + "main"
            + FileHelper.SEPARATOR + "resources"
            + FileHelper.SEPARATOR + "data"
            + FileHelper.SEPARATOR + "text"
            + FileHelper.SEPARATOR + "homes.txt";
    public static final String HOMES_JSON_FILE_PATH = "src"
            + FileHelper.SEPARATOR + "main"
            + FileHelper.SEPARATOR + "resources"
            + FileHelper.SEPARATOR + "data"
            + FileHelper.SEPARATOR + "json"
            + FileHelper.SEPARATOR + "homes.json";

    private File file;
    private AddressIO addressIO;
    private GarageIO garageIO;

    public HomesIO(String filePath) {
        this.file = new File(filePath);
        this.addressIO = new AddressIO();
        this.garageIO = new GarageIO(filePath);
    }

    public File getFile() {
        return file;
    }

    public String getFilePath() {
        return file.getPath();
    }

    @Override
    public void writeToJsonFile(Homes homes, String filePath) {
        List<HomeWritePOJO> homeWritePOJOList = new ArrayList<>();
        for (Address address: homes.getHomes().keySet()) {
            HomeWritePOJO homeWritePOJO = new HomeWritePOJO();
            homeWritePOJO.setAddress(address);
            homeWritePOJO.setGarage(homes.getHomes().get(address));

            homeWritePOJOList.add(homeWritePOJO);
        }
        HomesWritePOJO homesWritePOJO = new HomesWritePOJO();
        homesWritePOJO.setCountOfCreatedHomes(homes.getCountOfCreatedHomes());
        homesWritePOJO.setHomes(homeWritePOJOList);

        FileIO.createFileIfItDoesNotExist(filePath);
        JsonConverter.convertJavaToJsonFile(homesWritePOJO, filePath);
    }

    @Override
    public Homes readJsonFile(String filePath) {
        HomesReadPOJO homesReadPOJO = JsonConverter.convertJsonFileToHomesPOJO(filePath);
        Homes homes = new Homes();
        for (HomeReadPOJO homeReadPOJO: homesReadPOJO.getHomes()) {
            homes.addHome(homeReadPOJO.getAddress(), Garage.getGarageFromGaragePOJO(homeReadPOJO.getGarage()));
        }
        return homes;
    }

    public void writeToFile(Map.Entry<Address, Garage<Vehicle>> home) {
        StringBuilder sb = new StringBuilder();

        if (home != null) {
            sb.append(fromHomeToString(home));
        }
        sb.append("-").append(System.lineSeparator());

        FileIO.writeToFile(getFilePath(), sb.toString());
    }

    @Override
    public void writeAllToFile(Homes homes) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Address, Garage<Vehicle>> home: homes.getHomes().entrySet()) {
            if (home != null) {
                sb.append(fromHomeToString(home));
            }
            sb.append("-").append(System.lineSeparator());
        }

        FileIO.writeToFile(getFilePath(), sb.toString());
    }

    public String fromHomeToString(Map.Entry<Address, Garage<Vehicle>> home) {
        StringBuilder sb = new StringBuilder();

        sb.append("Address").append(System.lineSeparator());
        sb.append(addressIO.fromAddressToString(home.getKey())).append(System.lineSeparator());
        String bigField = home.getValue().isBig() ? "(big)" : "(small)";
        sb.append("Garage").append(bigField).append(System.lineSeparator());
        for (Vehicle vehicle: home.getValue().getCarsInGarage()) {
            sb.append(garageIO.fromVehicleToString(vehicle)).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public Homes readAllFromFile() {
        Homes homes = new Homes();
        Address address = null;
        Garage<Vehicle> garage = null;
        String stringFromFile = FileIO.readFromFile(getFilePath());
        String[] lines = stringFromFile.split(System.getProperty("line.separator"));
        /*
         * set 1 to statusFlag if line = Address, after that initialize address
         * set 2 to statusFlag if line = Garage, after that create add cars from file to the garage
         * set 0 to reset statusFlag
         */
        int lineType = -1;
        for (String line: lines) {
            switch (line) {
                case "-":
                    homes.addHome(address, garage);
                    lineType = 0;
                    break;
                case "Address":
                    lineType = 1;
                    address = null;
                    continue;
                default:
                    if (line.startsWith("Garage")) {
                        lineType = 2;
                        garage = new Garage<>();
                        garage.setBig(line.endsWith("(big)"));
                        continue;
                    }
            }
            if (lineType == 1) {
                address = addressIO.fromStringToAddress(line);
                lineType = 0;
            }
            else if (lineType == 2) {
                garage.add(garageIO.fromStringToVehicle(line));
            }
        }

        return homes;
    }

    @Override
    public void clearFile() {
        FileIO.clearFile(getFilePath());
    }

}