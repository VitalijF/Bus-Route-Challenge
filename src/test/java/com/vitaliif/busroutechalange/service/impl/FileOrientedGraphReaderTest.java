package com.vitaliif.busroutechalange.service.impl;

import com.vitaliif.busroutechalange.graph.OrientedGraph;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class FileOrientedGraphReaderTest {

    @Test
    public void testInitializationCorrectFile() throws IOException {
        final FileOrientedGraphReader fileOrientedGraphReader =
                new FileOrientedGraphReader("src/test/resources/busStations/valid/threeRoads.txt");

        OrientedGraph actualGraph = fileOrientedGraphReader.initialize();
        OrientedGraph expectedGraph = generateExpectedGraph();

        Assert.assertNotNull(actualGraph);
        Assert.assertEquals(expectedGraph, actualGraph);

    }

    @Test(expected = IOException.class)
    public void testInitializationIncorrectFile() throws IOException {
        final FileOrientedGraphReader fileOrientedGraphReader =
                new FileOrientedGraphReader("src/test/resources/busStations/wrong/invalid.txt");
        fileOrientedGraphReader.initialize();
    }

    private static OrientedGraph generateExpectedGraph() {
        final OrientedGraph graph = new OrientedGraph();
        graph.addArc(1, 2);
        graph.addArc(2, 3);
        graph.addArc(2, 4);
        return graph;
    }

}
