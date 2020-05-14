package com.solvd.car.place_io.helper;

public interface FileOperation<G> {
    void writeAllToFile(G group);
    G readAllFromFile();
    void clearFile();
}
