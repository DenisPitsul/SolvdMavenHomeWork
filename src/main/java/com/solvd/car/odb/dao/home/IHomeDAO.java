package com.solvd.car.odb.dao.home;

import com.solvd.car.odb.entity.Home;

import java.util.List;

public interface IHomeDAO {

    List<Home> getAllHomes();

    Home getHomeById(Long id);

    Home getLastHome();

    void addHome(Home home);

    void deleteHome(Home home);

    void updateHome(Home home);

}
