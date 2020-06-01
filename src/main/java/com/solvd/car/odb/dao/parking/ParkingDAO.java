package com.solvd.car.odb.dao.parking;

import com.solvd.car.odb.entity.ParkedCar;
import com.solvd.car.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class ParkingDAO implements IParkingDAO {
    private static final Logger LOGGER = Logger.getLogger(ParkingDAO.class);

    private IParkingDAO parkingDAO;
    private Class<IParkingDAO> parkingDAOClass = IParkingDAO.class;

    @Override
    public List<ParkedCar> getAllParkedCars() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        parkingDAO = session.getMapper(parkingDAOClass);
        List<ParkedCar> parkedCarList = parkingDAO.getAllParkedCars();
        LOGGER.info("Got all parked cars");
        session.close();

        return parkedCarList;
    }

    @Override
    public ParkedCar getParkedCarById(Long id) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        parkingDAO = session.getMapper(parkingDAOClass);
        ParkedCar parkedCar = parkingDAO.getParkedCarById(id);
        LOGGER.info("Got parked car by id");
        session.close();

        return parkedCar;
    }

    @Override
    public void addParkedCar(ParkedCar parkedCar) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        parkingDAO = session.getMapper(parkingDAOClass);
        parkingDAO.addParkedCar(parkedCar);
        LOGGER.info("Added car to parking");
        session.commit();
        session.close();
    }

    @Override
    public void deleteParkedCar(ParkedCar parkedCar) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        parkingDAO = session.getMapper(parkingDAOClass);
        parkingDAO.deleteParkedCar(parkedCar);
        LOGGER.info("Deleted car from parking");
        session.commit();
        session.close();
    }

    @Override
    public void updateParkedCar(ParkedCar parkedCar) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        parkingDAO = session.getMapper(parkingDAOClass);
        parkingDAO.updateParkedCar(parkedCar);
        LOGGER.info("Updated parked car");
        session.commit();
        session.close();
    }
}
