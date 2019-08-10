package com.vitaliif.busroutechalange.service.impl;

import com.vitaliif.busroutechalange.dto.BusRoadResponse;
import com.vitaliif.busroutechalange.graph.OrientedGraph;
import com.vitaliif.busroutechalange.graph.OrientedGraphInitializer;
import com.vitaliif.busroutechalange.service.BusRoadChecker;
import com.vitaliif.busroutechalange.service.GraphRoadFinder;
import org.springframework.stereotype.Service;

@Service
public class BusRoadCheckerImpl implements BusRoadChecker {

    private final OrientedGraphInitializer orientedGraphInitializer;
    private final GraphRoadFinder graphRoadFinder;

    public BusRoadCheckerImpl(OrientedGraphInitializer orientedGraphInitializer, GraphRoadFinder graphRoadFinder) {
        this.orientedGraphInitializer = orientedGraphInitializer;
        this.graphRoadFinder = graphRoadFinder;
    }

    @Override
    public BusRoadResponse checkRoadExisting(int depSid, int arrSid) {
        OrientedGraph orientedGraph = orientedGraphInitializer.getOrientedGraph();
        boolean roadExist = graphRoadFinder.isRoadExist(orientedGraph, depSid, arrSid);

        return new BusRoadResponse(depSid, arrSid, roadExist);
    }

}
