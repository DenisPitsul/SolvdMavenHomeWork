package com.solvd.car.place;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Address {
    private String city;
    private String district;
    private String village;
    private String street;
    @JsonProperty("house_address")
    private int houseAddress;

    public Address(String city, String district, String village, String street, int houseAddress) {
        this.city = city;
        this.district = district;
        this.village = village;
        this.street = street;
        this.houseAddress = houseAddress;
    }

    public Address() { }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getVillage() {
        return village;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseAddress() {
        return houseAddress;
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

        sb.append(", homeAddress='").append(houseAddress).append('\'');
        sb.append('}');

        return sb.toString();
    }

    /**
     * Class builder through which we will initialize the object of the class Address via setters
     * and return this address via the build () method
     */
    public static class Builder {
        private String city;
        private String district;
        private String village;
        private String street;
        private int houseAddress;

        public Builder() { }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setVillage(String village) {
            this.village = village;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setHouseNumber(int houseNumber) {
            this.houseAddress = houseNumber;
            return this;
        }

        public Address build() {
            return new Address(city, district, village, street, houseAddress);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Address address = (Address) o;
        return houseAddress == address.houseAddress &&
                Objects.equals(city, address.city) &&
                Objects.equals(district, address.district) &&
                Objects.equals(village, address.village) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, district, village, street, houseAddress);
    }
}
