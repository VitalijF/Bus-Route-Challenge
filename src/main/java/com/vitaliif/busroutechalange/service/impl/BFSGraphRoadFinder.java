package com.vitaliif.busroutechalange.service.impl;

import com.vitaliif.busroutechalange.graph.OrientedGraph;
import com.vitaliif.busroutechalange.service.GraphRoadFinder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BFSGraphRoadFinder implements GraphRoadFinder {

    /**
     * Check if road between two vertexes exists for provided oriented graph. For searching uses BFS algorithm
     *
     * @param graph oriented graph
     * @param sourceVertex start vertex
     * @param destinationVertex destination vertex
     * @return true - if road exist, otherwise - false
     *
     *
     */
    @Override
    public boolean isRoadExist(final OrientedGraph graph, final int sourceVertex, final int destinationVertex) {

        final Map<Integer, Set<Integer>> orientedGraph = graph.getGraph();

        // Declare map which will contain visitedVertexes
        final Map<Integer, Boolean> visitedVertexes = new HashMap<>();

        // Declare queue for containing vertexes which need to be visited
        final LinkedList<Integer> candidatesForVising = new LinkedList<>();

        // Mark source vertex as visited and put it into queue
        visitedVertexes.put(sourceVertex, true);
        candidatesForVising.add(sourceVertex);

        while (candidatesForVising.size() != 0) {

            //Poll vertex from queue
            int currentVertex = candidatesForVising.poll();

            final Set<Integer> subVertexes = orientedGraph.get(currentVertex);

            //Skip iteration when vertex has no childes
            if (subVertexes == null) {
                continue;
            }

            // If one of sub vertex equals with destination then road exists
            if (subVertexes.contains(destinationVertex)) {
                return true;
            }

            // Go through all sub vertexes
            for (int currentSubVertex : subVertexes) {

                // Check if current sub vertex was been visited
                if (visitedVertexes.get(currentSubVertex) != Boolean.TRUE) {

                    visitedVertexes.put(currentVertex, true);

                    //If sub vertex has childes than add it to candidates for visiting
                    if (orientedGraph.get(currentSubVertex) != null) {
                        candidatesForVising.add(currentSubVertex);
                    }
                }
            }
        }
        return false;
    }

}
