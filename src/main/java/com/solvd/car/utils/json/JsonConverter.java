package com.solvd.car.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.car.utils.json.model.CarDealershipPOJO;
import com.solvd.car.utils.json.model.GaragePOJO;
import com.solvd.car.utils.json.model.HomesReadPOJO;
import com.solvd.car.utils.json.model.ParkingPOJO;
import com.solvd.car.utils.text_file.FileIO;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class JsonConverter {
    private final static Logger LOGGER = Logger.getLogger(JsonConverter.class);

    public static String convertJavaToJsonStr(Object obj) {
        String jsonStr = "";
        try {
            jsonStr = new ObjectMapper().writeValueAsString(obj);
            LOGGER.info("Converting to string, finished.");
        } catch (JsonProcessingException e) {
            LOGGER.error("Converting to string, failed.", e);
        }
        return jsonStr;
    }

    public static void convertJavaToJsonFile(Object obj, String pathToFile) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
            DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
            prettyPrinter.indentObjectsWith(indenter);
            prettyPrinter.indentArraysWith(indenter);

            mapper.writer(prettyPrinter).writeValue(Paths.get(pathToFile).toFile(), obj);
            LOGGER.info("Writing to file, finished.");
        } catch (JsonProcessingException e) {
            LOGGER.error("Converting to object, failed.", e);
        } catch (IOException e) {
            LOGGER.error("Writing to file failed.", e);
        }
    }

    public static ParkingPOJO convertJsonFileToParkingPOJO(String filePath) {
        FileIO.createFileIfItDoesNotExist(filePath);
        ParkingPOJO parking = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            parking  = mapper.readValue(new File(filePath), ParkingPOJO.class);
            LOGGER.info("Converting to Parking POJO, finished.");
        } catch (JsonProcessingException e) {
            LOGGER.error("Converting to Parking POJO, failed.", e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return parking;
    }

    public static GaragePOJO convertJsonFileToGaragePOJO(String filePath) {
        FileIO.createFileIfItDoesNotExist(filePath);
        GaragePOJO garage = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            garage  = mapper.readValue(new File(filePath), GaragePOJO.class);
            LOGGER.info("Converting to Garage POJO, finished.");
        } catch (JsonProcessingException e) {
            LOGGER.error("Converting to Garage POJO, failed.", e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return garage;
    }

    public static CarDealershipPOJO convertJsonFileToCarDealershipPOJO(String filePath) {
        FileIO.createFileIfItDoesNotExist(filePath);
        CarDealershipPOJO carDealership = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            carDealership  = mapper.readValue(new File(filePath), CarDealershipPOJO.class);
            LOGGER.info("Converting to Car Dealership POJO, finished.");
        } catch (JsonProcessingException e) {
            LOGGER.error("Converting to Car Dealership POJO, failed.", e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return carDealership;
    }

    public static HomesReadPOJO convertJsonFileToHomesPOJO(String filePath) {
        FileIO.createFileIfItDoesNotExist(filePath);
        HomesReadPOJO homes = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            homes = mapper.readValue(new File(filePath), HomesReadPOJO.class);
            LOGGER.info("Converting to Homes POJO, finished.");
        } catch (JsonProcessingException e) {
            LOGGER.error("Converting to Homes POJO, failed.", e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return homes;
    }

}
