package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void should_return_car_when_fetch_given_a_ticket_and_parking_boy() {
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car("A12345");
        Ticket ticket;

        try {
            // When
            ticket = parkingBoy.park(car);
        } catch (NoAvailablePositionException exception) {
            fail();
            return;
        }

        // When
        try {
            Car fetchedCar = parkingBoy.fetch(ticket);
            assertNotNull(fetchedCar);
        } catch (UnrecognizedTicketException exception) {
            fail();
            return;
        }
    }

    @Test
    public void should_return_corrsponding_cars_when_fetch_given_two_cars_and_parking_boy() {
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car("A12345");
        Car car2 = new Car("B12345");
        Ticket ticket1;
        Ticket ticket2;
        try {
            ticket1 = parkingBoy.park(car1);
            ticket2 = parkingBoy.park(car2);
        } catch (NoAvailablePositionException exception) {
            fail();
            return;
        }

        // When
        // Then
        try {
            Car fetchedCar1 = parkingBoy.fetch(ticket1);
            Car fetchedCar2 = parkingBoy.fetch(ticket2);
            assertEquals(car1, fetchedCar1);
            assertEquals(car2, fetchedCar2);
        } catch (UnrecognizedTicketException exception) {
            fail();
        }
    }

    @Test
    public void should_throw_error_message_when_fetch_given_unrecognized_ticket_and_parking_boy() {
        // Given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = new Ticket(new Car("C123456"));

        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(ticket);
        });

        // Then
        assertEquals(exception.getMessage(), "Unrecognized parking ticket.");
    }

    @Test
    public void should_throw_error_message_when_fetch_given_used_ticket_and_parking_boy() {
        // Given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car("A12345");
        Ticket ticket;

        try {
            ticket = parkingBoy.park(car);
        } catch (NoAvailablePositionException exception) {
            fail();
            return;
        }

        try {
            parkingBoy.fetch(ticket);
        } catch (UnrecognizedTicketException exception) {
            fail();
            return;
        }

        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(ticket);
        });

        // Then
        assertEquals(exception.getMessage(), "Unrecognized parking ticket.");
    }

    @Test
    public void should_throw_error_message_when_park_given_no_space_and_parking_boy() {
        // Given
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car("A12345");

        // When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class, () -> {
            parkingBoy.park(car);
        });

        // Then
        assertEquals(exception.getMessage(), "No available position.");
    }

    @Test
    public void should_park_first_parking_lot_when_park_given_two_parking_lots() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy();
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

    @Test
    public void should_park_second_parking_lot_when_park_given_two_parking_lots_and_parking_lot_one_is_full() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);

        Car car1 = new Car("A12345");
        Car car2 = new Car("B12345");

        try {
            // When
            parkingBoy.park(car1);
            Ticket ticket = parkingBoy.park(car2);

            // Then
            assertNotNull(ticket);
            assertEquals(parkingLot1.getSize(), 1);
            assertEquals(parkingLot2.getSize(), 1);
        } catch (NoAvailablePositionException exception) {
            fail();
        }
    }

    @Test
    public void should_fetch_corrsponding_ticket_lot_when_fetch_given_two_parking_lots_and_one_car_in_each_parking_lot() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);

        Car car1 = new Car("A12345");
        Car car2 = new Car("B12345");

        try {
            // When
            Ticket ticket1 = parkingBoy.park(car1);
            Ticket ticket2 = parkingBoy.park(car2);

            assertEquals(parkingLot1.getSize(), 1);
            assertEquals(parkingLot2.getSize(), 1);

            Car fetchedCar1 = parkingBoy.fetch(ticket1);
            Car fetchedCar2 = parkingBoy.fetch(ticket2);

            assertEquals(car1, fetchedCar1);
            assertEquals(car2, fetchedCar2);
            assertEquals(parkingLot1.getSize(), 0);
            assertEquals(parkingLot2.getSize(), 0);

        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void should_throw_error_message_when_fetch_given_unrecognized_ticket_and_two_parking_lots() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);

        Ticket ticket = new Ticket(new Car("C123456"));

        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(ticket);
        });

        // Then
        assertEquals(exception.getMessage(), "Unrecognized parking ticket.");
    }

    @Test
    public void should_throw_error_message_when_fetch_given_used_ticket_and_two_parking_lots() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);

        Car car = new Car("A12345");
        Ticket ticket;

        try {
            ticket = parkingBoy.park(car);
        } catch (NoAvailablePositionException exception) {
            fail();
            return;
        }

        try {
            parkingBoy.fetch(ticket);
        } catch (UnrecognizedTicketException exception) {
            fail();
            return;
        }

        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(ticket);
        });

        // Then
        assertEquals(exception.getMessage(), "Unrecognized parking ticket.");
    }

    @Test
    public void should_throw_error_message_when_park_given_no_space_and_two_parking_lots() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);

        Car car = new Car("A12345");

        // When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class, () -> {
            parkingBoy.park(car);
        });

        // Then
        assertEquals(exception.getMessage(), "No available position.");
    }
}
