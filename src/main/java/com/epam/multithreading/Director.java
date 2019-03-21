package com.epam.multithreading;

import com.epam.multithreading.entity.Passenger;
import com.epam.multithreading.entity.Taxi;
import com.epam.multithreading.logic.UberPark;
import com.epam.multithreading.parse.JsonListParser;
import com.epam.multithreading.parse.Parser;
import com.epam.multithreading.requestor.AsyncRequest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Director {
    private static final String TAXIS_FILE = "taxis.json";
    private static final String PASSENGERS_FILE = "passengers.json";

    public static void main(String[] args) {
        initializePark();

        List<AsyncRequest> requests = generateRequests();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (AsyncRequest request : requests) {
            executorService.submit(request);
        }

        executorService.shutdown();
    }

    private static List<AsyncRequest> generateRequests() {
        InputStream passengersStream = getResourceStream(PASSENGERS_FILE);
        Parser<Passenger> passengerParser = new JsonListParser<>();
        List<Passenger> passengers = passengerParser.parse(passengersStream, Passenger.class);

        UberPark park = UberPark.getInstance();
        List<AsyncRequest> requests = new ArrayList<>();
        for (Passenger passenger : passengers) {
            requests.add(new AsyncRequest(passenger, park));
        }
        return requests;
    }

    private static void initializePark() {
        InputStream taxisStream = getResourceStream(TAXIS_FILE);
        Parser<Taxi> taxiParser = new JsonListParser<>();
        List<Taxi> taxis = taxiParser.parse(taxisStream, Taxi.class);

        UberPark park = UberPark.getInstance();
        park.setTaxis(taxis);
    }

    private static InputStream getResourceStream(String resourcePath) {
        ClassLoader directorClassLoader = Director.class.getClassLoader();
        return directorClassLoader.getResourceAsStream(resourcePath);
    }
}
