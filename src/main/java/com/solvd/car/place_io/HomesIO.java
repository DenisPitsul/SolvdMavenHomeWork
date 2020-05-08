package com.solvd.car.place_io;

import com.solvd.car.place.Address;
import com.solvd.car.place.Garage;
import com.solvd.car.place.Homes;
import com.solvd.car.utils.FileHelper;
import com.solvd.car.utils.FileIO;
import com.solvd.car.vehicle.Vehicle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class HomesIO {
    public static final String HOMES_FILE_PATH = "src" + FileHelper.SEPARATOR + "main" + FileHelper.SEPARATOR + "resources"
            + FileHelper.SEPARATOR + "data" + FileHelper.SEPARATOR + "text_files" + FileHelper.SEPARATOR + "homes.txt";

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

    public void writeStringToFile(String str) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(Map.Entry<Address, Garage<Vehicle>> home) {
        StringBuilder sb = new StringBuilder();

        if (home != null) {
            sb.append(fromHomeToString(home));
        }
        sb.append("-").append(System.lineSeparator());

        FileIO.writeToFile(getFilePath(), sb.toString());
    }

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

    public void clearFile() {
        FileIO.clearFile(getFilePath());
    }
}