package com.solvd.car.odb.dao.cars_in_garage;

import com.solvd.car.odb.dao.car_dealership.CarDealershipDAO;
import com.solvd.car.odb.entity.CarInGarage;
import com.solvd.car.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class CarsInGarageDAO implements ICarsInGarageDAO {
    private static final Logger LOGGER = Logger.getLogger(CarDealershipDAO.class);

    private ICarsInGarageDAO carsInGarageDAO;
    private Class<ICarsInGarageDAO> carsInGarageDAOClass = ICarsInGarageDAO.class;

    @Override
    public List<CarInGarage> getAllCarsInGarages() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carsInGarageDAO = session.getMapper(carsInGarageDAOClass);
        List<CarInGarage> carInGarageList = carsInGarageDAO.getAllCarsInGarages();
        LOGGER.info("Got all cars in garages");
        session.close();

        return carInGarageList;
    }

    @Override
    public CarInGarage getCarInGarageById(Long id) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carsInGarageDAO = session.getMapper(carsInGarageDAOClass);
        CarInGarage carInGarage = carsInGarageDAO.getCarInGarageById(id);
        LOGGER.info("Got car in garage by id");
        session.close();

        return carInGarage;
    }

    @Override
    public List<CarInGarage> getCarsInGarageByGarageId(Long garageId) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carsInGarageDAO = session.getMapper(carsInGarageDAOClass);
        List<CarInGarage> carInGarageList = carsInGarageDAO.getCarsInGarageByGarageId(garageId);
        LOGGER.info("Got cars in garage by garage id");
        session.close();

        return carInGarageList;
    }

    @Override
    public void addCarInGarage(CarInGarage carInGarage) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carsInGarageDAO = session.getMapper(carsInGarageDAOClass);
        carsInGarageDAO.addCarInGarage(carInGarage);
        LOGGER.info("Added new car to garage");
        session.commit();
        session.close();
    }

    @Override
    public void deleteCarInGarage(CarInGarage carInGarage) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carsInGarageDAO = session.getMapper(carsInGarageDAOClass);
        carsInGarageDAO.deleteCarInGarage(carInGarage);
        LOGGER.info("Deleted car from garage");
        session.commit();
        session.close();
    }

    @Override
    public void updateCarInGarage(CarInGarage carInGarage) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carsInGarageDAO = session.getMapper(carsInGarageDAOClass);
        carsInGarageDAO.updateCarInGarage(carInGarage);
        LOGGER.info("Updated car in garage");
        session.commit();
        session.close();
    }
}
