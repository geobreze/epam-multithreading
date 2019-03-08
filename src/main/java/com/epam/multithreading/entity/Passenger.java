package com.epam.multithreading.entity;

import java.util.Objects;

public class Passenger {
    private final Integer id;
    private int coordinate;
    private int destination;

    public Passenger(Integer id) {
        this.id = id;
    }

    public Passenger(Integer id, int coordinate, int destination) {
        this.id = id;
        this.coordinate = coordinate;
        this.destination = destination;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passenger passenger = (Passenger) o;
        return coordinate == passenger.coordinate;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", coordinate=" + coordinate +
                ", destination=" + destination +
                '}';
    }
}
