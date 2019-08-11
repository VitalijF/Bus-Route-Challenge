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

    /**
     *  Receives the `dep id` and `sid id` and find if destination between sids exist
     *
     * @param depSid the given dep sid
     * @param arrSid the given arr sid
     * @return @see com.vitaliif.busroutechalange.dto.BusRoadResponse result which contains information about
     * road existing between provided sids
     */
    @GetMapping("/direct")
    public BusRoadResponse findRoadBetweenTwoStations(@RequestParam(value = "dep_sid") int depSid,
                                                      @RequestParam(value = "arr_sid") int arrSid) {
        return busRoadChecker.checkRoadExisting(depSid, arrSid);
    }

}
