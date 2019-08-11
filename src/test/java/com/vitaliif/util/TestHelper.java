package com.vitaliif.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestHelper {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T convertToObjectFromStringJson(final String source, final Class<T> targetClass) throws IOException {
        return OBJECT_MAPPER.reader().forType(targetClass).readValue(source);
    }

    public static String readFromFile(final String path) throws IOException {
        final StringBuilder sb = new StringBuilder();
        try (Stream<String> lines = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            lines.forEach(sb::append);
        }
        return sb.toString();
    }
}
