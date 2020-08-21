package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Attribute;
import cn.blatter.network.service.AttributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}