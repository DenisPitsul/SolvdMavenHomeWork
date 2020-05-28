package com.solvd.car.odb.dao.car;

import com.solvd.car.odb.entity.Car;
import org.apache.log4j.Logger;

import java.util.List;

public interface ICarDAO {

    void add(Car car);
    List<Car> getAll();
    Car getById(long id);
    void remove(long id);
}
