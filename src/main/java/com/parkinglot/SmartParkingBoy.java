package com.parkinglot;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy implements ParkingBoyStrategy {
    public SmartParkingBoy() {
    }

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public Ticket park(Car car) throws NoAvailablePositionException {
        return this.getParkingLots().stream()
                .sorted(Comparator.comparingInt(ParkingLot::getAvailableCapacity).reversed())
                .filter(pl -> !pl.isFull()).findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
