package com.solvd.car.utils.json.model;

import com.solvd.car.place.Address;
import com.solvd.car.place.Garage;
import com.solvd.car.vehicle.Vehicle;

public class HomeWritePOJO {
    private Address address;
    private Garage<Vehicle> garage;

    public HomeWritePOJO() { }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Garage<Vehicle> getGarage() {
        return garage;
    }

    public void setGarage(Garage<Vehicle> garage) {
        this.garage = garage;
    }

    @Override
    public String toString() {
        return "HomePOJO{" +
                "address=" + address +
                ", garage=" + garage +
                '}';
    }
}
