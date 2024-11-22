package com.parkinglot;

import java.util.Objects;

public class Ticket {
    private Car car;

    public Ticket(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(car, ticket.car);
    }
}
