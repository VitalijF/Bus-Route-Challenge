package com.vitaliif.busroutechalange.service;

import com.vitaliif.busroutechalange.dto.BusRoadResponse;

public interface BusRoadChecker {

    BusRoadResponse checkRoadExisting(final int depSid, final int arrSid);
}
