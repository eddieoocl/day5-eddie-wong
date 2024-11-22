package com.parkinglot;

import java.util.Collections;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy implements ParkingBoyStrategy {
    public SmartParkingBoy() {
    }

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public Ticket park(Car car) throws NoAvailablePositionException {
        ParkingLot parkingLot = this.getParkingLots().stream()
                .sorted((parkingLot1, parkingLot2) -> parkingLot2.getAvailableCapacity() - parkingLot1.getAvailableCapacity())
                .filter(pl -> !pl.isFull()).findFirst().orElse(null);
        if (parkingLot == null) {
            throw new NoAvailablePositionException();
        }
        return parkingLot.park(car);
    }
}
