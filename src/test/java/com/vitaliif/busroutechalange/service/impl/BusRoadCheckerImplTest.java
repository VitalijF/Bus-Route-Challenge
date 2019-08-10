package com.vitaliif.busroutechalange.service.impl;

import com.vitaliif.busroutechalange.dto.BusRoadResponse;
import com.vitaliif.busroutechalange.graph.OrientedGraph;
import com.vitaliif.busroutechalange.graph.OrientedGraphInitializer;
import com.vitaliif.busroutechalange.service.GraphRoadFinder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BusRoadCheckerImplTest {

    private GraphRoadFinder graphRoadFinder;

    private BusRoadCheckerImpl busRoadChecker;

    @Before
    public void initialize() {
        final OrientedGraphInitializer orientedGraphInitializer = Mockito.mock(OrientedGraphInitializer.class);
        Mockito.when(orientedGraphInitializer.getOrientedGraph()).thenReturn(new OrientedGraph());

        graphRoadFinder = Mockito.mock(BFSGraphRoadFinder.class);

        busRoadChecker = new BusRoadCheckerImpl(orientedGraphInitializer, graphRoadFinder);
    }

    @Test
    public void testBusRoadExist() {
        Mockito.when(graphRoadFinder.isRoadExist(new OrientedGraph(), 1, 2)).thenReturn(true);

        final BusRoadResponse expectedResponse = generateExpectedResponse(true);
        final BusRoadResponse actualResponse = busRoadChecker.checkRoadExisting(1, 2);

        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void testBusRoadNotExist() {
        Mockito.when(graphRoadFinder.isRoadExist(new OrientedGraph(), 1,2)).thenReturn(false);

        final BusRoadResponse expectedResponse = generateExpectedResponse(false);
        final BusRoadResponse actualResponse = busRoadChecker.checkRoadExisting(1, 2);

        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    private static BusRoadResponse generateExpectedResponse(final boolean directBusRoad) {
        return new BusRoadResponse(1, 2, directBusRoad);
    }
}
