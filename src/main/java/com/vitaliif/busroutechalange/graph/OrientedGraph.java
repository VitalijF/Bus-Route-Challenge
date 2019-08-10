package com.vitaliif.busroutechalange.graph;

import com.vitaliif.busroutechalange.service.BusStationValidator;
import com.vitaliif.busroutechalange.service.GraphRoadFinder;
import com.vitaliif.busroutechalange.service.OrientedGraphReader;
import com.vitaliif.busroutechalange.service.impl.BFSGraphRoadFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrientedGraph {


    private final Map<Integer, Set<Integer>> graph;

    public OrientedGraph() {
        graph = new HashMap<>();
    }

    public Map<Integer, Set<Integer>> getGraph() {
        return graph;
    }

    public void addArc(int sourceVertex, int destinationVertex) {
        graph.computeIfAbsent(sourceVertex, key -> {
            final Set<Integer> destinationVertexes = new HashSet<>();
            destinationVertexes.add(destinationVertex);
            return destinationVertexes;
        }).add(destinationVertex);
    }
}
