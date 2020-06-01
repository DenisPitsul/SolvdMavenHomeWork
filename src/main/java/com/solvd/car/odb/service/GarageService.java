package com.solvd.car.odb.service;

import com.solvd.car.odb.dao.garage.GarageDAO;
import com.solvd.car.odb.dao.garage.IGarageDAO;
import com.solvd.car.odb.entity.Garage;

import java.util.List;

public class GarageService {
    private IGarageDAO garageDAO = new GarageDAO();

    public List<Garage> getAllGarage() {
        return garageDAO.getAllGarages();
    }

    public Garage getGarageById(Long id) {
        return garageDAO.getGarageById(id);
    }

    public Garage getLastGarage() {
        return garageDAO.getLastGarage();
    }

    public void addGarage(Garage garage) {
        garageDAO.addGarage(garage);
    }

    public void deleteGarage(Garage garage) {
        garageDAO.deleteGarage(garage);
    }

    public void updateGarage(Garage garage) {
        garageDAO.updateGarage(garage);
    }
}
