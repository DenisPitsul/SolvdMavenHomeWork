package com.solvd.car.odb.dao.address;

import com.solvd.car.odb.entity.Address;

import java.util.List;

public interface IAddressDAO {

    List<Address> getAllAddresses();

    Address getAddressById(Long id);

    Address getLastAddress();

    void addAddress(Address address);

    void deleteAddress(Address address);

    void updateAddress(Address address);

}
