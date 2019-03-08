package com.epam.multithreading.logic;

import com.epam.multithreading.entity.Passenger;
import com.epam.multithreading.entity.Taxi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// TODO:sinleton
public class UberPark {
    private static final Logger LOGGER = LogManager.getLogger(UberPark.class);
    private static final Lock lock = new ReentrantLock();
    private static AtomicReference<UberPark> instance = new AtomicReference<>();
    private final OptimalTaxiCalculator optimalTaxiCalculator = new OptimalTaxiCalculator();
    private final TaxiLogic taxiLogic = new TaxiLogic(lock);
    private List<Taxi> taxis;
    private Semaphore semaphore;

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

    public void processRequest(Passenger passenger) {
        try {
            semaphore.acquire();
            int destination = passenger.getDestination();
            List<Taxi> freeTaxis = optimalTaxiCalculator.findFree(taxis);
            Taxi optimalTaxi = optimalTaxiCalculator.calculateOptimal(freeTaxis, destination);
            taxiLogic.transportPassenger(optimalTaxi, passenger, destination);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        } finally {
            semaphore.release();
        }
    }

}
