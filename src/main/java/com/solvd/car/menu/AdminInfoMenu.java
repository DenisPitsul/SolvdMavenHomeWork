package com.solvd.car.menu;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminInfoMenu {
    private static final Logger LOGGER = Logger.getLogger(AdminInfoMenu.class);

    private Scanner in;
    private MainMenu mainMenu;
    private String inputIndex;

    public AdminInfoMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void inputAdminInfoOperation() {
        inputIndex = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Add new information                ->  1|");
                System.out.println("Get information by key             ->  2|");
                System.out.println("Show all admin information         ->  3|");
                System.out.println("Clear all admin information        ->  4|");

                inputIndex = in.nextLine();

                switch (inputIndex) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        mainMenu.openMainMenu();
                        break;
                    case "1":
                        openAddAdminInfoMenu();
                        break;
                    case "2":
                        openGetAdmitInfoMenu();
                        break;
                    case "3":
                        showAllAdminInfo();
                        break;
                    case "4":
                        clearAllAdminInfo();
                        break;
                    default:
                        LOGGER.info("You have to input number from menu.");
                        inputAdminInfoOperation();
                        break;
                }
                break;
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                inputAdminInfoOperation();
            }
        }
    }

    private void openAddAdminInfoMenu() {
        String inputData = "";
        String key = "";
        String value = "";
        /*
         *  number of iteration in method
         *  0 -> input key
         *  1 -> input value
         */
        int propertyNumber = 0;
        while(propertyNumber < 3) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input               -> -1|");
                System.out.println("Go back input                      -> -2|");
                System.out.println("----------------------------------------|");
                System.out.println("Adding new information about admin      |");
                System.out.println("----------------------------------------|");
                if(propertyNumber == 0) {
                    System.out.print("Input key: ");
                    inputData = in.nextLine();

                    switch (inputData) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputAdminInfoOperation();
                            break;
                        default:
                            if (!inputData.equals("")) {
                                key = inputData;
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input key.");
                            }
                            break;
                    }
                }
                else if(propertyNumber == 1) {
                    System.out.print("Input value: ");
                    inputData = in.nextLine();

                    switch (inputData) {
                        case "-1":
                            System.exit(0);
                            break;
                        case "-2":
                            inputAdminInfoOperation();
                            break;
                        default:
                            if (!inputData.equals("")) {
                                value = inputData;
                                propertyNumber++;
                            }
                            else {
                                LOGGER.info("You have to input value.");
                            }
                            break;
                    }
                }
                else {
                    System.out.println("Info added!");
                    System.out.println(key + " - " + value);
                    mainMenu.getAdminInfoDAO().setValue(key, value);
                    inputAdminInfoOperation();
                    propertyNumber++;
                }
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct input data.");
            }
        }
    }

    private void openGetAdmitInfoMenu() {
        String inputData = "";
        while(true) {
            try {
                in = new Scanner(System.in);

                System.out.println("Finish program input                      -> -1|");
                System.out.println("Go back input                             -> -2|");
                System.out.println("-----------------------------------------------|");
                System.out.println("Getting new information about admin by key     |");
                System.out.println("-----------------------------------------------|");
                System.out.print("Input key: ");
                inputData = in.nextLine();

                switch (inputData) {
                    case "-1":
                        System.exit(0);
                        break;
                    case "-2":
                        mainMenu.openMainMenu();
                        break;
                    case "":
                        LOGGER.info("You have to input number from menu.");
                        openGetAdmitInfoMenu();
                        break;
                    default:
                        String value = mainMenu.getAdminInfoDAO().getValue(inputData);
                        LOGGER.info(inputData + " - " + value);
                        inputAdminInfoOperation();
                        break;
                }
                break;
            } catch(InputMismatchException | NumberFormatException e) {
                LOGGER.error(e);
                LOGGER.info("You have to input correct number.");
            } finally {
                inputAdminInfoOperation();
            }
        }
    }

    private void showAllAdminInfo() {
        System.out.println(mainMenu.getAdminInfoDAO().getAllInfo());
        inputAdminInfoOperation();
    }

    private void clearAllAdminInfo() {
        mainMenu.getAdminInfoDAO().clearAllInfo();
        LOGGER.info(mainMenu.getAdminInfoDAO().getFilePath() + " has cleared.");
        inputAdminInfoOperation();
    }
}
