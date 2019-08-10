package com.vitaliif.busroutechalange.service.impl;

import com.vitaliif.busroutechalange.graph.OrientedGraph;
import com.vitaliif.busroutechalange.service.OrientedGraphReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileOrientedGraphReader implements OrientedGraphReader {

    private String pathToBusStationsFile;

    public FileOrientedGraphReader(@Value("${file.busStations.path}") String pathToBusStationsFile) {
        this.pathToBusStationsFile = pathToBusStationsFile;
    }

    @Override
    public OrientedGraph initialize() throws IOException {

        final OrientedGraph orientedGraph = new OrientedGraph();
        final Path path = Paths.get(pathToBusStationsFile);


        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            //Skip first line
            reader.readLine();

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                final String[] chars = currentLine.split(" ");
                for (int i = 1; i < chars.length - 1; i++) {
                    orientedGraph.addArc(Integer.parseInt(chars[i]), Integer.parseInt(chars[i + 1]));
                }
            }
        }
        return orientedGraph;
    }
}
