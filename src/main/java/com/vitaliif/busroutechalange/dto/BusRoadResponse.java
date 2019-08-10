package com.vitaliif.busroutechalange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusRoadResponse {

    @JsonProperty(value = "dep_sid")
    private int depSid;

    @JsonProperty(value = "arr_sid")
    private int arrSid;

    @JsonProperty(value = "direct_bus_road")
    private boolean directBusRoad;

    public BusRoadResponse() {
    }

    public BusRoadResponse(int depSid, int arrSid, boolean directBusRoad) {
        this.depSid = depSid;
        this.arrSid = arrSid;
        this.directBusRoad = directBusRoad;
    }

    public int getDepSid() {
        return depSid;
    }

    public void setDepSid(int depSid) {
        this.depSid = depSid;
    }

    public int getArrSid() {
        return arrSid;
    }

    public void setArrSid(int arrSid) {
        this.arrSid = arrSid;
    }

    public boolean isDirectBusRoad() {
        return directBusRoad;
    }

    public void setDirectBusRoad(boolean directBusRoad) {
        this.directBusRoad = directBusRoad;
    }
}
