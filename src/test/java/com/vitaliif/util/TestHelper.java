package com.vitaliif.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestHelper {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T convertToObjectFromStringJson(final String source, final Class<T> targetClass) throws IOException {
        return OBJECT_MAPPER.reader().forType(targetClass).readValue(source);
    }
}
