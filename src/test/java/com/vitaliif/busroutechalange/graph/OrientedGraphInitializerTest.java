package com.vitaliif.busroutechalange.graph;

import com.vitaliif.busroutechalange.service.BusStationValidator;
import com.vitaliif.busroutechalange.service.OrientedGraphReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class OrientedGraphInitializerTest {

    private OrientedGraphInitializer graphInitializer;

    private  BusStationValidator validator;
    private  OrientedGraphReader orientedGraphReader;


    @Before
    public void initialize() throws IOException {
        validator = Mockito.mock(BusStationValidator.class);
        orientedGraphReader = Mockito.mock(OrientedGraphReader.class);

        final OrientedGraph orientedGraph = constructExpectedGraph();
        Mockito.when(orientedGraphReader.initialize()).thenReturn(orientedGraph);

        graphInitializer =  new OrientedGraphInitializer(validator, orientedGraphReader);
    }

    @Test
    public void testInitializationFromValidFile() throws IOException {
        Mockito.when(validator.isValid()).thenReturn(true);

        graphInitializer.initialize();

        Mockito.verify(validator, Mockito.times(1)).isValid();
        Mockito.verify(orientedGraphReader, Mockito.times(1)).initialize();
        OrientedGraph orientedGraph = graphInitializer.getOrientedGraph();

        Assert.assertNotNull(orientedGraph);
        Assert.assertEquals(2, orientedGraph.getGraph().size());
        Assert.assertEquals(constructExpectedGraph(), orientedGraph);
    }

    @Test(expected = IllegalStateException.class)
    public void testInitializationFromInvalidFile() throws IOException {
        graphInitializer.initialize();

        Mockito.verify(validator, Mockito.times(1)).isValid();
        Mockito.verify(orientedGraphReader, Mockito.never()).initialize();
        graphInitializer.getOrientedGraph();

    }

    @Test(expected = RuntimeException.class)
    public void testInitializationWithExceptionInValidation() throws IOException {
        Mockito.when(validator.isValid()).thenThrow(RuntimeException.class);

        graphInitializer.initialize();

        Mockito.verify(validator, Mockito.times(1)).isValid();
        Mockito.verify(orientedGraphReader, Mockito.never()).initialize();
        OrientedGraph orientedGraph = graphInitializer.getOrientedGraph();

        Assert.assertNull(orientedGraph);
    }

    @Test(expected = IOException.class)
    public void testInitializationWithExceptionInReader() throws IOException {
        Mockito.when(validator.isValid()).thenReturn(true);
        Mockito.when(orientedGraphReader.initialize()).thenThrow(IOException.class);

        graphInitializer.initialize();

        Mockito.verify(validator, Mockito.times(1)).isValid();
        Mockito.verify(orientedGraphReader, Mockito.never()).initialize();
        OrientedGraph orientedGraph = graphInitializer.getOrientedGraph();

        Assert.assertNull(orientedGraph);
    }

    private static OrientedGraph constructExpectedGraph() {
        final OrientedGraph orientedGraph = new OrientedGraph();
        orientedGraph.addArc(1, 2);
        orientedGraph.addArc(2, 3);
        return orientedGraph;
    }
}
