package com.parkinglot;

import java.util.*;

public class ParkingLot {
    private final Map<Ticket, Car> parkingRecords = new HashMap<>();
    private final int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (this.isFull()) {
            return null;
        }
        Ticket ticket = getTicket(car);
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car car = parkingRecords.get(ticket);
        return car;
    }

    private Ticket getTicket(Car car) {
        return new Ticket(car);
    }

    private boolean isFull() {
        return parkingRecords.size() >= this.capacity;
    }
}