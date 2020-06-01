package com.solvd.car.menu;

import com.solvd.car.exception.TruckInGarageException;
import com.solvd.car.odb.entity.Car;
import com.solvd.car.odb.entity.CarInGarage;
import com.solvd.car.place.Homes;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GarageMenu {
    private static final Logger LOGGER = Logger.getLogger(GarageMenu.class);

    private Scanner in;
    private MainMenu mainMenu;
    private HomesMenu homesMenu;
    private String inputIndex;

    private boolean isCarInTheGarage = false;

    public GarageMenu(MainMenu mainMenu, HomesMenu homesMenu) {
        this.mainMenu = mainMenu;
        this.homesMenu = homesMenu;
    }

    public boolean isCarInTheGarage() {
        return isCarInTheGarage;
    }

    public void setCarInTheGarage(boolean carInTheGarage) {
        isCarInTheGarage = carInTheGarage;
    }

    /**
     * Choose a parking action.
     * 1 -> Add car to garage
     * 2 -> Show all cars in the garage
     * 3 -> create home if there is at least 1 car in garage
     */
    public void inputGarageOperation () {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input                       -> -1|");
                System.out.println("Go back input(inputted address won't save) -> -2|");
                System.out.println("------------------------------------------------|");
                System.out.println("Add car to garage input                    ->  1|");
                System.out.println("Show all cars in the garage input          ->  2|");
                System.out.println("Create home with address and garage        ->  3|");

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        homesMenu.openCreateHomeMenu();
                        break;
                    case "1":
                        openAddCarToGarageMenu();
                        break;
                    case "2":
                        homesMenu.showCarInTheGarage();
                        inputGarageOperation();
                        break;
                    case "3":
                        if (isCarInTheGarage) {
                            homesMenu.createHome();
                            Homes homes = mainMenu.getAllHomesFromDatabase();
                            mainMenu.setHomesInstance(homes);
                            LOGGER.info("Home created.");
                            homesMenu.inputHomesOperation();
                        }
                        else {
                            LOGGER.info("To create the home you have to add at least 1 car to garage.");
                            inputGarageOperation();
                        }
                        break;
                    default:
                        LOGGER.info("You have to input number from menu.");
                        inputGarageOperation();
                        break;
                }
                break;
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                inputGarageOperation();
            }
        }
    }

    /**
     * Input 1 to create big garage or 2 to create small garage.
     * After that show all cars on the screen and choose number of car to add to the garage
     */
    private void openAddCarToGarageMenu() {
        inputIndex = "";
        /*
         *  number of iteration in method
         *  0 -> input isBig garage
         *  1 -> add cars to garage
         */
        int propertyNumber = 0;
        while(true) {
            try {
                in = new Scanner(System.in);

                if (homesMenu.getGarageOfHome().isBigGarageValueSetted()) {
                    propertyNumber = 1;
                }

                if(propertyNumber == 0) {
                    System.out.println("Finish program input                       -> -1|");
                    System.out.println("Go back input                              -> -2|");
                    System.out.println("------------------------------------------------|");
                    System.out.println("Create big garage input                    ->  1|");
                    System.out.println("Create small garage input                  ->  2|");

                    inputIndex = in.nextLine();

                    switch (inputIndex) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputGarageOperation();
                            break;
                        case "1":
                            homesMenu.getGarageOfHome().setBig(true);
                            propertyNumber++;
                            break;
                        case "2":
                            homesMenu.getGarageOfHome().setBig(false);
                            propertyNumber++;
                            break;
                        default:
                            LOGGER.info("You have to input number from menu.");
                            openAddCarToGarageMenu();
                            break;
                    }
                    break;
                }
                else {
                    System.out.println("Finish program input               -> -1|");
                    System.out.println("Go back input                      -> -2|");
                    System.out.println("----------------------------------------|");
                    System.out.println("Input number of car to add to garage    |");
                    System.out.println("----------------------------------------|");
                    homesMenu.getMainMenu().showAllCars();

                    inputIndex = in.nextLine();

                    switch (inputIndex) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputGarageOperation();
                            break;
                        default:
                            if (!inputIndex.equals("")) {
                                int carIndex = Integer.parseInt(inputIndex);
                                if (carIndex >= 0 && carIndex < homesMenu.getMainMenu().getCarListInstance().size()) {
                                    Car car = homesMenu.getMainMenu().getCarListInstance().get(carIndex);
                                    if (car.getModel().equals("Tesla Semi") && !homesMenu.getGarageOfHome().isBig()) {
                                        throw new TruckInGarageException();
                                    }
                                    else {
                                        CarInGarage carInGarage = new CarInGarage();
                                        carInGarage.setCar(car);
                                        homesMenu.getGarageOfHome().add(carInGarage);
                                        isCarInTheGarage = true;
                                        LOGGER.debug("Car " + car.getShortInfo() + " added to garage.");
                                        inputGarageOperation();
                                    }
                                }
                                else {
                                    LOGGER.info("Such car does not exist.");
                                    openAddCarToGarageMenu();
                                }
                            }
                            else {
                                LOGGER.info("You have to input correct number.");
                                openAddCarToGarageMenu();
                            }
                            break;
                    }
                }
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } catch (TruckInGarageException e) {
                LOGGER.error(e.getMessage());
            } finally {
                openAddCarToGarageMenu();
            }
        }
    }

}
