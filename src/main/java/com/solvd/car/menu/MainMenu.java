package com.solvd.car.menu;
import com.solvd.car.place.*;
import com.solvd.car.place_io.AdminInfoIO;
import com.solvd.car.place_io.CarDealershipIO;
import com.solvd.car.place_io.HomesIO;
import com.solvd.car.place_io.ParkingIO;
import com.solvd.car.vehicle.Vehicle;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final Logger LOGGER = Logger.getLogger(MainMenu.class);

    private List<Vehicle> carList;
    private Parking<Vehicle> parking;
    private CarDealership<Vehicle> carDealership;
    private Homes homes;

    private CarMenu carMenu;
    private ParkingMenu parkingMenu;
    private CarDealershipMenu carDealershipMenu;
    private HomesMenu homesMenu;
    private AdminInfoMenu adminInfoMenu;

    private ParkingIO parkingIO;
    private CarDealershipIO carDealershipIO;
    private HomesIO homesIO;
    private AdminInfoIO adminInfoIO;

    private Scanner in;
    private String inputIndex;

    public MainMenu() {
        parkingIO = new ParkingIO(ParkingIO.PARKING_FILE_PATH);
        carDealershipIO = new CarDealershipIO(CarDealershipIO.CAR_DEALERSHIP_FILE_PATH);
        homesIO = new HomesIO(HomesIO.HOMES_FILE_PATH);
        adminInfoIO = new AdminInfoIO(AdminInfoIO.ADMIN_INFO_FILE_PATH);
        parking = parkingIO.readAllFromFile();
        carDealership = carDealershipIO.readAllFromFile();
        homes = homesIO.readAllFromFile();
    }

    /**
     * If we have not opened CarMenu instance yet then create and return
     * @return carMenu instance
     */
    private CarMenu getCarMenuInstance() {
        if (carMenu == null) {
            carMenu = new CarMenu(this);
        }
        return carMenu;
    }

    /**
     * If we have not opened ParkingMenu instance yet then create and return
     * @return parkingMenu instance
     */
    private ParkingMenu getParkingMenuInstance() {
        if (parkingMenu == null) {
            parkingMenu = new ParkingMenu(this);
        }
        return parkingMenu;
    }

    /**
     * If we have not opened CarDealershipMenu instance yet then create and return
     * @return carDealershipMenu instance
     */
    private CarDealershipMenu getCarDealershipMenuInstance() {
        if (carDealershipMenu == null) {
            carDealershipMenu = new CarDealershipMenu(this);
        }
        return carDealershipMenu;
    }

    /**
     * If we have not opened HomesMenu instance yet then create and return
     * @return homesMenu instance
     */
    private HomesMenu getHomesMenuInstance() {
        if (homesMenu == null) {
            homesMenu = new HomesMenu(this);
        }
        return homesMenu;
    }

    /**
     * If we have not opened AdminInfoMenu instance yet then create and return
     * @return homesMenu instance
     */
    private AdminInfoMenu getAdminInfoMenu() {
        if (adminInfoMenu == null) {
            adminInfoMenu = new AdminInfoMenu(this);
        }
        return adminInfoMenu;
    }

    /**
     * If we have not list of car instance yet then create and return
     * @return list of car instance
     */
    public List<Vehicle> getCarListInstance() {
        if (carList == null) {
            carList = new LinkedList<>();
        }
        return carList;
    }

    /**
     * If we have not parking instance yet then create and return
     * @return parking instance
     */
    public Parking<Vehicle> getParkingInstance() {
        if (parking == null) {
            parking = new Parking<>();
        }
        return parking;
    }

    /**
     * If we have not car dealership instance yet then create and return
     * @return car dealership instance
     */
    public CarDealership<Vehicle> getCarDealershipInstance() {
        if (carDealership == null) {
            carDealership = new CarDealership<>();
        }
        return carDealership;
    }

    /**
     * If we have not homes.xml instance yet then create and return
     * @return homes instance
     */
    public Homes getHomesInstance() {
        if (homes == null) {
            homes = new Homes();
        }
        return homes;
    }

    /**
     * @return parkingDAO instance
     */
    public ParkingIO getParkingIO() {
        return parkingIO;
    }

    /**
     * @return carDealershipDAO instance
     */
    public CarDealershipIO getCarDealershipIO() {
        return carDealershipIO;
    }

    /**
     * @return homesDAO instance
     */
    public HomesIO getHomesIO() {
        return homesIO;
    }

    /**
     * @return adminInfoDAO instance
     */
    public AdminInfoIO getAdminInfoDAO() {
        return adminInfoIO;
    }

    /**
     * Show all created car on the screen
     */
    public void showAllCars() {
        if (carList == null || carList.size() == 0) {
            LOGGER.info("There is not any car.");
            openMainMenu();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("All cars {").append(System.lineSeparator());
        int i = 0;
        for (Vehicle car : carList) {
            sb.append("\tCar #").append(i).append(": ").append(car.getShortInfo()).append(System.lineSeparator());
            i++;
        }
        sb.append('}');
        LOGGER.debug(sb.toString());
    }

    /**
     * Show all parked car on the screen
     */
    public void showCarsOnTheParking() {
        if (getParkingInstance().getParkedCars() == null || getParkingInstance().getParkedCars().size() == 0) {
            LOGGER.info("There is not any car on the parking.");
            openParkingMenu();
            return;
        }
        parking.showInfo();
    }

    /**
     * Show all car in the car dealership on the screen
     */
    public void showCarsInTheCarDealership() {
        if (getCarDealershipInstance().getSellingCars() == null || getCarDealershipInstance().getSellingCars().size() == 0) {
            LOGGER.info("There is not any car in the car dealership.");
            openCarDealership();
            return;
        }
        carDealership.showInfo();
    }

    /**
     * Show homes with addresses and garages on the screen
     */
    public void showAllHomes() {
        if (getHomesInstance().getHomes() == null || getHomesInstance().getHomes().size() == 0) {
            LOGGER.info("There is not any homes.");
            openHomeMenu();
            return;
        }
        homes.showInfo();
    }

    /**
     * open main menu and choose option to do some operation
     * 1 -> choose car menu
     * 2 -> choose parking menu
     * 3 -> choose car dealership menu
     * 4 -> choose home menu
     */
    public void openMainMenu() {
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("-------------M A I N  P A G E------------");
                System.out.println("Finish program input               -> -1|");
                System.out.println("----------------------------------------|");
                System.out.println("Car menu input                     ->  1|");
                System.out.println("Parking menu input                 ->  2|");
                System.out.println("Car dealership menu input          ->  3|");
                System.out.println("Home menu input                    ->  4|");
                System.out.println("Admin information menu input       ->  5|");

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "1":
                        openCarMenu();
                        break;
                    case "2":
                        openParkingMenu();
                        break;
                    case "3":
                        openCarDealership();
                        break;
                    case "4":
                        openHomeMenu();
                        break;
                    case "5":
                        openAdminInfoMenu();
                        break;
                    default:
                        LOGGER.info("You have to input number from menu.");
                        openMainMenu();
                        break;
                }
                break;
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                openMainMenu();
            }
        }
    }

    /**
     * Go to car menu
     */
    private void openCarMenu() {
        CarMenu carMenu = getCarMenuInstance();
        carMenu.inputCarType();
    }

    /**
     * Go to parking menu
     */
    private void openParkingMenu() {
        ParkingMenu parkingMenu = getParkingMenuInstance();
        parkingMenu.inputParkingOperation();
    }

    /**
     * Go to car dealership menu
     */
    private void openCarDealership() {
        CarDealershipMenu carDealershipMenu = getCarDealershipMenuInstance();
        carDealershipMenu.inputCarDealershipOperation();
    }

    /**
     * Go to homes.xml menu
     */
    private void openHomeMenu() {
        HomesMenu homesMenu = getHomesMenuInstance();
        homesMenu.inputHomesOperation();
    }

    /**
     * Go to admin info menu
     */
    private void openAdminInfoMenu() {
        AdminInfoMenu adminInfoMenu = getAdminInfoMenu();
        adminInfoMenu.inputAdminInfoOperation();
    }

}
