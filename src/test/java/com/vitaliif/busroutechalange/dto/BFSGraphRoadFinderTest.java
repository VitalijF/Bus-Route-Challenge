package com.vitaliif.busroutechalange.dto;

import com.vitaliif.busroutechalange.graph.OrientedGraph;
import com.vitaliif.busroutechalange.service.impl.BFSGraphRoadFinder;
import org.junit.Assert;
import org.junit.Test;

public class BFSGraphRoadFinderTest {

    private BFSGraphRoadFinder roadFinder = new BFSGraphRoadFinder();

    @Test
    public void testRoadExist() {
        final OrientedGraph graph = new OrientedGraph();
        graph.addArc(1, 2);
        graph.addArc(2, 3);
        graph.addArc(3, 5);
        graph.addArc(5, 8);

        Assert.assertTrue(roadFinder.isRoadExist(graph, 1 , 3));
        Assert.assertTrue(roadFinder.isRoadExist(graph, 2 , 5));
        Assert.assertTrue(roadFinder.isRoadExist(graph, 3 , 8));
        Assert.assertTrue(roadFinder.isRoadExist(graph, 5 , 8));
    }

    @Test
    public void testRoadNotExist() {
        final OrientedGraph graph = new OrientedGraph();

        graph.addArc(0, 1);
        graph.addArc(1, 2);
        graph.addArc(2, 3);
        graph.addArc(3, 4);
        graph.addArc(3, 1);
        graph.addArc(1, 6);
        graph.addArc(6, 5);
        graph.addArc(0, 6);
        graph.addArc(6, 4);

        Assert.assertFalse(roadFinder.isRoadExist(graph, 4 , 6));
        Assert.assertFalse(roadFinder.isRoadExist(graph, 5 , 0));
        Assert.assertFalse(roadFinder.isRoadExist(graph, 6 , 1));
        Assert.assertFalse(roadFinder.isRoadExist(graph, 6 , 0));

    }
}
