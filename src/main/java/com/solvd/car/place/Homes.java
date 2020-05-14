package com.solvd.car.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.car.vehicle.Vehicle;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Homes {
    private static final Logger LOGGER = Logger.getLogger(Homes.class);

    private int countOfCreatedHomes;
    private Map<Address, Garage<Vehicle>> homes;

    public Homes() {
        homes = new HashMap<>();
    }

    public Map<Address, Garage<Vehicle>> getHomes() {
        return homes;
    }

    public void setHomes(Map<Address, Garage<Vehicle>> homes) {
        this.homes = homes;
    }

    public int getCountOfCreatedHomes() {
        return countOfCreatedHomes;
    }

    /**
     * create home by address and garage and add to map
     * @param address key of the homes map
     * @param garage value of the homes map
     */
    public void addHome(Address address, Garage<Vehicle> garage) {
        homes.put(address, garage);
        countOfCreatedHomes++;
    }

    /**
     * Delete home from the map by address
     * @param address of the home which have to delete from the map
     * @return garage of deleted home or null if there weren't any homes.xml in the map with this addres
     */
    public Garage<Vehicle> deleteHome(Address address) {
        if (homes.get(address) == null) {
            LOGGER.info("Car with this index does not exist.");
            return null;
        }
        LOGGER.info("Removed home from this address: " + address);
        countOfCreatedHomes--;
        return homes.remove(address);
    }

    /**
     * Delete home from the map by index
     * @param index -> index of home which have to delete from the map
     */
    public void deleteHome(int index) {
        if (homes.size() == 0) {
            LOGGER.info("There is not any homes.");
        }

        if (index >= 0 && index < homes.size()) {
            int i = 0;
            boolean isHomeRemoved = false;
            for (Address address : homes.keySet()) {
                if (i == index) {
                    homes.remove(address);
                    countOfCreatedHomes--;
                    isHomeRemoved = true;
                    LOGGER.info("Removed home from this address: " + address);
                    break;
                }
                i++;
            }
            if (!isHomeRemoved) {
                LOGGER.info("Car with this index does not exist.");
            }
        }
    }

    /**
     * Show all homes on the screen
     */
    public void showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Homes {").append(System.lineSeparator());
        sb.append("\tCount of created homes: ").append(countOfCreatedHomes).append(System.lineSeparator());
        int i = 0;
        for (Address address : homes.keySet()) {
            sb.append("\tHome #").append(i).append(" {").append(System.lineSeparator());
            sb.append("\t\tAddress: ").append(address).append(System.lineSeparator());
            sb.append(homes.get(address)).append(System.lineSeparator());
            sb.append("\t}").append(System.lineSeparator());
            i++;
        }
        sb.append("}");
        LOGGER.debug(sb.toString());
    }
}
