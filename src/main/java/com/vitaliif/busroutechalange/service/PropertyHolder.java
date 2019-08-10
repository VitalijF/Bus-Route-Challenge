package com.vitaliif.busroutechalange.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
public class PropertyHolder {

    @Value("${file.busStations.path}")
    private String busStationsFilePath;

    @Value("${validation.bus.max.roads}")
    private int maximumBusRoads;

    @Value("${validation.bus.max.stations}")
    private int maximumBusStations;

    @Value("${validation.stations.max.number}")
    private int maximumStationsNumber;

    @Value("${validation.bus.min.roads}")
    private int minimumBusRoads;

    @Value("${validation.bus.min.stations}")
    private int minimumBusStations;

    public String getBusStationsFilePath() {
        return busStationsFilePath;
    }

    public int getMaximumBusRoads() {
        return maximumBusRoads;
    }

    public int getMaximumBusStations() {
        return maximumBusStations;
    }

    public int getMaximumStationsNumber() {
        return maximumStationsNumber;
    }

    public int getMinimumBusRoads() {
        return minimumBusRoads;
    }

    public int getMinimumBusStations() {
        return minimumBusStations;
    }
}
