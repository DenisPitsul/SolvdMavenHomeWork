package com.solvd.car.utils.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class HomesWritePOJO {
    @JsonProperty("count")
    private int countOfCreatedHomes;
    @JsonProperty("homes")
    private List<HomeWritePOJO> homes;

    public HomesWritePOJO() {
        homes = new ArrayList<>();
    }

    public int getCountOfCreatedHomes() {
        return countOfCreatedHomes;
    }

    public void setCountOfCreatedHomes(int countOfCreatedHomes) {
        this.countOfCreatedHomes = countOfCreatedHomes;
    }

    public List<HomeWritePOJO> getHomes() {
        return homes;
    }

    public void setHomes(List<HomeWritePOJO> homes) {
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
