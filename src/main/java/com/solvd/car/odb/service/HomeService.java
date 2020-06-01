package com.solvd.car.odb.service;

import com.solvd.car.odb.dao.home.HomeDAO;
import com.solvd.car.odb.dao.home.IHomeDAO;
import com.solvd.car.odb.entity.Home;

import java.util.List;

public class HomeService {
    private IHomeDAO homeDAO = new HomeDAO();

    public List<Home> getAllHomes() {
        return homeDAO.getAllHomes();
    }

    public Home getHomeById(Long id) {
        return homeDAO.getHomeById(id);
    }

    public Home getLastHome() {
        return homeDAO.getLastHome();
    }

    public void addHome(Home home) {
        homeDAO.addHome(home);
    }

    public void deleteHome(Home home) {
        homeDAO.deleteHome(home);
    }

    public void updateHome(Home home) {
        homeDAO.updateHome(home);
    }
}
