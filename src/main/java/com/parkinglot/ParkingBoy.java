package com.parkinglot;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) throws NoAvailablePositionException {
        return this.parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) throws UnrecognizedTicketException {
        return this.parkingLot.fetch(ticket);
    }
}
