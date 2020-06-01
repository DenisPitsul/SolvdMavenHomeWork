package com.solvd.car.menu;

import com.solvd.car.odb.entity.*;
import com.solvd.car.place.GarageOfHome;
import com.solvd.car.place.Homes;
import org.apache.log4j.Logger;

import java.util.*;

public class HomesMenu {
    private static final Logger LOGGER = Logger.getLogger(HomesMenu.class);
    private static int houseNumber = (int) (Math.random()*200+1);

    private Address address;
    private GarageOfHome garageOfHome;

    private AddressMenu addressMenu;
    private GarageMenu garageMenu;

    private Scanner in;
    private MainMenu mainMenu;
    private String inputIndex;

    public HomesMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public GarageOfHome getGarageOfHome() {
        return garageOfHome;
    }

    public void setGarageOfHome(GarageOfHome garageOfHome) {
        this.garageOfHome = garageOfHome;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * if we have not opened AddressMenu instance yet then create and return
     * @return addressMenu instance
     */
    private AddressMenu getAddressMenuInstance() {
        if (addressMenu == null) {
            addressMenu = new AddressMenu(this);
        }
        return addressMenu;
    }

    /**
     * if we have not opened GarageMenu instance yet then create and return
     * @return garageMenu instance
     */
    private GarageMenu getGarageMenuInstance() {
        if (garageMenu == null) {
            garageMenu = new GarageMenu(mainMenu, this);
        }
        garageMenu.setCarInTheGarage(false);
        return garageMenu;
    }

    /**
     * Show all added car in current garage on the screen
     */
    public void showCarInTheGarage() {
        if (getGarageOfHome().getCarsInGarage() == null || getGarageOfHome().getCarsInGarage().size() == 0) {
            LOGGER.info("There is not any car in the garage.");
            inputHomesOperation();
            return;
        }
        garageOfHome.showInfo();
    }

    /**
     * Choose a home action.
     * 1 -> Create home
     * 2 -> delete home
     * 3 -> Show info about all homes.xml
     */
    public void inputHomesOperation() {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Create home input                  ->  1|");
                System.out.println("Delete home input                  ->  2|");
                System.out.println("Show info about all homes          ->  3|");

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        mainMenu.openMainMenu();
                        break;
                    case "1":
                        garageOfHome = new GarageOfHome();
                        openCreateHomeMenu();
                        break;
                    case "2":
                        openDeleteHomeMenu();
                        break;
                    case "3":
                        mainMenu.showAllHomes();
                        inputHomesOperation();
                        break;
                    default:
                        LOGGER.info("You have to input number from menu.");
                        inputHomesOperation();
                        break;
                }
                break;
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                inputHomesOperation();
            }
        }
    }

    /**
     * To create home to have to create address of home and add car to garage.
     * In this method we choose option to create the address of the home
     * Choose 1 to address of the home manually
     * Choose 2 to address of the home automatically
     * If we choose 2 address of the home will be created and will open garage menu
     */
    public void openCreateHomeMenu() {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Create address manually            ->  1|");
                System.out.println("Create address automatically       ->  2|");

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        inputHomesOperation();
                        break;
                    case "1":
                        openAddressMenu();
                        break;
                    case "2":
                        address = new Address();
                        address.setCity("Chernivtsy");
                        address.setStreet("Nebesnoy sotny");
                        address.setHouseNumber(houseNumber);

                        houseNumber++;
                        openGarageMenu();
                        break;
                    default:
                        LOGGER.info("You have to input correct number.");
                        openCreateHomeMenu();
                        break;
                }
                break;
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                openCreateHomeMenu();
            }
        }
    }

    /**
     * Show all homes on the screen and choose number of home to leave delete
     */
    public void openDeleteHomeMenu() {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input                -> -1|");
                System.out.println("Go back input                       -> -2|");
                System.out.println("-----------------------------------------|");
                System.out.println("Input number of home to delete           |");
                System.out.println("-----------------------------------------|");
                mainMenu.showAllHomes();

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        inputHomesOperation();
                        break;
                    default:
                        if (!inputIndex.equals("")) {
                            int homeIndex = Integer.parseInt(inputIndex);
                            if (homeIndex >= 0 && homeIndex < mainMenu.getHomesInstance().getHomes().size()) {

                                List<CarInGarage> carInGarageList = mainMenu.getHomesInstance()
                                        .getCarsInGarageByHomeIndex(homeIndex);
                                Address address = carInGarageList.get(0).getGarage().getHome().getAddress();
                                deleteHome(carInGarageList);
                                Homes homes = mainMenu.getAllHomesFromDatabase();
                                mainMenu.setHomesInstance(homes);
                                LOGGER.debug("Home with address: " + address + " deleted.");

                                inputHomesOperation();
                            }
                            else {
                                LOGGER.info("Such home does not exist.");
                                openDeleteHomeMenu();
                            }
                        }
                        else {
                            LOGGER.info("You have to input correct number.");
                            openDeleteHomeMenu();
                        }
                        break;
                }
                break;
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                openDeleteHomeMenu();
            }
        }
    }

    /**
     * Go to address menu
     */
    public void openAddressMenu() {
        AddressMenu addressMenu = getAddressMenuInstance();
        addressMenu.inputAddressManually(0, new Address());
    }

    /**
     * Go to garage menu
     */
    public void openGarageMenu() {
        GarageMenu garageMenu = getGarageMenuInstance();
        garageMenu.inputGarageOperation();
    }

    /**
     *  1 - Create address in database and get this(last) address from data base because we need id from address
     *  2 - Create home in database and get this home
     *  3 - Create garage in database and get this garage
     *  4 - Add cars to cars_in_garage in database with lastGarage
     */
    public void createHome() {
        // 1
        mainMenu.getAddressServiceInstance().addAddress(address);
        Address lastAddress = mainMenu.getAddressServiceInstance().getLastAddress();

        // 2
        Home home = new Home();
        home.setAddress(lastAddress);
        mainMenu.getHomeServiceInstance().addHome(home);
        Home lastHome = mainMenu.getHomeServiceInstance().getLastHome();

        // 3
        Garage garageInstance = new Garage();
        garageInstance.setBig(garageOfHome.isBig());
        garageInstance.setHome(lastHome);
        mainMenu.getGarageServiceInstance().addGarage(garageInstance);
        Garage lastGarage = mainMenu.getGarageServiceInstance().getLastGarage();

        // 4
        for (CarInGarage carInGarage: garageOfHome.getCarsInGarage()) {
            carInGarage.setGarage(lastGarage);
            mainMenu.getCarsInGarageServiceInstance().addCarInGarage(carInGarage);
        }
    }

    /**
     *
     * Delete all data related to the home in reverse order as we added this data to home
     *
     * 1 - get garage by first car in garage because garage has at least 1 car in garage and delete all cars in garage
     * 2 - get home by garage and delete this garage
     * 3 - get address bu home and delete this home
     * 4 - delete this address
     *
     * @param carInGarageList - cars in garage in current home
     */
    public void deleteHome(List<CarInGarage> carInGarageList) {
        // 1
        Garage garageFromDatabase = carInGarageList.get(0).getGarage();
        for (CarInGarage carInGarage: carInGarageList) {
            mainMenu.getCarsInGarageServiceInstance().deleteCarInGarage(carInGarage);
        }

        // 2
        Home home = garageFromDatabase.getHome();
        mainMenu.getGarageServiceInstance().deleteGarage(garageFromDatabase);

        // 3
        Address address = home.getAddress();
        mainMenu.getHomeServiceInstance().deleteHome(home);

        // 4
        mainMenu.getAddressServiceInstance().deleteAddress(address);
    }
}
