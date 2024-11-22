package com.parkinglot;

public class NoAvailablePositionException extends Exception {
    public NoAvailablePositionException() {
        super("No available position.");
    }
}
