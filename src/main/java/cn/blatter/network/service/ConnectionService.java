package cn.blatter.network.service;

import cn.blatter.network.domain.Connection;

import java.util.List;

public interface ConnectionService {
    List<Connection> findAll();
    List<Connection> findByEid(Integer element_id);
    void setConnection(Integer id,Integer element_id,Double x,Double y,String name);
    void deleteConnection(Integer id);
    void addConnection(Integer id,Integer element_id,Double x,Double y,String name);
    Connection queryById(Integer id);
}
