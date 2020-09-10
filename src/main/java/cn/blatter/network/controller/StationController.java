package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Station;
import cn.blatter.network.service.StationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/30 22:46
 */
@Slf4j
@RestController
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "/getStation", method = RequestMethod.POST)
    public ServiceResponse list(@RequestBody Station station) {
        List<Station> stationList = stationService.findAll(station.getProjectId());
        return ServiceResponse.createBySuccess(stationList);
    }

    @RequestMapping(value = "/addStation", method = RequestMethod.POST)
    public ServiceResponse addStation(@RequestBody Station station) {
        stationService.addStation(station);
        return ServiceResponse.createBySuccess();
    }

    @RequestMapping(value = "/findStationById", method = RequestMethod.POST)
    public ServiceResponse findById(@RequestBody Station station) {
        Station stationList = stationService.findById(station.getId());
        return ServiceResponse.createBySuccess(stationList);
    }

    @PostMapping(value = "/deleteStation")
    public ServiceResponse deleteStation(@RequestBody Station station) {
        stationService.deleteStation(station.getId());
        return ServiceResponse.createBySuccess();
    }

    @PostMapping(value = "/setStation")
    public ServiceResponse setStation(@RequestBody Station station) {
        stationService.setStation(station);
        return ServiceResponse.createBySuccess();
    }
}
