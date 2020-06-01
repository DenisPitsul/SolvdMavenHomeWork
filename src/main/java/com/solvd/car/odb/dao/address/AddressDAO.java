package com.solvd.car.odb.dao.address;

import com.solvd.car.odb.entity.Address;
import com.solvd.car.utils.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class AddressDAO implements IAddressDAO {
    private static final Logger LOGGER = Logger.getLogger(AddressDAO.class);

    private IAddressDAO addressDAO;
    private Class<IAddressDAO> addressDAOClass = IAddressDAO.class;

    @Override
    public List<Address> getAllAddresses() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        addressDAO = session.getMapper(addressDAOClass);
        List<Address> addressList = addressDAO.getAllAddresses();
        LOGGER.info("Got all addresses");
        session.close();

        return addressList;
    }

    @Override
    public Address getAddressById(Long id) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        addressDAO = session.getMapper(addressDAOClass);
        Address address = addressDAO.getAddressById(id);
        LOGGER.info("Got address by id");
        session.close();

        return address;
    }

    @Override
    public Address getLastAddress() {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        addressDAO = session.getMapper(addressDAOClass);
        Address address = addressDAO.getLastAddress();
        LOGGER.info("Got last address");
        session.close();

        return address;
    }

    @Override
    public void addAddress(Address address) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        addressDAO = session.getMapper(addressDAOClass);
        addressDAO.addAddress(address);
        LOGGER.info("Added new address");
        session.commit();
        session.close();
    }

    @Override
    public void deleteAddress(Address address) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        addressDAO = session.getMapper(addressDAOClass);
        addressDAO.deleteAddress(address);
        LOGGER.info("Deleted address");
        session.commit();
        session.close();
    }

    @Override
    public void updateAddress(Address address) {
        SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession();
        addressDAO = session.getMapper(addressDAOClass);
        addressDAO.updateAddress(address);
        LOGGER.info("Updated address");
        session.commit();
        session.close();
    }
}
