package com.vitaliif.busroutechalange.service.impl;


import com.vitaliif.busroutechalange.service.BusStationValidator;
import com.vitaliif.busroutechalange.service.PropertyHolder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class BusStationsFileValidator implements BusStationValidator {

    private final PropertyHolder propertyHolder;

    public BusStationsFileValidator(PropertyHolder propertyHolder) {
        this.propertyHolder = propertyHolder;
    }

    @Override
    public boolean isValid() {

        final Path path = Paths.get(propertyHolder.getBusStationsFilePath());
        checkTotalBusRoadsNumber(path);

        int totalStations = 0;
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            //Skip first line
            reader.readLine();


            String currentLine;
            while ((currentLine = reader.readLine()) != null) {

                checkRegexForBusStations(currentLine);

                final String[] stationsWithBusId = currentLine.split(" ");
                checkBusStationNumber(stationsWithBusId);
                List<String> stationsWithoutBusId = Arrays.asList(
                        Arrays.copyOfRange(stationsWithBusId, 1, stationsWithBusId.length)
                );
                checkStationsForUniqueValues(stationsWithBusId, stationsWithoutBusId);
                totalStations += stationsWithoutBusId.size();
                checkTotalNumberOfStations(totalStations);
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception occurs while file reading", e);
        }
        return true;
    }

    private void checkTotalNumberOfStations(int totalStations) {
        if (totalStations > propertyHolder.getMaximumBusStations()) {
            throw new IllegalArgumentException(String.format(
                    "Wrong number of total stations. It must be between 1 and [%d]",
                    propertyHolder.getMaximumBusStations()
            ));
        }
    }

    private void checkStationsForUniqueValues(String[] stationsWithBusId, List<String> stationsWithoutBusId) {
        final HashSet<String> uniqueStations = new HashSet<>(stationsWithoutBusId);
        if (uniqueStations.size() != stationsWithoutBusId.size()) {
            throw new IllegalArgumentException(String.format(
                    "Stations couldn't repeat for same bus id. Bus id = [%s]",
                    stationsWithBusId[0]
            ));
        }
    }

    private void checkBusStationNumber(String[] stationsWithBusId) {
        if (stationsWithBusId.length < propertyHolder.getMinimumBusStations() + 1 ||
                stationsWithBusId.length > propertyHolder.getMaximumBusStations() + 1) {
            throw new IllegalArgumentException(String.format(
                    "Wrong number of total stations. It must be between [%d] and [%d]",
                    propertyHolder.getMaximumBusStations(),
                    propertyHolder.getMaximumBusStations()
            ));
        }
    }

    private void checkRegexForBusStations(String currentLine) {
        String regexOnlySpacesAndDigits = "[0-9 ]+";
        if (!currentLine.matches(regexOnlySpacesAndDigits)) {
            throw new IllegalArgumentException("File can only contain digits and spaces");
        }
    }

    private void checkTotalBusRoadsNumber(Path path) {
        long fileLines;
        try {
            fileLines = Files.lines(path).count();
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format(
                    "Could not find file by provided path = [%s]",
                    propertyHolder.getBusStationsFilePath()
            ));
        }

        if (fileLines < propertyHolder.getMinimumBusRoads() + 1 || fileLines > propertyHolder.getMaximumBusRoads() + 1) {
            throw new IllegalArgumentException(String.format(
                    "Wrong number of roads. It must be between [%s] and [%s]",
                    propertyHolder.getMinimumBusRoads(),
                    propertyHolder.getMaximumBusRoads()
            ));
        }
    }
}