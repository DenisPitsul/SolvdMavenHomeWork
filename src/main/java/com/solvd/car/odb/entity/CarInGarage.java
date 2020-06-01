package com.solvd.car.odb.entity;

public class CarInGarage {
    private Long id;
    private Car car;
    private Garage garage;

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

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    @Override
    public String toString() {
        return "CarInGarage{" +
                "id=" + id +
                ", car=" + car +
                ", garage=" + garage +
                '}';
    }
}
