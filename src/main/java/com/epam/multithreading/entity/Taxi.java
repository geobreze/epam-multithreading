package com.epam.multithreading.entity;

import java.util.Objects;

public class Taxi {
    private final Integer id;
    private int coordinate;
    private boolean free;

    public Taxi(Integer id) {
        this.id = id;
        free = true;
    }

    public Taxi(Integer id, int coordinate) {
        this(id);
        this.coordinate = coordinate;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Taxi taxi = (Taxi) o;
        return coordinate == taxi.coordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "id=" + id +
                ", coordinate=" + coordinate +
                ", is free=" + free +
                '}';
    }
}
