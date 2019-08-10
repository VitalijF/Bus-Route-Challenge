package com.vitaliif.busroutechalange.controller;

import com.vitaliif.busroutechalange.dto.BusRoadResponse;
import com.vitaliif.busroutechalange.service.BusRoadChecker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BusStationsController {

    private final BusRoadChecker busRoadChecker;

    public BusStationsController(BusRoadChecker busRoadChecker) {
        this.busRoadChecker = busRoadChecker;
    }

    @GetMapping("/direct")
    public BusRoadResponse findRoadBetweenTwoStations(@RequestParam(value = "dep_sid") int depSid,
                                                      @RequestParam(value = "arr_sid") int arrSid) {
        return busRoadChecker.checkRoadExisting(depSid, arrSid);
    }

}
