package com.solvd.car.odb.dao.car;

import com.solvd.car.odb.dao.car_detail.ICarDetailDAO;
import com.solvd.car.odb.dao.engine.IEngineDAO;
import com.solvd.car.odb.entity.Car;
import com.solvd.car.odb.entity.CarDetail;
import com.solvd.car.odb.entity.Engine;
import com.solvd.car.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class CarDAO implements ICarDAO {
    private static final Logger LOGGER = Logger.getLogger(CarDAO.class);

    private ICarDAO carDAO;
    private Class<ICarDAO> carDAOClass = ICarDAO.class;
    private IEngineDAO engineDAO;
    private Class<IEngineDAO> engineDAOClass = IEngineDAO.class;
    private ICarDetailDAO carDetailDAO;
    private Class<ICarDetailDAO> carDetailDAOClass = ICarDetailDAO.class;

    @Override
    public List<Car> getAllCars() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carDAO = session.getMapper(carDAOClass);
        List<Car> carList = carDAO.getAllCars();
        LOGGER.info("Got all cars");
        session.close();

        return carList;
    }

    @Override
    public Car getCarById(Long id) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carDAO = session.getMapper(carDAOClass);
        Car car =  carDAO.getCarById(id);
        LOGGER.info("Got car by id");
        session.close();

        return car;
    }

    @Override
    public void addCar(Car car) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();

        engineDAO = session.getMapper(engineDAOClass);
        Engine engine = engineDAO.getEngineByName(car.getEngine().getName());
        LOGGER.info("Got engine by name");
        if (engine == null) {
            LOGGER.info("There is not any engine by name");
            engineDAO.addEngine(car.getEngine());
            LOGGER.info("Added engine");
            engine = engineDAO.getLastEngine();
            LOGGER.info("Got last engine");
        }
        car.setEngine(engine);

        carDetailDAO = session.getMapper(carDetailDAOClass);
        carDetailDAO.addCarDetail(car.getCarDetail());
        LOGGER.info("Added car detail");
        CarDetail carDetail = carDetailDAO.getLastCarDetail();
        car.setCarDetail(carDetail);

        carDAO = session.getMapper(carDAOClass);
        carDAO.addCar(car);
        LOGGER.info("Added car");
        session.commit();
        session.close();
    }

    @Override
    public void deleteCar(Car car) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carDAO = session.getMapper(carDAOClass);
        carDAO.deleteCar(car);
        LOGGER.info("Deleted car");
        carDetailDAO = session.getMapper(carDetailDAOClass);
        carDetailDAO.deleteCarDetail(car.getCarDetail());
        LOGGER.info("Deleted car detail");
        session.commit();
        session.close();
    }

    @Override
    public void updateCar(Car car) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carDAO = session.getMapper(carDAOClass);
        carDAO.updateCar(car);
        LOGGER.info("Updated car");
        carDetailDAO = session.getMapper(carDetailDAOClass);
        carDetailDAO.updateCarDetail(car.getCarDetail());
        LOGGER.info("Updated car detail");
        session.commit();
        session.close();
    }

}
