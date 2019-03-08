package com.epam.multithreading.parse;

import com.epam.multithreading.entity.Taxi;
import com.epam.multithreading.exception.ParseException;

import java.io.InputStream;
import java.util.List;

public class TaxiParser extends AbstractParser<Taxi> {
    @Override
    public List<Taxi> parse(InputStream inputStream) throws ParseException {
        return super.parseClass(inputStream, Taxi.class);
    }

}
