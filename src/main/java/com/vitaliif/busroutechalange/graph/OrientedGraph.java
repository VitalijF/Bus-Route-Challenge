package com.vitaliif.busroutechalange.graph;

import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrientedGraph that = (OrientedGraph) o;
        return Objects.equals(graph, that.graph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graph);
    }
}
