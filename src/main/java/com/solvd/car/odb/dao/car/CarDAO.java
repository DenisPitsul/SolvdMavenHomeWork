package com.solvd.car.odb.dao.car;

import com.solvd.car.odb.dao.DAOFactory;
import com.solvd.car.odb.entity.Car;
import com.solvd.car.odb.entity.CarDetail;
import com.solvd.car.odb.entity.Engine;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements ICarDAO {
    private static final Logger LOGGER = Logger.getLogger(CarDAO.class);

    @Override
    public void add(Car car) {
        Connection connection = DAOFactory.getInstance().getConnection();
        PreparedStatement statement;
        try {
            // add car detail in a separate table
            addCarDetail(car.getCarDetail(), connection);

            // get car detail id
            long carDetailId = getCarDetailId(connection);

            // get engine id, if it does not exist that add new engine to engine table
            long engineId = getEngineId(car.getEngine().getName(), connection);
            if (engineId == -1) {
                statement = connection.prepareStatement("INSERT INTO engine(name, type) VALUES (?, ?)");
                statement.setString(1, car.getEngine().getName());
                statement.setString(2, car.getEngine().getType());
                statement.execute();

                statement = connection.prepareStatement("SELECT MAX(id) FROM engine");
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next())
                    engineId = resultSet.getLong(1);
            }

            // add car to database
            statement = connection
                    .prepareStatement("INSERT INTO car(model, color, number, max_speed, year, engine_id, car_detail_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, car.getModel());
            statement.setString(2, car.getColor());
            statement.setString(3, car.getNumber());
            statement.setInt(4, car.getMaxSpeed());
            statement.setInt(5, car.getYear());
            statement.setLong(6, engineId);
            statement.setLong(7, carDetailId);
            statement.execute();
            LOGGER.info("Car added to database");
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
    }

    private void addCarDetail(CarDetail carDetail, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO car_detail" +
                            "(wheel_radius, salon, is_there_back_view_camera, passenger_seats_count, is_passenger, " +
                            "is_there_back_windows, clearance_length, is_there_top_trunk, lifting_capacity, battery_power_reserve) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, carDetail.getWheelRadius());
            statement.setString(2, carDetail.getSalon());
            statement.setBoolean(3, carDetail.isThereBackViewCamera());
            statement.setInt(4, carDetail.getPassengerSeatsCount());
            statement.setBoolean(5, carDetail.isPassenger());
            statement.setBoolean(6, carDetail.isThereBackWindows());
            statement.setInt(7, carDetail.getClearanceLength());
            statement.setBoolean(8, carDetail.isThereTopTrunk());
            statement.setInt(9, carDetail.getLiftingCapacity());
            statement.setInt(10, carDetail.getBatteryPowerReserve());
            statement.execute();
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
    }

    private long getCarDetailId(Connection connection) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("SELECT MAX(id) FROM car_detail");

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                return resultSet.getLong(1);
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
        return -1;
    }

    private long getEngineId(String engineName, Connection connection) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("SELECT id FROM engine WHERE name = ?");
            preparedStatement.setString(1, engineName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                return resultSet.getLong(1);
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
        return -1;
    }

    @Override
    public List<Car> getAll() {
        List<Car> carList = new ArrayList<>();
        Connection connection = DAOFactory.getInstance().getConnection();
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(
                    "SELECT c.id, c.model, c.color, c.number, c.max_speed, c.year, e.name, e.type, " +
                            "cd.wheel_radius, cd.salon, cd.is_there_back_view_camera, cd.passenger_seats_count, " +
                            "cd.is_passenger, cd.is_there_back_windows, cd.clearance_length, cd.is_there_top_trunk, " +
                            "cd.lifting_capacity, cd.battery_power_reserve FROM car as c " +
                            "INNER JOIN engine as e ON c.engine_id = e.id " +
                            "INNER JOIN car_detail as cd ON c.car_detail_id = cd.id");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong(1));
                car.setModel(resultSet.getString(2));
                car.setColor(resultSet.getString(3));
                car.setNumber(resultSet.getString(4));
                car.setMaxSpeed(resultSet.getInt(5));
                car.setYear(resultSet.getInt(6));

                Engine engine = new Engine();
                engine.setName(resultSet.getString(7));
                engine.setType(resultSet.getString(8));
                car.setEngine(engine);

                CarDetail carDetail = new CarDetail();
                carDetail.setWheelRadius(resultSet.getInt(9));
                carDetail.setSalon(resultSet.getString(10));
                carDetail.setThereBackViewCamera(resultSet.getBoolean(11));
                carDetail.setPassengerSeatsCount(resultSet.getInt(12));
                carDetail.setPassenger(resultSet.getBoolean(13));
                carDetail.setThereBackWindows(resultSet.getBoolean(14));
                carDetail.setClearanceLength(resultSet.getInt(15));
                carDetail.setThereTopTrunk(resultSet.getBoolean(16));
                carDetail.setLiftingCapacity(resultSet.getInt(17));
                carDetail.setBatteryPowerReserve(resultSet.getInt(18));
                car.setCarDetail(carDetail);

                carList.add(car);
            }
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }

        return carList;
    }

    @Override
    public Car getById(long id) {
        return null;
    }

    @Override
    public void remove(long id) {

    }

}
