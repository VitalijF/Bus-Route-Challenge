package com.vitaliif.busroutechalange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    public boolean getDirectBusRoad() {
        return directBusRoad;
    }

    public void setDirectBusRoad(boolean directBusRoad) {
        this.directBusRoad = directBusRoad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusRoadResponse that = (BusRoadResponse) o;
        return depSid == that.depSid &&
                arrSid == that.arrSid &&
                directBusRoad == that.directBusRoad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depSid, arrSid, directBusRoad);
    }
}
