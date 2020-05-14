package com.solvd.car.utils.json.model;

import com.solvd.car.place.Address;

public class HomeReadPOJO {
    private Address address;
    private GaragePOJO garage;

    public HomeReadPOJO() { }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public GaragePOJO getGarage() {
        return garage;
    }

    public void setGarage(GaragePOJO garage) {
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
