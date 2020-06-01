package com.solvd.car.odb.dao.home;

import com.solvd.car.odb.entity.Home;
import com.solvd.car.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class HomeDAO implements IHomeDAO {
    private static final Logger LOGGER = Logger.getLogger(HomeDAO.class);

    private IHomeDAO homeDAO;
    private Class<IHomeDAO> homeDAOClass = IHomeDAO.class;

    @Override
    public List<Home> getAllHomes() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        homeDAO = session.getMapper(homeDAOClass);
        List<Home> homeList = homeDAO.getAllHomes();
        LOGGER.info("Got all homes");
        session.close();

        return homeList;
    }

    @Override
    public Home getHomeById(Long id) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        homeDAO = session.getMapper(homeDAOClass);
        Home home = homeDAO.getHomeById(id);
        LOGGER.info("Got home by id");
        session.close();

        return home;
    }

    @Override
    public Home getLastHome() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        homeDAO = session.getMapper(homeDAOClass);
        Home home = homeDAO.getLastHome();
        LOGGER.info("Got last home");
        session.close();

        return home;
    }

    @Override
    public void addHome(Home home) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        homeDAO = session.getMapper(homeDAOClass);
        homeDAO.addHome(home);
        LOGGER.info("Added new home");
        session.commit();
        session.close();
    }

    @Override
    public void deleteHome(Home home) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        homeDAO = session.getMapper(homeDAOClass);
        homeDAO.deleteHome(home);
        LOGGER.info("Deleted home");
        session.commit();
        session.close();
    }

    @Override
    public void updateHome(Home home) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        homeDAO = session.getMapper(homeDAOClass);
        homeDAO.updateHome(home);
        LOGGER.info("Updated home");
        session.commit();
        session.close();
    }
}
