package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    public void should_park_second_parking_lot_when_park_given_two_parking_lots_with_second_parking_lot_has_more_available_capacity() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(6);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SmartParkingBoy parkingBoy = new SmartParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);

        Car car = new Car("A12345");

        try {
            // When
            Ticket ticket = parkingBoy.park(car);

            // Then
            assertNotNull(ticket);
            assertEquals(parkingLot1.getSize(), 0);
            assertEquals(parkingLot2.getSize(), 1);
        } catch (NoAvailablePositionException exception) {
            fail();
        }
    }

    @Test
    public void should_park_first_parking_lot_when_park_given_two_parking_lots_with_equal_available_capacity() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SmartParkingBoy parkingBoy = new SmartParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);

        Car car = new Car("A12345");

        try {
            // When
            Ticket ticket = parkingBoy.park(car);

            // Then
            assertNotNull(ticket);
            assertEquals(parkingLot1.getSize(), 1);
            assertEquals(parkingLot2.getSize(), 0);
        } catch (NoAvailablePositionException exception) {
            fail();
        }
    }
}
