package com.solvd.car.odb.service;

import com.solvd.car.odb.dao.parking.IParkingDAO;
import com.solvd.car.odb.dao.parking.ParkingDAO;
import com.solvd.car.odb.entity.ParkedCar;

import java.util.List;

public class ParkingService {
    private IParkingDAO parkingDAO = new ParkingDAO();

    public List<ParkedCar> getAllParkedCars() {
        return parkingDAO.getAllParkedCars();
    }

    public ParkedCar getParkedCarById(Long id) {
        return parkingDAO.getParkedCarById(id);
    }

    public void addParkedCar(ParkedCar parkedCar) {
        parkingDAO.addParkedCar(parkedCar);
    }

    public void deleteParkedCar(ParkedCar parkedCar) {
        parkingDAO.deleteParkedCar(parkedCar);
    }

    public void updateParkedCar(ParkedCar parkedCar) {
        parkingDAO.updateParkedCar(parkedCar);
    }
}
