package com.solvd.car.odb.dao.parking;

import com.solvd.car.odb.entity.ParkedCar;

import java.util.List;

public interface IParkingDAO {

    List<ParkedCar> getAllParkedCars();

    ParkedCar getParkedCarById(Long id);

    void addParkedCar(ParkedCar parkedCar);

    void deleteParkedCar(ParkedCar parkedCar);

    void updateParkedCar(ParkedCar parkedCar);

}
