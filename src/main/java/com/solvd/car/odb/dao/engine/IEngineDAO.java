package com.solvd.car.odb.dao.engine;

import com.solvd.car.odb.entity.Engine;

import java.util.List;

public interface IEngineDAO {

    List<Engine> getAllEngines();

    Engine getEngineById(Long id);

    Engine getLastEngine();

    Engine getEngineByName(String name);

    void addEngine(Engine engine);

    void deleteEngine(Engine engine);

    void updateEngine(Engine engine);

}
