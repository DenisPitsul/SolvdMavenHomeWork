package com.solvd.car.odb.dao;

import com.solvd.car.odb.dao.car.ICarDAO;

public interface IDAOFactory {

    ICarDAO getCarDAO();

}
