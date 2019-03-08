package com.epam.multithreading.requestor;

import com.epam.multithreading.entity.Passenger;
import com.epam.multithreading.logic.UberPark;

public class AsyncRequest implements Runnable {
    private final Passenger passenger;
    private final UberPark park;

    public AsyncRequest(Passenger passenger, UberPark park) {
        this.passenger = passenger;
        this.park = park;
    }

    @Override
    public void run() {
        park.processRequest(passenger);
    }
}
