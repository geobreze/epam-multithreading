package com.epam.multithreading.parse;

import java.io.InputStream;
import java.util.List;

public interface Parser<T> {
    List<T> parse(InputStream inputStream, Class<T> objectClass);
}
