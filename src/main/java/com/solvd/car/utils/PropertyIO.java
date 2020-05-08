package com.solvd.car.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyIO {
    private static final Logger LOGGER = Logger.getLogger(PropertyIO.class);

    public static String getAllProperties(String filePath) {
        Properties properties = new Properties();

        StringBuilder sb = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath))){
            properties.load(fileInputStream);
            for (Object key: properties.keySet()) {
                sb.append(key.toString()).append(" - ").append(properties.getProperty(key.toString()))
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return sb.toString();
    }

    public static String getValueFromProperties(String filePath, String key) {
        Properties properties = new Properties();

        String value = null;
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath))){
            properties.load(fileInputStream);
            value = properties.getProperty(key);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return value == null ? "no information" : value;
    }

    public static void setValueToProperties(String filePath, String key, String value) {
        Properties properties = new Properties();

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath), true)){
            properties.setProperty(key, value);
            properties.store(fileOutputStream, "");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public static void clearProperties(String filePath) {
        Properties properties = new Properties();

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath))){
            properties.store(fileOutputStream, "");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
