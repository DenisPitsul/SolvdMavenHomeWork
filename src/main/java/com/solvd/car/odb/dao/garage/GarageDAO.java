package com.solvd.car.odb.dao.garage;

import com.solvd.car.odb.entity.Garage;
import com.solvd.car.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class GarageDAO implements IGarageDAO {
    private static final Logger LOGGER = Logger.getLogger(GarageDAO.class);

    private IGarageDAO garageDAO;
    private Class<IGarageDAO> garageDAOClass = IGarageDAO.class;

    @Override
    public List<Garage> getAllGarages() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        garageDAO = session.getMapper(garageDAOClass);
        List<Garage> garageList = garageDAO.getAllGarages();
        LOGGER.info("Got all garages");
        session.close();

        return garageList;
    }

    @Override
    public Garage getGarageById(Long id) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        garageDAO = session.getMapper(garageDAOClass);
        Garage garage = garageDAO.getGarageById(id);
        LOGGER.info("Got garage by id");
        session.close();

        return garage;
    }

    @Override
    public Garage getLastGarage() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        garageDAO = session.getMapper(garageDAOClass);
        Garage garage = garageDAO.getLastGarage();
        LOGGER.info("Got last garage");
        session.close();

        return garage;
    }

    @Override
    public void addGarage(Garage garage) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        garageDAO = session.getMapper(garageDAOClass);
        garageDAO.addGarage(garage);
        LOGGER.info("Added new garage");
        session.commit();
        session.close();
    }

    @Override
    public void deleteGarage(Garage garage) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        garageDAO = session.getMapper(garageDAOClass);
        garageDAO.deleteGarage(garage);
        LOGGER.info("Deleted garage");
        session.commit();
        session.close();
    }

    @Override
    public void updateGarage(Garage garage) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        garageDAO = session.getMapper(garageDAOClass);
        garageDAO.updateGarage(garage);
        LOGGER.info("Updated garage");
        session.commit();
        session.close();
    }
}
