package com.parkinglot;

import java.util.Objects;

public class Car {
    private final String license;

    public Car(String license) {
        this.license = license;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(license, car.license);
    }
}
