package com.vitaliif.busroutechalange.service;

import com.vitaliif.busroutechalange.graph.OrientedGraph;

public interface GraphRoadFinder {

    boolean isRoadExist(final OrientedGraph graph, final int sourceVertex, final int destinationVertex);

}
