package com.solvd.car.odb.entity;

public class Garage {
    private Long id;
    private boolean isBig;
    private Home home;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isBig() {
        return isBig;
    }

    public void setBig(boolean big) {
        isBig = big;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Garage{" +
                "id=" + id +
                ", isBig=" + isBig +
                ", home=" + home +
                '}';
    }
}
