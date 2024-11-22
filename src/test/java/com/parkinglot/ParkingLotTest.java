package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {
    @Test
    public void should_return_ticket_when_park_given_a_car() {
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("A12345");

        // When
        Ticket ticket = parkingLot.park(car);

        // Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_car_when_fetch_given_a_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("A12345");
        Ticket ticket = parkingLot.park(car);

        // When
        Car fetchedCar = parkingLot.fetch(ticket);

        // Then
        assertNotNull(fetchedCar);
    }

    @Test
    public void should_return_corrsponding_cars_when_fetch_given_two_cars() {
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car("A12345");
        Ticket ticket1 = parkingLot.park(car1);
        Car car2 = new Car("B12345");
        Ticket ticket2 = parkingLot.park(car2);

        // When
        Car fetchedCar1 = parkingLot.fetch(ticket1);
        Car fetchedCar2 = parkingLot.fetch(ticket2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }
}
