package com.vitaliif.busroutechalange.service.impl;

import com.vitaliif.busroutechalange.exception.IllegalBusStationFileException;
import com.vitaliif.busroutechalange.service.PropertyHolder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BusStationsFileValidatorTest {

    private BusStationsFileValidator fileValidator;
    private PropertyHolder propertyHolder;

    @Rule
    public ExpectedException exception = ExpectedException.none();;

    @Before
    public void initialize() {
        propertyHolder = Mockito.mock(PropertyHolder.class);
        Mockito.when(propertyHolder.getMaximumBusStations()).thenReturn(25);
        Mockito.when(propertyHolder.getMinimumBusStations()).thenReturn(1);
        Mockito.when(propertyHolder.getMaximumStationsNumber()).thenReturn(181);
        Mockito.when(propertyHolder.getMinimumBusRoads()).thenReturn(2);
        Mockito.when(propertyHolder.getMaximumBusRoads()).thenReturn(20);

        fileValidator = new BusStationsFileValidator(propertyHolder);
    }

    @Test
    public void testMaxBusRoads() {
        Mockito.when(propertyHolder.getBusStationsFilePath())
                .thenReturn("src/test/resources/busStations/invalid/maxBusRoads.txt");
        exception.expect(IllegalBusStationFileException.class);
        exception.expectMessage("Wrong number of roads. It must be between [2] and [20]");

        fileValidator.isValid();
    }

    @Test
    public void testMinBusRoads() {
        Mockito.when(propertyHolder.getBusStationsFilePath())
                .thenReturn("src/test/resources/busStations/invalid/minBusRoads.txt");
        exception.expect(IllegalBusStationFileException.class);
        exception.expectMessage("Wrong number of roads. It must be between [2] and [20]");

        fileValidator.isValid();
    }

    @Test
    public void testNonNumericSymbolsForBusId() {
        Mockito.when(propertyHolder.getBusStationsFilePath())
                .thenReturn("src/test/resources/busStations/invalid/nonNumericSymbolsForBusId.txt");
        exception.expect(IllegalBusStationFileException.class);
        exception.expectMessage("File can only contain digits and spaces");

        fileValidator.isValid();
    }

    @Test
    public void testMaxTotalStations() {
        Mockito.when(propertyHolder.getBusStationsFilePath())
                .thenReturn("src/test/resources/busStations/invalid/maxTotalStations.txt");
        exception.expect(IllegalBusStationFileException.class);
        exception.expectMessage("Wrong number of total stations. It must be between [1] and [181]");

        fileValidator.isValid();
    }

    @Test
    public void testRepeatableStationsForBusId() {
        Mockito.when(propertyHolder.getBusStationsFilePath())
                .thenReturn("src/test/resources/busStations/invalid/stationsRepeatForSameBusId.txt");
        exception.expect(IllegalBusStationFileException.class);
        exception.expectMessage("Stations couldn't repeat for same bus id. Bus id = [0]");

        fileValidator.isValid();
    }

    @Test
    public void testMaxStationsForBus() {
        Mockito.when(propertyHolder.getBusStationsFilePath())
                .thenReturn("src/test/resources/busStations/invalid/minStationsForBus.txt");
        exception.expect(IllegalBusStationFileException.class);
        exception.expectMessage("Wrong number of stations for bus. It must be between [1] and [25]. Bus id = [0]");

        fileValidator.isValid();
    }

    @Test
    public void testMinStationsForBus() {
        Mockito.when(propertyHolder.getBusStationsFilePath())
                .thenReturn("src/test/resources/busStations/invalid/maxStationsForBus.txt");
        exception.expect(IllegalBusStationFileException.class);
        exception.expectMessage("Wrong number of stations for bus. It must be between [1] and [25]. Bus id = [0]");

        fileValidator.isValid();
    }

    @Test
    public void testWrongDestination() {
        Mockito.when(propertyHolder.getBusStationsFilePath())
                .thenReturn("src/test/resources/busStations/wrongPath/invalidFile.txt");
        exception.expect(IllegalBusStationFileException.class);
        exception.expectMessage("Exception occurs while file reading");

        fileValidator.isValid();
    }

    @Test
    public void testValid() {
        Mockito.when(propertyHolder.getBusStationsFilePath())
                .thenReturn("src/test/resources/busStations/valid/stations.txt");

        boolean valid = fileValidator.isValid();

        Assert.assertTrue(valid);

    }
}
