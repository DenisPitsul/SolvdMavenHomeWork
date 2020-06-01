package com.solvd.car.odb.dao.car;

import com.solvd.car.odb.entity.Car;

import java.util.List;

public interface ICarDAO {

    List<Car> getAllCars();

    Car getCarById(Long id);

    void addCar(Car car);

    void deleteCar(Car car);

    void updateCar(Car car);

}
