package com.epam.multithreading.parse;

import com.epam.multithreading.exception.ParseException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParser<T> implements Parser<T> {
    private static final String ENCODING = "UTF-8";

    public List<T> parseClass(InputStream inputStream, Class<T> elementClass) throws ParseException {
        String jsonString;
        try {
            jsonString = IOUtils.toString(inputStream, ENCODING);
        } catch (IOException e) {
            throw new ParseException("Invalid input stream", e);
        }
        JsonParser jsonParser = new JsonParser();
        JsonElement arrayElement = jsonParser.parse(jsonString);
        return parseJsonArray(arrayElement.getAsJsonArray(), elementClass);
    }

    private List<T> parseJsonArray(JsonArray taxisArray, Class<T> elementClass) {
        List<T> elements = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < taxisArray.size(); ++i) {
            JsonElement taxiElement = taxisArray.get(i);
            T element = gson.fromJson(taxiElement, elementClass);
            elements.add(element);
        }
        return elements;
    }
}
