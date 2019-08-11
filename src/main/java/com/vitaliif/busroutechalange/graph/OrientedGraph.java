package com.vitaliif.busroutechalange.graph;

import java.util.*;

public class OrientedGraph {

    private final Map<Integer, List<Integer>> graph;

    public OrientedGraph() {
        graph = new HashMap<>();
    }

    public Map<Integer, List<Integer>> getGraph() {
        return graph;
    }

    public void addArc(int sourceVertex, int destinationVertex) {
        if (graph.get(sourceVertex) == null) {
            graph.put(sourceVertex, new ArrayList<>(Collections.singletonList(destinationVertex)));
        } else {
            graph.get(sourceVertex).add(destinationVertex);

        }
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
