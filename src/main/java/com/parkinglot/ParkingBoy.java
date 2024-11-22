package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy implements ParkingBoyStrategy {
    private final List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy() {}

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public Ticket park(Car car) throws NoAvailablePositionException {
        ParkingLot parkingLot = parkingLots.stream().filter(pl -> !pl.isFull()).findFirst().orElse(null);
        if (parkingLot == null) {
            throw new NoAvailablePositionException();
        }
        return parkingLot.park(car);
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

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
