package com.solvd.car.odb.dao.car_dealership;

import com.solvd.car.odb.entity.SellingCar;

import java.util.List;

public interface ICarDealershipDAO {

    List<SellingCar> getAllSellingCars();

    SellingCar getSellingCarById(Long id);

    void addSellingCar(SellingCar sellingCar);

    void deleteSellingCar(SellingCar sellingCar);

    void updateSellingCar(SellingCar sellingCar);

}
