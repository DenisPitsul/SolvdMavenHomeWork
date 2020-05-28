package com.solvd.car.odb.dao;

import com.solvd.car.odb.dao.car.CarDAO;
import com.solvd.car.odb.dao.car.ICarDAO;
import com.solvd.car.utils.property.PropertyIO;
import com.solvd.car.utils.text_file.FileHelper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory implements IDAOFactory {
    private static final String DB_PROPERTIES_PATH = "src"
            + FileHelper.SEPARATOR + "main"
            + FileHelper.SEPARATOR + "resources"
            + FileHelper.SEPARATOR + "db.properties";
    private static final Logger LOGGER = Logger.getLogger(DAOFactory.class);

    private Connection connection;
    private static DAOFactory instance;

    private DAOFactory() throws SQLException {
        String driver = PropertyIO.getValueFromProperties(DB_PROPERTIES_PATH, "jdbc.driver");
        String url = PropertyIO.getValueFromProperties(DB_PROPERTIES_PATH, "jdbc.url");
        String user = PropertyIO.getValueFromProperties(DB_PROPERTIES_PATH, "jdbc.user");
        String password = PropertyIO.getValueFromProperties(DB_PROPERTIES_PATH, "jdbc.password");
        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            LOGGER.error("Database Connection Creation Failed : " + ex.getMessage(), ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DAOFactory getInstance()  {
        try {
            if (instance == null) {
                instance = new DAOFactory();
            } else if (instance.getConnection().isClosed()) {
                instance = new DAOFactory();
            }
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
        return instance;
    }

    @Override
    public ICarDAO getCarDAO() {
        return new CarDAO();
    }
}
