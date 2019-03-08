package com.epam.multithreading.logic;

import com.epam.multithreading.entity.Passenger;
import com.epam.multithreading.entity.Taxi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;

public class TaxiLogic {
    private static final Logger LOGGER = LogManager.getLogger(TaxiLogic.class);
    private final Lock lock;

    public TaxiLogic(Lock lock) {
        this.lock = lock;
    }

    public void transportPassenger(Taxi taxi, Passenger passenger, int destination) {
        lock.lock();
        if (!taxi.isFree()) {
            String message = taxi + " is already driving";
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }
        taxi.setFree(false);
        LOGGER.info("{} started a ride with {} to {}", taxi, passenger, destination);
        passenger.setCoordinate(destination);
        taxi.setCoordinate(destination);
        taxi.setFree(true);
        lock.unlock();
    }

}
