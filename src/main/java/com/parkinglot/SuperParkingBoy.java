package com.parkinglot;

import java.util.Comparator;

public class SuperParkingBoy extends ParkingBoy implements ParkingBoyStrategy {
    public SuperParkingBoy() {
    }

    public SuperParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public Ticket park(Car car) throws NoAvailablePositionException {
        return this.getParkingLots().stream()
                .sorted(Comparator.comparingDouble(ParkingLot::getAvailablePositionRate).reversed())
                .filter(pl -> !pl.isFull()).findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
