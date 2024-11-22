package com.parkinglot;

public interface ParkingBoyStrategy {
    Ticket park(Car car) throws NoAvailablePositionException;
}
