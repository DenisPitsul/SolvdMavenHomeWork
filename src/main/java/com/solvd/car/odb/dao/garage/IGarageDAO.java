package com.solvd.car.odb.dao.garage;

import com.solvd.car.odb.entity.Garage;

import java.util.List;

public interface IGarageDAO {

    List<Garage> getAllGarages();

    Garage getGarageById(Long id);

    Garage getLastGarage();

    void addGarage(Garage garage);

    void deleteGarage(Garage garage);

    void updateGarage(Garage garage);

}
