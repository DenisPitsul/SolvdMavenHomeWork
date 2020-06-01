package com.solvd.car.odb.entity;

public class Car {
    private Long id;
    private String model;
    private String color;
    private String number;
    private Integer maxSpeed;
    private Integer year;
    private Engine engine;
    private CarDetail carDetail;

    public Car() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public CarDetail getCarDetail() {
        return carDetail;
    }

    public void setCarDetail(CarDetail carDetail) {
        this.carDetail = carDetail;
    }

    public String getShortInfo() {
        return "Car{model='" + this.getModel() + "', number='" + this.getNumber() + "'}";
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", number='" + number + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", year=" + year +
                ", engine=" + engine +
                ", carDetail=" + carDetail +
                '}';
    }
}
