package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Ejector;
import cn.blatter.network.service.EjectorService;
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
public class EjectorController {

    @Autowired
    private EjectorService ejectorService;

    @RequestMapping(value = "/getEjector", method = RequestMethod.POST)
    public ServiceResponse list(@RequestBody Ejector ejector) {
        List<Ejector> ejectorList = ejectorService.findAll(ejector.getProjectId());
        return ServiceResponse.createBySuccess(ejectorList);
    }

    @RequestMapping(value = "/addEjector", method = RequestMethod.POST)
    public ServiceResponse addEjector(@RequestBody Ejector ejector) {
        ejectorService.addEjector(ejector);
        return ServiceResponse.createBySuccess();
    }

    @RequestMapping(value = "/findEjectorById", method = RequestMethod.POST)
    public ServiceResponse findById(@RequestBody Ejector ejector) {
        Ejector ejectorList = ejectorService.findById(ejector.getId());
        return ServiceResponse.createBySuccess(ejectorList);
    }

    @PostMapping(value = "/deleteEjector")
    public ServiceResponse deleteEjector(@RequestBody Ejector ejector) {
        ejectorService.deleteEjector(ejector.getId());
        return ServiceResponse.createBySuccess();
    }

    @PostMapping(value = "/setEjector")
    public ServiceResponse setEjector(@RequestBody Ejector ejector) {
        ejectorService.setEjector(ejector);
        return ServiceResponse.createBySuccess();
    }
}
