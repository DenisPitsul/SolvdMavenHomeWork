package com.solvd.car.menu;

import com.solvd.car.odb.entity.*;
import com.solvd.car.odb.service.*;
import com.solvd.car.place.*;
import com.solvd.car.place.GarageOfHome;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final Logger LOGGER = Logger.getLogger(MainMenu.class);

    private List<Car> carList;
    private Parking parking;
    private CarDealership carDealership;
    private Homes homes;

    private CarMenu carMenu;
    private ParkingMenu parkingMenu;
    private CarDealershipMenu carDealershipMenu;
    private HomesMenu homesMenu;

    private CarService carService;
    private ParkingService parkingService;
    private CarDealershipService carDealershipService;

    private AddressService addressService;
    private HomeService homeService;
    private GarageService garageService;
    private CarsInGarageService carsInGarageService;

    private Scanner in;
    private String inputIndex;

    public MainMenu() {
        this.carService = getCarServiceInstance();
        this.carList = carService.getAllCars();

        this.parkingService = getParkingServiceInstance();
        this.parking = new Parking();
        this.parking.setParkedCars(parkingService.getAllParkedCars());

        this.carDealershipService = getCarDealershipServiceInstance();
        this.carDealership = new CarDealership();
        this.carDealership.setSellingCars(carDealershipService.getAllSellingCars());

        this.addressService = getAddressServiceInstance();
        this.homeService = getHomeServiceInstance();
        this.garageService = getGarageServiceInstance();
        this.carsInGarageService = getCarsInGarageServiceInstance();

        this.homes = getAllHomesFromDatabase();
    }

    /**
     * If we have not initialize CarService instance yet then init and return
     * @return CarService instance
     */
    public CarService getCarServiceInstance() {
        if (carService == null) {
            this.carService = new CarService();
        }
        return carService;
    }

    /**
     * If we have not initialize ParkingService instance yet then init and return
     * @return ParkingService instance
     */
    public ParkingService getParkingServiceInstance() {
        if (parkingService == null) {
            this.parkingService = new ParkingService();
        }
        return parkingService;
    }

    /**
     * If we have not initialize CarDealershipService instance yet then init and return
     * @return CarDealershipService instance
     */
    public CarDealershipService getCarDealershipServiceInstance() {
        if (carDealershipService == null) {
            this.carDealershipService = new CarDealershipService();
        }
        return carDealershipService;
    }

    /**
     * If we have not initialize AddressService instance yet then init and return
     * @return AddressService instance
     */
    public AddressService getAddressServiceInstance() {
        if (addressService == null) {
            this.addressService = new AddressService();
        }
        return addressService;
    }

    /**
     * If we have not initialize HomeService instance yet then init and return
     * @return HomeService instance
     */
    public HomeService getHomeServiceInstance() {
        if (homeService == null) {
            this.homeService = new HomeService();
        }
        return homeService;
    }

    /**
     * If we have not initialize GarageService instance yet then init and return
     * @return GarageService instance
     */
    public GarageService getGarageServiceInstance() {
        if (garageService == null) {
            this.garageService = new GarageService();
        }
        return garageService;
    }

    /**
     * If we have not initialize CarsInGarageService instance yet then init and return
     * @return CarsInGarageService instance
     */
    public CarsInGarageService getCarsInGarageServiceInstance() {
        if (carsInGarageService == null) {
            this.carsInGarageService = new CarsInGarageService();
        }
        return carsInGarageService;
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
     * If we have not list of car instance yet then create and return
     * @return list of car instance
     */
    public List<Car> getCarListInstance() {
        if (carList == null) {
            carList = new LinkedList<>();
        }
        return carList;
    }

    /**
     * Set car to car list. We use this method when we get data from database
     * after deleting some car from car table in database
     *
     * @param carList - cars which we get from database
     */
    public void setCarListInstance(List<Car> carList) {
        this.carList = carList;
    }

    /**
     * If we have not parking instance yet then create and return
     * @return parking instance
     */
    public Parking getParkingInstance() {
        if (parking == null) {
            parking = new Parking();
        }
        return parking;
    }

    /**
     * Set parked cars to parking. We use this method when we get data from database
     * after deleting some car from parking in database
     *
     * @param parkedCarList - cars which we get from database
     */
    public void setParkingInstance(List<ParkedCar> parkedCarList) {
        this.parking.setParkedCars(parkedCarList);
    }

    /**
     * If we have not car dealership instance yet then create and return
     * @return car dealership instance
     */
    public CarDealership getCarDealershipInstance() {
        if (carDealership == null) {
            carDealership = new CarDealership();
        }
        return carDealership;
    }

    /**
     * Set selling cars to car dealership. We use this method when we get data from database
     * after deleting some car from car dealership in database
     *
     * @param sellingCarList - cars which we get from database
     */
    public void setCarDealershipInstance(List<SellingCar> sellingCarList) {
        this.carDealership.setSellingCars(sellingCarList);
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
     * In this method we generate our local object homes by data from database
     *
     * @return all homes from database
     */
    public Homes getAllHomesFromDatabase() {
        Homes homes = new Homes();
        List<Garage> garageList = garageService.getAllGarage();
        for (Garage garageFromDatabase: garageList) {
            Address address = garageFromDatabase.getHome().getAddress();

            List<CarInGarage> carInGarageList = carsInGarageService.getCarsInGarageByGarageId(garageFromDatabase.getId());
            GarageOfHome garageOfHome = new GarageOfHome();
            garageOfHome.setBig(garageFromDatabase.isBig());
            garageOfHome.setCarsInGarage(carInGarageList);

            homes.addHome(address, garageOfHome);
        }
        return homes;
    }

    /**
     * Set homes to local object homes. We use this method when we get data from database
     * after deleting some home from home table in database
     *
     * @param homes - set to our local object homes
     */
    public void setHomesInstance(Homes homes) {
        this.homes = homes;
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
        for (Car car : carList) {
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


}
