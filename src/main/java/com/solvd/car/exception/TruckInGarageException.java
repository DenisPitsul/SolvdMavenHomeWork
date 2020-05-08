package com.solvd.car.exception;

public class TruckInGarageException extends Exception {
    private static final String MESSAGE = "You can't add truck in small garage!";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
