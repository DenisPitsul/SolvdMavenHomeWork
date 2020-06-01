package com.solvd.car.menu;

import com.solvd.car.exception.TruckOnParkingException;
import com.solvd.car.odb.entity.Car;
import com.solvd.car.odb.entity.ParkedCar;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ParkingMenu {
    private static final Logger LOGGER = Logger.getLogger(ParkingMenu.class);

    private Scanner in;
    private MainMenu mainMenu;
    private String inputIndex;

    public ParkingMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * Choose a parking action.
     * 1 -> Add car to parking
     * 2 -> Leave car from the parking
     * 3 -> Show all cars on the parking
     */
    public void inputParkingOperation() {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Park Car                           ->  1|");
                System.out.println("Leave the Parking input            ->  2|");
                System.out.println("Show all cars on the parking input ->  3|");

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        mainMenu.openMainMenu();
                        break;
                    case "1":
                        openParkCarMenu();
                        break;
                    case "2":
                        openLeaveTheParkingMenu();
                        break;
                    case "3":
                        mainMenu.showCarsOnTheParking();
                        inputParkingOperation();
                        break;
                    default:
                        LOGGER.info("You have to input number from menu.");
                        inputParkingOperation();
                        break;
                }
                break;
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                inputParkingOperation();
            }
        }
    }

    /**
     * Show all cars on the screen and choose number of car to add to the parking
     */
    private void openParkCarMenu() {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Input number of car to park             |");
                System.out.println("----------------------------------------|");
                mainMenu.showAllCars();

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        inputParkingOperation();
                        break;
                    default:
                        if (!inputIndex.equals("")) {
                            int carIndex = Integer.parseInt(inputIndex);
                            if (carIndex >= 0 && carIndex < mainMenu.getCarListInstance().size()) {
                                Car car = mainMenu.getCarListInstance().get(carIndex);
                                if (car.getModel().equals("Tesla Semi")) {
                                    throw new TruckOnParkingException();
                                }
                                else {
                                    if (isCarAlreadyOnTheParking(car)) {
                                        LOGGER.info(car.getShortInfo() + " is already on the parking.");
                                        openParkCarMenu();
                                    } else {
                                        ParkedCar parkedCar = new ParkedCar();
                                        parkedCar.setCar(car);
                                        mainMenu.getParkingServiceInstance().addParkedCar(parkedCar);
                                        List<ParkedCar> parkedCarList = mainMenu.getParkingServiceInstance().getAllParkedCars();
                                        mainMenu.setParkingInstance(parkedCarList);
                                        LOGGER.debug("Car " + car.getShortInfo() + " has parked.");
                                        inputParkingOperation();
                                    }
                                }

                            }
                            else {
                                LOGGER.info("Such car does not exist.");
                                openParkCarMenu();
                            }
                        }
                        else {
                            LOGGER.info("You have to input correct number.");
                            openParkCarMenu();
                        }
                        break;
                }
                break;
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } catch (TruckOnParkingException e) {
                LOGGER.error(e.getMessage());
            } finally {
                inputParkingOperation();
            }
        }
    }

    private boolean isCarAlreadyOnTheParking(Car car) {
        boolean isCarOnTheParking = false;
        for (ParkedCar parkedCar: mainMenu.getParkingInstance().getParkedCars()) {
            if (car.getId().equals(parkedCar.getCar().getId())) {
                isCarOnTheParking = true;
                break;
            }
        }
        return isCarOnTheParking;
    }

    /**
     * Show all cars on the screen on the parking and choose number of car to leave the parking
     */
    private void openLeaveTheParkingMenu() {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input                -> -1|");
                System.out.println("Go back input                       -> -2|");
                System.out.println("-----------------------------------------|");
                System.out.println("Input number of car to leave the parking |");
                System.out.println("-----------------------------------------|");
                mainMenu.showCarsOnTheParking();

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        inputParkingOperation();
                        break;
                    default:
                        if (!inputIndex.equals("")) {
                            int carIndex = Integer.parseInt(inputIndex);
                            if (carIndex >= 0 && carIndex < mainMenu.getParkingInstance().getParkedCars().size()) {
                                ParkedCar parkedCar = mainMenu.getParkingInstance().getParkedCars().get(carIndex);
                                mainMenu.getParkingServiceInstance().deleteParkedCar(parkedCar);
                                List<ParkedCar> parkedCarList = mainMenu.getParkingServiceInstance().getAllParkedCars();
                                mainMenu.setParkingInstance(parkedCarList);

                                LOGGER.debug("Car " + parkedCar.getCar().getShortInfo() + " left the parking.");
                                inputParkingOperation();
                            }
                            else {
                                LOGGER.info("Such car does not exist on the parking.");
                                openLeaveTheParkingMenu();
                            }
                        }
                        else {
                            LOGGER.info("You have to input correct number.");
                            openLeaveTheParkingMenu();
                        }
                        break;
                }
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                openLeaveTheParkingMenu();
            }
        }
    }

}
