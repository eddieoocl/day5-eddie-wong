package com.parkinglot;

public class UnrecognizedTicketException extends Exception {
    public UnrecognizedTicketException() {
        super("Unrecognized parking ticket.");
    }
}
