package com.vitaliif.busroutechalange.service.impl;


import com.vitaliif.busroutechalange.exception.IllegalBusStationFileException;
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

        int totalStations = 0;
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            //Skip first line
            checkTotalBusRoadsNumber(reader.readLine());

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {

                checkRegexForBusStations(currentLine);

                final String[] stationsWithBusId = currentLine.split(" ");
                checkBusStationNumber(stationsWithBusId);
                final List<String> stationsWithoutBusId = Arrays.asList(
                        Arrays.copyOfRange(stationsWithBusId, 1, stationsWithBusId.length)
                );
                checkStationsForUniqueValues(stationsWithBusId, stationsWithoutBusId);
                totalStations += stationsWithoutBusId.size();
                checkTotalNumberOfStations(totalStations);
            }
        } catch (IOException e) {
            throw new IllegalBusStationFileException("Exception occurs while file reading", e);
        }
        return true;
    }

    private void checkTotalNumberOfStations(int totalStations) {
        if (totalStations > propertyHolder.getMaximumStationsNumber()) {
            throw new IllegalBusStationFileException(String.format(
                    "Wrong number of total stations. It must be between [1] and [%d]",
                    propertyHolder.getMaximumStationsNumber()
            ));
        }
    }

    private void checkStationsForUniqueValues(String[] stationsWithBusId, List<String> stationsWithoutBusId) {
        final HashSet<String> uniqueStations = new HashSet<>(stationsWithoutBusId);
        if (uniqueStations.size() != stationsWithoutBusId.size()) {
            throw new IllegalBusStationFileException(String.format(
                    "Stations couldn't repeat for same bus id. Bus id = [%s]",
                    stationsWithBusId[0]
            ));
        }
    }

    private void checkBusStationNumber(String[] stationsWithBusId) {
        if (stationsWithBusId.length < propertyHolder.getMinimumBusStations() + 1 ||
                stationsWithBusId.length > propertyHolder.getMaximumBusStations() + 1) {
            throw new IllegalBusStationFileException(String.format(
                    "Wrong number of stations for bus. It must be between [%d] and [%d]. Bus id = [%s]",
                    propertyHolder.getMinimumBusStations(),
                    propertyHolder.getMaximumBusStations(),
                    stationsWithBusId[0]
            ));
        }
    }

    private void checkRegexForBusStations(String currentLine) {
        String regexOnlySpacesAndDigits = "[0-9 ]+";
        if (!currentLine.matches(regexOnlySpacesAndDigits)) {
            throw new IllegalBusStationFileException("File can only contain digits and spaces");
        }
    }

    private void checkTotalBusRoadsNumber(String currentLine) {
        checkRegexForBusStations(currentLine);
        int busRoads = Integer.parseInt(currentLine);

        if (busRoads < propertyHolder.getMinimumBusRoads() || busRoads > propertyHolder.getMaximumBusRoads()) {
            throw new IllegalBusStationFileException(String.format(
                    "Wrong number of roads. It must be between [%d] and [%d]",
                    propertyHolder.getMinimumBusRoads(),
                    propertyHolder.getMaximumBusRoads()
            ));
        }
    }
}