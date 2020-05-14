package com.solvd.car.utils.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class HomesReadPOJO {
    @JsonProperty("count")
    private int countOfCreatedHomes;
    private List<HomeReadPOJO> homes;

    public HomesReadPOJO() {
        homes = new ArrayList<>();
    }

    public int getCountOfCreatedHomes() {
        return countOfCreatedHomes;
    }

    public void setCountOfCreatedHomes(int countOfCreatedHomes) {
        this.countOfCreatedHomes = countOfCreatedHomes;
    }

    public List<HomeReadPOJO> getHomes() {
        return homes;
    }

    public void setHomes(List<HomeReadPOJO> homes) {
        this.homes = homes;
    }

    @Override
    public String toString() {
        return "HomesPOJO{" +
                "countOfCreatedHomes=" + countOfCreatedHomes +
                ", homes=" + homes +
                '}';
    }
}
