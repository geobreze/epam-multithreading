package com.epam.multithreading;

import com.epam.multithreading.entity.Passenger;
import com.epam.multithreading.entity.Taxi;
import com.epam.multithreading.exception.ParseException;
import com.epam.multithreading.logic.UberPark;
import com.epam.multithreading.parse.Parser;
import com.epam.multithreading.parse.PassengerParser;
import com.epam.multithreading.parse.TaxiParser;
import com.epam.multithreading.requestor.AsyncRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Director {
    private static final Logger LOGGER = LogManager.getLogger(Director.class);
    private static final String TAXIS_FILE = "taxis.json";
    private static final String PASSENGERS_FILE = "passengers.json";

    public static void main(String[] args) {
        ClassLoader directorClassLoader = Director.class.getClassLoader();
        InputStream taxisStream = directorClassLoader.getResourceAsStream(TAXIS_FILE);
        InputStream passengersStream = directorClassLoader.getResourceAsStream(PASSENGERS_FILE);
        Parser<Taxi> taxiParser = new TaxiParser();
        Parser<Passenger> passengerParser = new PassengerParser();

        try {
            List<Taxi> taxis = taxiParser.parse(taxisStream);
            List<Passenger> passengers = passengerParser.parse(passengersStream);
            UberPark park = UberPark.getInstance();
            park.setTaxis(taxis);

            List<AsyncRequest> requests = new ArrayList<>();
            for (Passenger passenger : passengers) {
                requests.add(new AsyncRequest(passenger, UberPark.getInstance()));
            }

            ExecutorService executorService = Executors.newCachedThreadPool();
            for (AsyncRequest request : requests) {
                executorService.submit(request);
            }

            executorService.shutdown();
        } catch (ParseException e) {
            LOGGER.error(e);
        }
    }
}
