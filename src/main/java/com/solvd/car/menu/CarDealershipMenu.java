package com.solvd.car.menu;

import com.solvd.car.odb.entity.Car;
import com.solvd.car.odb.entity.SellingCar;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CarDealershipMenu {
    private static final Logger LOGGER = Logger.getLogger(CarDealershipMenu.class);

    private Scanner in;
    private MainMenu mainMenu;
    private String inputIndex;

    public CarDealershipMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * Choose a car dealership action.
     * 1 -> Add car to car dealership
     * 2 -> Leave car from the car dealership
     * 3 -> Show all cars on the car dealership
     */
    public void inputCarDealershipOperation() {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input                                 -> -1|");
                System.out.println("Go back input                                        -> -2|");
                System.out.println("----------------------------------------------------------|");
                System.out.println("Add Car to car dealership                            ->  1|");
                System.out.println("Leave the car dealership input                       ->  2|");
                System.out.println("Show all cars in the car dealership input            ->  3|");

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        mainMenu.openMainMenu();
                        break;
                    case "1":
                        openAddCarDealershipMenu();
                        break;
                    case "2":
                        openLeaveCarFromTheCarDealershipMenu();
                        break;
                    case "3":
                        mainMenu.showCarsInTheCarDealership();
                        inputCarDealershipOperation();
                        break;
                    default:
                        LOGGER.info("You have to input number from menu.");
                        inputCarDealershipOperation();
                        break;
                }
                break;
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                inputCarDealershipOperation();
            }
        }
    }

    /**
     * Show all cars on the screen and choose number of car to add to the car dealership
     */
    private void openAddCarDealershipMenu() {
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
                        inputCarDealershipOperation();
                        break;
                    default:
                        if (!inputIndex.equals("")) {
                            int carIndex = Integer.parseInt(inputIndex);
                            if (carIndex >= 0 && carIndex < mainMenu.getCarListInstance().size()) {
                                Car car = mainMenu.getCarListInstance().get(carIndex);

                                if (isCarAlreadyInTheCarDealership(car)) {
                                    LOGGER.info(car.getShortInfo() + " is already in the car dealership.");
                                    openAddCarDealershipMenu();
                                } else {
                                    SellingCar sellingCar = new SellingCar();
                                    sellingCar.setCar(car);
                                    mainMenu.getCarDealershipServiceInstance().addSellingCar(sellingCar);
                                    List<SellingCar> sellingCarList = mainMenu.getCarDealershipServiceInstance().getAllSellingCars();
                                    mainMenu.setCarDealershipInstance(sellingCarList);
                                    LOGGER.debug(car.getShortInfo() + " added to car dealership.");
                                    inputCarDealershipOperation();
                                }
                            }
                            else {
                                LOGGER.info("Such car does not exist.");
                                openAddCarDealershipMenu();
                            }
                            break;
                        }
                        else {
                            LOGGER.info("You have to input correct number.");
                            openAddCarDealershipMenu();
                        }
                        break;
                }
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                openAddCarDealershipMenu();
            }
        }
    }

    private boolean isCarAlreadyInTheCarDealership(Car car) {
        boolean isCarInTheCarDealership = false;
        for (SellingCar sellingCar: mainMenu.getCarDealershipInstance().getSellingCars()) {
            if (car.getId().equals(sellingCar.getCar().getId())) {
                isCarInTheCarDealership = true;
                break;
            }
        }
        return isCarInTheCarDealership;
    }

    /**
     * Show all cars on the screen in the car dealership and choose number of car to leave the car dealership
     */
    private void openLeaveCarFromTheCarDealershipMenu() {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input                -> -1|");
                System.out.println("Go back input                       -> -2|");
                System.out.println("-----------------------------------------|");
                System.out.println("Input number of car to leave the parking |");
                System.out.println("-----------------------------------------|");
                mainMenu.showCarsInTheCarDealership();

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        inputCarDealershipOperation();
                        break;
                    default:
                        if (!inputIndex.equals("") && inputIndex.matches("^([1-9][0-9]*|[0])$")) {
                            int carIndex = Integer.parseInt(inputIndex);
                            if (carIndex >= 0 && carIndex < mainMenu.getCarDealershipInstance().getSellingCars().size()) {
                                Car car = mainMenu.getCarListInstance().get(carIndex);
                                SellingCar sellingCar = mainMenu.getCarDealershipInstance().getSellingCars().get(carIndex);
                                mainMenu.getCarDealershipServiceInstance().deleteSellingCar(sellingCar);
                                List<SellingCar> sellingCarList = mainMenu.getCarDealershipServiceInstance().getAllSellingCars();
                                mainMenu.setCarDealershipInstance(sellingCarList);

                                LOGGER.debug(car.getShortInfo() + " left the car dealership.");
                                inputCarDealershipOperation();
                            }
                            else {
                                LOGGER.info("Such car does not exist in the car dealership.");
                                openLeaveCarFromTheCarDealershipMenu();
                            }
                        }
                        else {
                            LOGGER.info("You have to input correct number.");
                            openLeaveCarFromTheCarDealershipMenu();
                        }
                        break;
                }
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                openLeaveCarFromTheCarDealershipMenu();
            }
        }
    }

}
