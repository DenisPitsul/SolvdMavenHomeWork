package com.solvd.car.place_io;

import com.solvd.car.utils.FileHelper;
import com.solvd.car.utils.PropertyIO;

import java.io.File;

public class AdminInfoIO {
    public static final String ADMIN_INFO_FILE_PATH = "src" + FileHelper.SEPARATOR + "main" + FileHelper.SEPARATOR + "resources"
            + FileHelper.SEPARATOR + "data" + FileHelper.SEPARATOR + "properties" + FileHelper.SEPARATOR + "admin_info.properties";

    private File file;

    public AdminInfoIO(String filePath) {
        this.file = new File(filePath);
    }

    public File getFile() {
        return file;
    }

    public String getFilePath() {
        return file.getPath();
    }

    public String getAllInfo() {
        return PropertyIO.getAllProperties(getFilePath());
    }

    public String getValue(String key) {
        return PropertyIO.getValueFromProperties(getFilePath(), key);
    }

    public void setValue(String key, String value) {
        PropertyIO.setValueToProperties(getFilePath(), key, value);
    }

    public void clearAllInfo() {
        PropertyIO.clearProperties(getFilePath());
    }
}
