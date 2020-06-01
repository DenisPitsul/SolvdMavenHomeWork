package com.solvd.car.odb.service;

import com.solvd.car.odb.dao.address.AddressDAO;
import com.solvd.car.odb.dao.address.IAddressDAO;
import com.solvd.car.odb.entity.Address;

import java.util.List;

public class AddressService {
    private IAddressDAO addressDAO = new AddressDAO();

    public List<Address> getAllAddresses() {
        return addressDAO.getAllAddresses();
    }

    public Address getAddressById(Long id) {
        return addressDAO.getAddressById(id);
    }

    public Address getLastAddress() {
        return addressDAO.getLastAddress();
    }

    public void addAddress(Address address) {
        addressDAO.addAddress(address);
    }

    public void deleteAddress(Address address) {
        addressDAO.deleteAddress(address);
    }

    public void updateAddress(Address address) {
        addressDAO.updateAddress(address);
    }
}
