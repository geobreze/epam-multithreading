package com.epam.multithreading.parse;

import com.epam.multithreading.entity.Passenger;
import com.epam.multithreading.entity.Taxi;
import com.google.gson.JsonSyntaxException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


public class JsonListParserTest {
    private static final String CORRECT_TAXIS_JSON = "src/test/resources/correct_taxis.json";
    private static final String CORRECT_PASSENGERS_JSON = "src/test/resources/correct_passengers.json";
    private static final String INCORRECT_PASSENGERS_JSON = "src/test/resources/incorrect_passengers.json";
    private static final List<Taxi> TAXI_LIST = Arrays.asList(
            new Taxi(1, -916),
            new Taxi(2, -806),
            new Taxi(3, -552),
            new Taxi(4, -436)
    );
    private static final List<Passenger> PASSENGER_LIST = Arrays.asList(
            new Passenger(1, -489, 66),
            new Passenger(2, -855, -489),
            new Passenger(3, -218, -973),
            new Passenger(4, -167, 27)
    );
    private final Parser<Taxi> taxiParser = new JsonListParser<>();
    private final Parser<Passenger> passengerParser = new JsonListParser<>();

    @Test
    public void parseShouldParseTaxisCorrectly() throws FileNotFoundException {
        InputStream taxisStream = new FileInputStream(CORRECT_TAXIS_JSON);
        List<Taxi> parsedTaxis = taxiParser.parse(taxisStream, Taxi.class);

        Assert.assertEquals(parsedTaxis, TAXI_LIST);
    }

    @Test
    public void parseShouldParsePassengersCorrectly() throws FileNotFoundException {
        InputStream taxisStream = new FileInputStream(CORRECT_PASSENGERS_JSON);
        List<Passenger> parsedPassengers = passengerParser.parse(taxisStream, Passenger.class);

        Assert.assertEquals(parsedPassengers, PASSENGER_LIST);
    }

    @Test(expectedExceptions = JsonSyntaxException.class)
    public void parseShouldThrowJsonSyntaxExceptionWhenIncorrectJsonSupplied() throws FileNotFoundException {
        InputStream taxisStream = new FileInputStream(INCORRECT_PASSENGERS_JSON);
        passengerParser.parse(taxisStream, Passenger.class);
    }
}