package com.vitaliif.busroutechalange.graph;

import com.vitaliif.busroutechalange.service.BusStationValidator;
import com.vitaliif.busroutechalange.service.OrientedGraphReader;
import com.vitaliif.busroutechalange.service.PropertyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class OrientedGraphInitializer {

    private OrientedGraph orientedGraph;

    private final BusStationValidator validator;
    private final OrientedGraphReader orientedGraphReader;


    public OrientedGraphInitializer(BusStationValidator validator, OrientedGraphReader orientedGraphReader, PropertyHolder propertyHolder) {
        this.validator = validator;
        this.orientedGraphReader = orientedGraphReader;
    }

    @PostConstruct
    public void initialize() throws IOException {
        if (validator.isValid()) {
            orientedGraph = orientedGraphReader.initialize();
        }
    }

    public OrientedGraph getOrientedGraph() {
        return orientedGraph;
    }
}