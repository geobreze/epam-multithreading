package com.epam.multithreading.parse;

import com.epam.multithreading.entity.Passenger;
import com.epam.multithreading.exception.ParseException;

import java.io.InputStream;
import java.util.List;

public class PassengerParser extends AbstractParser<Passenger> {
    @Override
    public List<Passenger> parse(InputStream inputStream) throws ParseException {
        return super.parseClass(inputStream, Passenger.class);
    }
}
