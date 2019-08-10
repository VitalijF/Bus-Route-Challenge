package com.vitaliif.busroutechalange.service;

import com.vitaliif.busroutechalange.graph.OrientedGraph;

import java.io.IOException;

public interface OrientedGraphReader {

    OrientedGraph initialize() throws IOException;
}
