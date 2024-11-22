package com.parkinglot;

public class SuperParkingBoy extends ParkingBoy implements ParkingBoyStrategy {
    public SuperParkingBoy() {
    }

    public SuperParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public Ticket park(Car car) throws NoAvailablePositionException {
        ParkingLot parkingLot = this.getParkingLots().stream()
                .sorted((parkingLot1, parkingLot2) -> Float.compare(parkingLot2.getAvailablePositionRate(), parkingLot1.getAvailablePositionRate()))
                .filter(pl -> !pl.isFull()).findFirst().orElse(null);
        if (parkingLot == null) {
            throw new NoAvailablePositionException();
        }
        return parkingLot.park(car);
    }
}
