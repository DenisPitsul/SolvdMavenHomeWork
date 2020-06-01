package com.solvd.car.odb.service;

import com.solvd.car.odb.dao.cars_in_garage.CarsInGarageDAO;
import com.solvd.car.odb.dao.cars_in_garage.ICarsInGarageDAO;
import com.solvd.car.odb.entity.CarInGarage;

import java.util.List;

public class CarsInGarageService {
    private ICarsInGarageDAO carsInGarageDAO = new CarsInGarageDAO();

    public List<CarInGarage> getAllCarsInGarages() {
        return carsInGarageDAO.getAllCarsInGarages();
    }

    public CarInGarage getCarInGarageById(Long id) {
        return carsInGarageDAO.getCarInGarageById(id);
    }

    public List<CarInGarage> getCarsInGarageByGarageId(Long garageId) {
        return carsInGarageDAO.getCarsInGarageByGarageId(garageId);
    }

    public void addCarInGarage(CarInGarage carInGarage) {
        carsInGarageDAO.addCarInGarage(carInGarage);
    }

    public void deleteCarInGarage(CarInGarage carInGarage) {
        carsInGarageDAO.deleteCarInGarage(carInGarage);
    }

    public void updateCarInGarage(CarInGarage carInGarage) {
        carsInGarageDAO.updateCarInGarage(carInGarage);
    }
}
