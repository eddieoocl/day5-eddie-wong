package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void should_return_ticket_when_park_given_a_car() {
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("A12345");

        try {
            // When
            Ticket ticket = parkingLot.park(car);

            // Then
            assertNotNull(ticket);
        } catch (NoAvailablePositionException exception) {
            fail();
            return;
        }
    }

    @Test
    public void should_return_car_when_fetch_given_a_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("A12345");
        Ticket ticket;

        try {
            // When
            ticket = parkingLot.park(car);
        } catch (NoAvailablePositionException exception) {
            fail();
            return;
        }

        // When
        try {
            Car fetchedCar = parkingLot.fetch(ticket);
            assertNotNull(fetchedCar);
        } catch (UnrecognizedTicketException exception) {
            fail();
            return;
        }
    }

    @Test
    public void should_return_corrsponding_cars_when_fetch_given_two_cars() {
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car("A12345");
        Car car2 = new Car("B12345");
        Ticket ticket1;
        Ticket ticket2;
        try {
            ticket1 = parkingLot.park(car1);
            ticket2 = parkingLot.park(car2);
        } catch (NoAvailablePositionException exception) {
            fail();
            return;
        }

        // When
        // Then
        try {
            Car fetchedCar1 = parkingLot.fetch(ticket1);
            Car fetchedCar2 = parkingLot.fetch(ticket2);
            assertEquals(car1, fetchedCar1);
            assertEquals(car2, fetchedCar2);
        } catch (UnrecognizedTicketException exception) {
            fail();
        }
    }

    @Test
    public void should_throw_error_message_when_fetch_given_unrecognized_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = new Ticket(new Car("C123456"));

        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetch(ticket);
        });

        // Then
        assertEquals(exception.getMessage(), "Unrecognized parking ticket.");
    }

    @Test
    public void should_throw_error_message_when_fetch_given_used_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("A12345");
        Ticket ticket;

        try {
            ticket = parkingLot.park(car);
        } catch (NoAvailablePositionException exception) {
            fail();
            return;
        }

        try {
            parkingLot.fetch(ticket);
        } catch (UnrecognizedTicketException exception) {
            fail();
            return;
        }

        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetch(ticket);
        });

        // Then
        assertEquals(exception.getMessage(), "Unrecognized parking ticket.");
    }

    @Test
    public void should_throw_error_message_when_park_given_no_space() {
        // Given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car("A12345");

        // When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class, () -> {
            parkingLot.park(car);
        });

        // Then
        assertEquals(exception.getMessage(), "No available position.");
    }

    private String systemOut() {
        return outContent.toString();
    }
}
