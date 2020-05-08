package com.solvd.car.place_io;

import com.solvd.car.place.Address;
import org.apache.log4j.Logger;

public class AddressIO {
    private static final Logger LOGGER = Logger.getLogger(AddressIO.class);

    public AddressIO() { }

    public String fromAddressToString(Address address) {
        StringBuilder sb = new StringBuilder();

        sb.append(address.getCity()).append(",");
        if (address.getDistrict() != null)
            sb.append(address.getDistrict()).append(",");
        if (address.getVillage() != null)
            sb.append(address.getVillage()).append(",");
        sb.append(address.getStreet()).append(",");
        sb.append(address.getHouseAddress()).append(",");

        return sb.toString();
    }

    public Address fromStringToAddress(String line) {
        if (line.equals("")) {
            LOGGER.info("Line is empty.");
            return null;
        }

        String[] addressToString = line.split(",");

        Address.Builder address = new Address.Builder();
        /*
         *  if addressToString.length = 5 then address has from Village
         *  if addressToString.length = 4 then address has from District
         *  if addressToString.length = 3 then address has from City
         */
        switch (addressToString.length) {
            case 5:
                address.setCity(addressToString[0]);
                address.setDistrict(addressToString[1]);
                address.setVillage(addressToString[2]);
                address.setStreet(addressToString[3]);
                try {
                    address.setHouseNumber(Integer.parseInt(addressToString[4]));
                } catch (NumberFormatException e) {
                    LOGGER.error(e);
                    LOGGER.error("Error convert line to address.");
                    return null;
                }
                break;
            case 4:
                address.setCity(addressToString[0]);
                address.setDistrict(addressToString[1]);
                address.setStreet(addressToString[2]);
                try {
                    address.setHouseNumber(Integer.parseInt(addressToString[3]));
                } catch (NumberFormatException e) {
                    LOGGER.error(e);
                    LOGGER.error("Error convert line to address.");
                    return null;
                }
                break;
            case 3:
                address.setCity(addressToString[0]);
                address.setStreet(addressToString[1]);
                try {
                    address.setHouseNumber(Integer.parseInt(addressToString[2]));
                } catch (NumberFormatException e) {
                    LOGGER.error(e);
                    LOGGER.error("Error convert line to address.");
                    return null;
                }
                break;
        }

        return address.build();
    }

}
