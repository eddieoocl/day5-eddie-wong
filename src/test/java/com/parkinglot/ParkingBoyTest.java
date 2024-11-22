package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ParkingBoyTest {
    @Test
    public void should_return_ticket_when_park_given_parking_boy() {
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car("A12345");

        try {
            // When
            Ticket ticket = parkingBoy.park(car);

            // Then
            assertNotNull(ticket);
        } catch (NoAvailablePositionException exception) {
            fail();
            return;
        }
    }
}
