package cn.blatter.network.service.impl;

import cn.blatter.network.domain.Connection;
import cn.blatter.network.mapper.ConnectionMapper;
import cn.blatter.network.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("connectionService")
public class ConnectionServiceImpl implements ConnectionService {

    @Autowired
    private ConnectionMapper connectionMapper;

    @Override
    public List<Connection> findAll(){
        List<Connection> connectionList = connectionMapper.findAll();
        return connectionList;
    }
}
