package com.solvd.car.place_io.helper;

public interface JsonOperation<G> {
    void writeToJsonFile(G group, String filePath);
    G readJsonFile(String filePath);
}
