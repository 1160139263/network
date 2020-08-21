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

    @Override
    public List<Connection> findByEid(Integer element_id){
        List<Connection> connectionList = connectionMapper.findByEid(element_id);
        return connectionList;
    }

    @Override
    public void setConnection(Integer id,Integer element_id,Double x,Double y,String name){connectionMapper.setConnection(id,element_id,x,y,name);}

    @Override
    public void deleteConnection(Integer id){connectionMapper.deleteConnection(id);}

    @Override
    public void addConnection(Integer id,Integer element_id,Double x,Double y,String name){connectionMapper.addConnection(id,element_id,x,y,name);}
}
