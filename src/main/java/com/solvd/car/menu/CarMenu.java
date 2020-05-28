package com.solvd.car.menu;

import com.solvd.car.factory_method.FactoryMethodCar;
import com.solvd.car.odb.dao.DAOFactory;
import com.solvd.car.odb.dao.car.CarDAO;
import com.solvd.car.odb.dao.car.ICarDAO;
import com.solvd.car.odb.entity.Car;
import com.solvd.car.odb.entity.CarDetail;
import com.solvd.car.odb.entity.Engine;
import com.solvd.car.vehicle.Vehicle;
import com.solvd.car.vehicle.final_car.AudiA6;
import com.solvd.car.vehicle.final_car.MercedesVito;
import com.solvd.car.vehicle.final_car.TeslaSemi;
import com.solvd.car.vehicle.final_car.ToyotaLandCruiser;
import com.solvd.car.vehicle.helper.CarModel;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CarMenu {
    private static final Logger LOGGER = Logger.getLogger(CarMenu.class);
    private static final String ONLY_LETTERS_REGEX = "^([a-zA-Z]*)$";
    private static final String ONLY_NUMBERS_REGEX = "^([1-9][0-9]*)$";
    private static final String LETTERS_NUMBERS_REGEX = "^[a-zA-Z0-9]*$";

    private Scanner in;
    private MainMenu mainMenu;
    private String inputIndex;

    private ICarDAO carDAO;

    public CarMenu(MainMenu mainMenu)  {
        this.mainMenu = mainMenu;
        setCarDao();
    }

    private void setCarDao() {
        this.carDAO = DAOFactory.getInstance().getCarDAO();
    }

    /**
     * Choose model of car to create or choose 6 to show all created cars on the screen
     * 1 -> Audi A6
     * 2 -> Mercedes Vito
     * 3 -> Toyota Land Cruiser
     * 4 -> Tesla Semi
     * 5 -> Default Car. It will call default method in CarCreator interface
     */
    public void inputCarType() {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Create Audi A6 input               ->  1|");
                System.out.println("Create Mercedes Vito input         ->  2|");
                System.out.println("Create Toyota Land Cruiser input   ->  3|");
                System.out.println("Create Tesla Semi input            ->  4|");
                System.out.println("Create Default Car(Audi A6) input  ->  5|");
                System.out.println("Show all cars input                ->  6|");

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        mainMenu.openMainMenu();
                        break;
                    case "1":
                        chooseCreateType(CarModel.AUDI_A6);
                        break;
                    case "2":
                        chooseCreateType(CarModel.MERCEDES_VITO);
                        break;
                    case "3":
                        chooseCreateType(CarModel.TOYOTA_LAND_CRUISER);
                        break;
                    case "4":
                        chooseCreateType(CarModel.TESLA_SEMI);
                        break;
                    case "5":
                        Vehicle car = FactoryMethodCar.createCar(CarModel.DEFAULT);
                        mainMenu.getCarListInstance().add(car);

                        AudiA6 audiA6 = (AudiA6) car;
                        addAudiA6ToDataBase(audiA6);

                        inputCarType();
                        break;
                    case "6":
                        mainMenu.showAllCars();
                        mainMenu.openMainMenu();
                        break;
                    default:
                        LOGGER.info("You have to input number from menu.");
                        inputCarType();
                        break;
                }
                break;
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                inputCarType();
            }
        }
    }

    /**
     * Choose 1 to create selected car manually
     * Choose 2 to create selected car automatically
     * If we choose 2 car will be created by FactoryMethodCar class
     */
    private void chooseCreateType(CarModel carModel) {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Create "+ carModel +" manually \t\t\t-> 1|");
                System.out.println("Create "+ carModel +" automatically\t\t-> 2|");

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        inputCarType();
                        break;
                    case "1":
                        switch (carModel) {
                            case AUDI_A6:
                                createAudiA6Form(0, new AudiA6());
                                break;
                            case MERCEDES_VITO:
                                createMercedesVitoForm(0, new MercedesVito());
                                break;
                            case TOYOTA_LAND_CRUISER:
                                createToyotaLandCruiserForm(0, new ToyotaLandCruiser());
                                break;
                            case TESLA_SEMI:
                                createTeslaSemiForm(0, new TeslaSemi());
                                break;
                        }
                        break;
                    case "2":
                        Vehicle car = FactoryMethodCar.createCar(carModel);
                        mainMenu.getCarListInstance().add(car);

                        switch (carModel) {
                            case AUDI_A6:
                                AudiA6 audiA6 = (AudiA6) car;
                                addAudiA6ToDataBase(audiA6);
                                break;
                            case MERCEDES_VITO:
                                MercedesVito mercedesVito = (MercedesVito) car;
                                addMercedesVitoToDataBase(mercedesVito);
                                break;
                            case TOYOTA_LAND_CRUISER:
                                ToyotaLandCruiser toyotaLandCruiser = (ToyotaLandCruiser) car;
                                addToyotaLandCruiserToDataBase(toyotaLandCruiser);
                                break;
                            case TESLA_SEMI:
                                TeslaSemi teslaSemi = (TeslaSemi) car;
                                addTeslaSemiToDataBase(teslaSemi);
                                break;
                        }

                        inputCarType();
                        break;
                    default:
                        LOGGER.info("You have to input number from menu.");
                        chooseCreateType(carModel);
                        break;
                }
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                chooseCreateType(carModel);
            }
        }
    }

    private void addAudiA6ToDataBase(AudiA6 audiA6) {
        Car car = new Car();
        car.setModel(audiA6.getCarModel());
        car.setColor(audiA6.getColor());
        car.setNumber(audiA6.getNumber());
        car.setMaxSpeed(audiA6.getMaxSpeed());
        car.setYear(audiA6.getYear());

        Engine engine = new Engine();
        engine.setName(audiA6.getEngine().getName());
        engine.setType(audiA6.getEngine().getType());
        car.setEngine(engine);

        CarDetail carDetail = new CarDetail();
        carDetail.setWheelRadius(audiA6.getWheelRadius());
        carDetail.setSalon(audiA6.getSalon());
        carDetail.setThereBackViewCamera(audiA6.isThereBackViewCamera());
        car.setCarDetail(carDetail);

        LOGGER.info("audi a6");

        carDAO.add(car);
    }

    private void addMercedesVitoToDataBase(MercedesVito mercedesVito) {
        Car car = new Car();
        car.setModel(mercedesVito.getCarModel());
        car.setColor(mercedesVito.getColor());
        car.setNumber(mercedesVito.getNumber());
        car.setMaxSpeed(mercedesVito.getMaxSpeed());
        car.setYear(mercedesVito.getYear());

        Engine engine = new Engine();
        engine.setName(mercedesVito.getEngine().getName());
        engine.setType(mercedesVito.getEngine().getType());
        car.setEngine(engine);

        CarDetail carDetail = new CarDetail();
        carDetail.setPassenger(mercedesVito.isPassenger());
        carDetail.setThereBackWindows(mercedesVito.isThereBackWindows());
        carDetail.setPassengerSeatsCount(mercedesVito.getPassengerSeatsCount());
        car.setCarDetail(carDetail);

        carDAO.add(car);
    }

    private void addToyotaLandCruiserToDataBase(ToyotaLandCruiser toyotaLandCruiser) {
        Car car = new Car();
        car.setModel(toyotaLandCruiser.getCarModel());
        car.setColor(toyotaLandCruiser.getColor());
        car.setNumber(toyotaLandCruiser.getNumber());
        car.setMaxSpeed(toyotaLandCruiser.getMaxSpeed());
        car.setYear(toyotaLandCruiser.getYear());

        Engine engine = new Engine();
        engine.setName(toyotaLandCruiser.getEngine().getName());
        engine.setType(toyotaLandCruiser.getEngine().getType());
        car.setEngine(engine);

        CarDetail carDetail = new CarDetail();
        carDetail.setThereTopTrunk(toyotaLandCruiser.isThereTopTrunk());
        carDetail.setClearanceLength(toyotaLandCruiser.getClearanceLength());
        carDetail.setThereBackViewCamera(toyotaLandCruiser.isThereBackViewCamera());
        car.setCarDetail(carDetail);

        carDAO.add(car);
    }

    private void addTeslaSemiToDataBase(TeslaSemi teslaSemi) {
        Car car = new Car();
        car.setModel(teslaSemi.getCarModel());
        car.setColor(teslaSemi.getColor());
        car.setNumber(teslaSemi.getNumber());
        car.setMaxSpeed(teslaSemi.getMaxSpeed());
        car.setYear(teslaSemi.getYear());

        Engine engine = new Engine();
        engine.setName(teslaSemi.getEngine().getName());
        engine.setType(teslaSemi.getEngine().getType());
        car.setEngine(engine);

        CarDetail carDetail = new CarDetail();
        carDetail.setLiftingCapacity(teslaSemi.getLiftingCapacity());
        carDetail.setBatteryPowerReserve(teslaSemi.getBatteryPowerReserve());
        car.setCarDetail(carDetail);

        carDAO.add(car);
    }

    /**
     *  Create Audi A6 manually
     *  Input color of car, car number, max speed and year of the car
     */
    private void createAudiA6Form(int propertyNumberParameter, AudiA6 audiA6Car) {
        AudiA6 audiA6 = audiA6Car;

        String value = "";
        /*
         *  number of iteration in method
         *  0 -> input color
         *  1 -> input car number
         *  2 -> input max speed
         *  3 -> input year of car
         *  4 -> create car
         */
        int propertyNumber = propertyNumberParameter;
        while(propertyNumber < 5) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Create Audi A6                          |");
                System.out.println("----------------------------------------|");
                if(propertyNumber == 0) {
                    System.out.print("Input color: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_LETTERS_REGEX)) {
                                audiA6.setColor(value);
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input color. Color can contains only letters.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 1) {
                    System.out.print("Input car number: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(LETTERS_NUMBERS_REGEX)) {
                                audiA6.setNumber(value);
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input car number. Car number can contains only letters and numbers.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 2) {
                    System.out.print("Input max speed: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_NUMBERS_REGEX)) {
                                audiA6.setMaxSpeed(Integer.parseInt(value));
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input max speed. Max speed can contains only numbers.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 3) {
                    System.out.print("Input year of car: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_NUMBERS_REGEX)) {
                                audiA6.setYear(Integer.parseInt(value));
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input year of car. Year of car can contains only numbers.");
                            }
                            break;
                    }
                }
                else {
                    LOGGER.info("Audi A6 created.");
                    LOGGER.debug(audiA6);
                    mainMenu.getCarListInstance().add(audiA6);

                    addAudiA6ToDataBase(audiA6);

                    mainMenu.openMainMenu();
                    propertyNumber++;
                }
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct value.");
            } finally {
                createAudiA6Form(propertyNumber, audiA6);
            }
        }
    }

    /**
     *  Create Mercedes Vito manually
     *  Input color of car, car number, max speed and year of the car
     */
    private void createMercedesVitoForm(int propertyNumberParameter, MercedesVito mercedesVitoCar) {
        MercedesVito mercedesVito = mercedesVitoCar;

        String value = "";
        /*
         *  number of iteration in method
         *  0 -> input color
         *  1 -> input car number
         *  2 -> input max speed
         *  3 -> input year of car
         *  4 -> create car
         */
        int propertyNumber = propertyNumberParameter;
        while(propertyNumber < 5) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Create Mercedes Vito                    |");
                System.out.println("----------------------------------------|");
                if(propertyNumber == 0) {
                    System.out.print("Input color: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_LETTERS_REGEX)) {
                                mercedesVito.setColor(value);
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input color. Color can contains only letters.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 1) {
                    System.out.print("Input car number: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(LETTERS_NUMBERS_REGEX)) {
                                mercedesVito.setNumber(value);
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input car number. Car number can contains only letters and numbers.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 2) {
                    System.out.print("Input max speed: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_NUMBERS_REGEX)) {
                                mercedesVito.setMaxSpeed(Integer.parseInt(value));
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input max speed. Max speed can contains only numbers.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 3) {
                    System.out.print("Input year of car: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_NUMBERS_REGEX)) {
                                mercedesVito.setYear(Integer.parseInt(value));
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input year of car. Year of car can contains only numbers.");
                            }
                            break;
                    }
                }
                else {
                    LOGGER.info("Mercedes Vito created.");
                    LOGGER.debug(mercedesVito);
                    mainMenu.getCarListInstance().add(mercedesVito);

                    addMercedesVitoToDataBase(mercedesVito);

                    mainMenu.openMainMenu();
                    propertyNumber++;
                }
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct value.");
            } finally {
                createMercedesVitoForm(propertyNumber, mercedesVito);
            }
        }
    }

    /**
     *  Create Toyota Land Cruiser manually
     *  Input color of car, car number, max speed and year of the car
     */
    private void createToyotaLandCruiserForm(int propertyNumberParameter, ToyotaLandCruiser toyotaLandCruiserCar) {
        ToyotaLandCruiser toyotaLandCruiser = toyotaLandCruiserCar;

        String value = "";
        /*
         *  number of iteration in method
         *  0 -> input color
         *  1 -> input car number
         *  2 -> input max speed
         *  3 -> input year of car
         *  4 -> create car
         */
        int propertyNumber = propertyNumberParameter;
        while(propertyNumber < 5) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Create Toyota Land Cruiser              |");
                System.out.println("----------------------------------------|");
                if(propertyNumber == 0) {
                    System.out.print("Input color: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_LETTERS_REGEX)) {
                                toyotaLandCruiser.setColor(value);
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input color. Color can contains only letters.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 1) {
                    System.out.print("Input car number: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(LETTERS_NUMBERS_REGEX)) {
                                toyotaLandCruiser.setNumber(value);
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input car number. Car number can contains only letters and numbers.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 2) {
                    System.out.print("Input max speed: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_NUMBERS_REGEX)) {
                                toyotaLandCruiser.setMaxSpeed(Integer.parseInt(value));
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input max speed. Max speed can contains only numbers.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 3) {
                    System.out.print("Input year of car: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_NUMBERS_REGEX)) {
                                toyotaLandCruiser.setYear(Integer.parseInt(value));
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input year of car. Year of car can contains only numbers.");
                            }
                            break;
                    }
                }
                else {
                    LOGGER.info("Toyota Land Cruiser created.");
                    LOGGER.debug(toyotaLandCruiser);
                    mainMenu.getCarListInstance().add(toyotaLandCruiser);

                    addToyotaLandCruiserToDataBase(toyotaLandCruiser);

                    mainMenu.openMainMenu();
                    propertyNumber++;
                }
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct value.");
            } finally {
                createToyotaLandCruiserForm(propertyNumber, toyotaLandCruiser);
            }
        }
    }

    /**
     *  Create Tesla Semi manually
     *  Input color of car, car number, max speed and year of the car
     */
    private void createTeslaSemiForm(int propertyNumberParameter, TeslaSemi teslaSemiCar) {
        TeslaSemi teslaSemi = teslaSemiCar;

        String value = "";
        /*
         *  number of iteration in method
         *  0 -> input color
         *  1 -> input car number
         *  2 -> input max speed
         *  3 -> input year of car
         *  4 -> create car
         */
        int propertyNumber = propertyNumberParameter;
        while(propertyNumber < 5) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Create Tesla Semi                       |");
                System.out.println("----------------------------------------|");
                if(propertyNumber == 0) {
                    System.out.print("Input color: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_LETTERS_REGEX)) {
                                teslaSemi.setColor(value);
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input color. Color can contains only letters.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 1) {
                    System.out.print("Input car number: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(LETTERS_NUMBERS_REGEX)) {
                                teslaSemi.setNumber(value);
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input car number. Car number can contains only letters and numbers.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 2) {
                    System.out.print("Input max speed: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_NUMBERS_REGEX)) {
                                teslaSemi.setMaxSpeed(Integer.parseInt(value));
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input max speed. Max speed can contains only numbers.");
                            }
                            break;
                    }
                }
                else if (propertyNumber == 3) {
                    System.out.print("Input year of car: ");
                    value = in.nextLine();

                    switch (value) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputCarType();
                            break;
                        default:
                            if (!value.equals("") && value.matches(ONLY_NUMBERS_REGEX)) {
                                teslaSemi.setYear(Integer.parseInt(value));
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input year of car. Year of car can contains only numbers.");
                            }
                            break;
                    }
                }
                else {
                    LOGGER.info("Tesla Semi created.");
                    LOGGER.debug(teslaSemi);
                    mainMenu.getCarListInstance().add(teslaSemi);

                    addTeslaSemiToDataBase(teslaSemi);

                    mainMenu.openMainMenu();
                    propertyNumber++;
                }
            } catch(InputMismatchException | NumberFormatException e) {
                System.out.println("You have to input correct value");
            } finally {
                createTeslaSemiForm(propertyNumber, teslaSemi);
            }
        }
    }
}
