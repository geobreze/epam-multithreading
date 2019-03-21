package com.epam.multithreading.parse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonListParser<T> implements Parser<T> {
    @Override
    public List<T> parse(InputStream inputStream, Class<T> entityClass) {
        Type entityListType = TypeToken.getParameterized(List.class, entityClass).getType();

        Gson gson = new Gson();
        Reader reader = new InputStreamReader(inputStream);
        return gson.fromJson(reader, entityListType);
    }
}
