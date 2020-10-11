package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Connection;
import cn.blatter.network.service.ConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @RequestMapping(value = "/getConnection", method = RequestMethod.GET)
    public ServiceResponse list() {
        List<Connection> connectionList = connectionService.findAll();
        // log.info("连接点" + connectionList);
        return ServiceResponse.createBySuccess(connectionList);
    }

    @RequestMapping(value = "/findConnectionByEid", method = RequestMethod.POST)
    public ServiceResponse findByEid(@RequestBody Connection connection) {
        List<Connection> connectionList = connectionService.findByEid(connection.getElement_id());
        return ServiceResponse.createBySuccess(connectionList);
    }

    @PostMapping(value = "/setConnection")
    public ServiceResponse setConnection(@RequestBody Connection connection) {
        connectionService.setConnection(connection.getId(),connection.getElement_id(),connection.getX(),connection.getY(),connection.getName());
        return ServiceResponse.createBySuccess();
    }

    @PostMapping(value = "/deleteConnection")
    public ServiceResponse deleteConnection(@RequestBody Connection connection) {
        connectionService.deleteConnection(connection.getId());
        return ServiceResponse.createBySuccess();
    }

    @PostMapping(value = "/addConnection")
    public ServiceResponse addConnection(@RequestBody Connection connection){
        connectionService.addConnection(connection.getId(),connection.getElement_id(),connection.getX(),connection.getY(),connection.getName());
        return ServiceResponse.createBySuccess();
    }
}
