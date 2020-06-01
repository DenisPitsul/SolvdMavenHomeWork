package com.solvd.car.odb.entity;

public class Address {
    private Long id;
    private String city;
    private String district;
    private String village;
    private String street;
    private int houseNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{");
        if (city != null)
            sb.append("city='").append(city).append('\'');
        if (district != null)
            sb.append(", district='").append(district).append('\'');
        if (village != null)
            sb.append(", village='").append(village).append('\'');
        if (street != null)
            sb.append(", street='").append(street).append('\'');

        sb.append(", homeNumber='").append(houseNumber).append('\'');
        sb.append('}');

        return sb.toString();
    }}
