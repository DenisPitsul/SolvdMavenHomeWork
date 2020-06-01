package com.solvd.car.odb.dao.cars_in_garage;

import com.solvd.car.odb.entity.CarInGarage;

import java.util.List;

public interface ICarsInGarageDAO {

    List<CarInGarage> getAllCarsInGarages();

    CarInGarage getCarInGarageById(Long id);

    List<CarInGarage> getCarsInGarageByGarageId(Long garageId);

    void addCarInGarage(CarInGarage carInGarage);

    void deleteCarInGarage(CarInGarage carInGarage);

    void updateCarInGarage(CarInGarage carInGarage);

}
