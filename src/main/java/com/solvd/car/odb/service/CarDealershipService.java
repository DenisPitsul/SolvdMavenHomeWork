package com.solvd.car.odb.service;

import com.solvd.car.odb.dao.car_dealership.CarDealershipDAO;
import com.solvd.car.odb.dao.car_dealership.ICarDealershipDAO;
import com.solvd.car.odb.entity.SellingCar;

import java.util.List;

public class CarDealershipService {
    private ICarDealershipDAO carDealershipDAO = new CarDealershipDAO();

    public List<SellingCar> getAllSellingCars() {
        return carDealershipDAO.getAllSellingCars();
    }

    public SellingCar getSellingCarById(Long id) {
        return carDealershipDAO.getSellingCarById(id);
    }

    public void addSellingCar(SellingCar sellingCar) {
        carDealershipDAO.addSellingCar(sellingCar);
    }

    public void deleteSellingCar(SellingCar sellingCar) {
        carDealershipDAO.deleteSellingCar(sellingCar);
    }

    public void updateSellingCar(SellingCar sellingCar) {
        carDealershipDAO.updateSellingCar(sellingCar);
    }
}
