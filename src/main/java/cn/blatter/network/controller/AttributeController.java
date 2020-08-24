package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Attribute;
import cn.blatter.network.service.AttributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @RequestMapping(value = "/getAttribute", method = RequestMethod.GET)
    public ServiceResponse list() {
        List<Attribute> attributeList = attributeService.findAll();
        return ServiceResponse.createBySuccess(attributeList);
    }

    @RequestMapping(value = "/findAttributeById", method = RequestMethod.POST)
    public ServiceResponse findById(@RequestBody Attribute attribute) {
        Attribute attributeList = attributeService.findById(attribute.getElement_id());
        return ServiceResponse.createBySuccess(attributeList);
    }

    @PostMapping(value = "/setAttribute")
    public ServiceResponse setAttribute(@RequestBody Attribute attribute) {
        attributeService.setAttribute(attribute.getElement_id(),attribute.getPressure(),attribute.getPressure_state(),attribute.getLoads(),attribute.getLoad_state(),attribute.getElevation());
        return ServiceResponse.createBySuccess();
    }

    @PostMapping(value = "/addAttribute")
    public ServiceResponse addAttribute(@RequestBody Attribute attribute){
        attributeService.addAttribute(attribute.getElement_id(),attribute.getPressure(),attribute.getPressure_state(),attribute.getLoads(),attribute.getLoad_state(),attribute.getElevation());
        return ServiceResponse.createBySuccess();
    }

    @PostMapping(value = "/deleteAttribute")
    public ServiceResponse deleteAttribute(@RequestBody Attribute attribute) {
        attributeService.deleteAttribute(attribute.getElement_id());
        return ServiceResponse.createBySuccess();
    }
}
