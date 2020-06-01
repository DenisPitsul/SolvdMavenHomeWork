package com.solvd.car.odb.dao.car_detail;

import com.solvd.car.odb.entity.CarDetail;

import java.util.List;

public interface ICarDetailDAO {

    List<CarDetail> getAllCarsDetail();

    CarDetail getCarDetailById(Long id);

    CarDetail getLastCarDetail();

    void addCarDetail(CarDetail carDetail);

    void deleteCarDetail(CarDetail carDetail);

    void updateCarDetail(CarDetail carDetail);

}
