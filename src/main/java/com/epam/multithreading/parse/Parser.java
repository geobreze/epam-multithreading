package com.epam.multithreading.parse;

import com.epam.multithreading.exception.ParseException;

import java.io.InputStream;
import java.util.List;

public interface Parser<T> {
    List<T> parse(InputStream inputStream) throws ParseException;
}
