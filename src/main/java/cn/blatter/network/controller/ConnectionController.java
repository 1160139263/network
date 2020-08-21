package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Connection;
import cn.blatter.network.service.ConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @RequestMapping(value = "/getConnection", method = RequestMethod.GET)
    public ServiceResponse list() {
        List<Connection> connectionList = connectionService.findAll();
        return ServiceResponse.createBySuccess(connectionList);
    }
}
