package com.solvd.car.utils;

import org.apache.log4j.Logger;

import java.io.*;

public class FileIO {
    private static final Logger LOGGER = Logger.getLogger(FileIO.class);

    public static void writeToFile(String path, String value) {
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(value);
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public static String readFromFile(String path) {
        File file = new File(path);
        try {
            if (file.createNewFile())
                LOGGER.info(path + " has created!");
            else
                LOGGER.info(path + " already exists.");
        } catch (IOException e) {
            LOGGER.error(e);
        }
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return sb.toString();
    }

    public static void clearFile(String path) {
        File file = new File(path);
        try ( PrintWriter writer = new PrintWriter(file);) {
            writer.print("");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
