package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy() {}

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public Ticket park(Car car) throws NoAvailablePositionException {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.park(car);
            } catch (NoAvailablePositionException exception) {
                continue;
            }
        }
        throw new NoAvailablePositionException();
    }

    public Car fetch(Ticket ticket) throws UnrecognizedTicketException {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.fetch(ticket);
            } catch (UnrecognizedTicketException exception) {
                continue;
            }
        }
        throw new UnrecognizedTicketException();
    }

    public void addParkingLot(ParkingLot parkingLot) {
        if (!this.parkingLots.contains(parkingLot)) {
            this.parkingLots.add(parkingLot);
        }
    }
}
