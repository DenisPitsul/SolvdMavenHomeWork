package com.solvd.car.odb.entity;

public class Home {
    private Long id;
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id=" + id +
                ", address=" + address +
                '}';
    }
}
