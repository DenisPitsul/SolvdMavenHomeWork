package com.solvd.car.place;

import com.solvd.car.vehicle.Vehicle;

public interface CarPlace<T extends Vehicle> {
    void add(T car);
    T leaveThePlace(T car);
    void showInfo();
}
