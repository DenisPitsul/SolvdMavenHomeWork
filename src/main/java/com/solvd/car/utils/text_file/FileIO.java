package com.solvd.car.utils.text_file;

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
        createFileIfItDoesNotExist(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
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
        try (PrintWriter writer = new PrintWriter(file);) {
            writer.print("");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public static void createFileIfItDoesNotExist(String filePath) {
        File file = new File(filePath);
        try {
            if (file.createNewFile())
                LOGGER.info(filePath + " has created.");
            else
                LOGGER.info(filePath + " already exists.");
        } catch (IOException e) {
            LOGGER.error(String.format("Creating %s failed.", filePath), e);
        }
    }
}
