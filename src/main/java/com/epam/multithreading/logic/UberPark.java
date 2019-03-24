package com.epam.multithreading.logic;

import com.epam.multithreading.entity.Passenger;
import com.epam.multithreading.entity.Taxi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UberPark {
    private static final Logger LOGGER = LogManager.getLogger(UberPark.class);
    private static final Lock lock = new ReentrantLock();
    private static AtomicReference<UberPark> instance = new AtomicReference<>();
    private final OptimalTaxiCalculator optimalTaxiCalculator = new OptimalTaxiCalculator();
    private List<Taxi> taxis = new ArrayList<>();
    private Semaphore semaphore = new Semaphore(0);

    private UberPark() {
    }

    public void setTaxis(List<Taxi> taxis) {
        this.taxis = taxis;
        this.semaphore = new Semaphore(taxis.size());
    }

    public static UberPark getInstance() {
        UberPark park = instance.get();
        if (park == null) {
            lock.lock();
            park = instance.get();
            if (park == null) {
                LOGGER.info("Creating UberPark instance");
                park = new UberPark();
                instance.set(park);
            }
            lock.unlock();
        }
        return park;
    }

    public Taxi askForTaxi(Passenger passenger) {
        Taxi optimalTaxi = taxis.get(0);
        try {
            semaphore.acquire();
            int destination = passenger.getDestination();
            lock.lock();
            List<Taxi> freeTaxis = optimalTaxiCalculator.findFree(taxis);
            optimalTaxi = optimalTaxiCalculator.calculateOptimal(freeTaxis, destination);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        } finally {
            lock.unlock();
            semaphore.release();
        }
        return optimalTaxi;
    }
}
