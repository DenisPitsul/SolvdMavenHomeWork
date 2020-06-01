package com.solvd.car.odb.service;

import com.solvd.car.odb.dao.car.CarDAO;
import com.solvd.car.odb.dao.car.ICarDAO;
import com.solvd.car.odb.entity.Car;

import java.util.List;

public class CarService {
    private ICarDAO carDAO = new CarDAO();

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getCarById(Long id) {
        return carDAO.getCarById(id);
    }

    public void addCar(Car car) {
        carDAO.addCar(car);
    }

    public void deleteCar(Car car) {
        carDAO.deleteCar(car);
    }

    public void updateCar(Car car) {
        carDAO.updateCar(car);
    }
}
