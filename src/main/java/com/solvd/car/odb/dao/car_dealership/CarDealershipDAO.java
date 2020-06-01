package com.solvd.car.odb.dao.car_dealership;

import com.solvd.car.odb.entity.SellingCar;
import com.solvd.car.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class CarDealershipDAO implements ICarDealershipDAO {
    private static final Logger LOGGER = Logger.getLogger(CarDealershipDAO.class);

    private ICarDealershipDAO carDealershipDAO;
    private Class<ICarDealershipDAO> carDealershipDAOClass = ICarDealershipDAO.class;

    @Override
    public List<SellingCar> getAllSellingCars() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carDealershipDAO = session.getMapper(carDealershipDAOClass);
        List<SellingCar> sellingCarList = carDealershipDAO.getAllSellingCars();
        LOGGER.info("Got all selling cars");
        session.close();

        return sellingCarList;
    }

    @Override
    public SellingCar getSellingCarById(Long id) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carDealershipDAO = session.getMapper(carDealershipDAOClass);
        SellingCar sellingCar = carDealershipDAO.getSellingCarById(id);
        LOGGER.info("Got selling car by id");
        session.close();

        return sellingCar;
    }

    @Override
    public void addSellingCar(SellingCar sellingCar) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carDealershipDAO = session.getMapper(carDealershipDAOClass);
        carDealershipDAO.addSellingCar(sellingCar);
        LOGGER.info("Added car to car dealership");
        session.commit();
        session.close();
    }

    @Override
    public void deleteSellingCar(SellingCar sellingCar) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carDealershipDAO = session.getMapper(carDealershipDAOClass);
        carDealershipDAO.deleteSellingCar(sellingCar);
        LOGGER.info("Deleted car from car dealership");
        session.commit();
        session.close();
    }

    @Override
    public void updateSellingCar(SellingCar sellingCar) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        carDealershipDAO = session.getMapper(carDealershipDAOClass);
        carDealershipDAO.updateSellingCar(sellingCar);
        LOGGER.info("Updated selling car");
        session.commit();
        session.close();
    }
}
