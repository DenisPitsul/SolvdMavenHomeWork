package com.solvd.car.odb.entity;

public class ParkedCar {
    private Long id;
    private Car car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "ParkedCar{" +
                "id=" + id +
                ", car=" + car +
                '}';
    }
}
