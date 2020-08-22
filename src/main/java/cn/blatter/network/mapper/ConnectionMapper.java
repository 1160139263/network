package cn.blatter.network.mapper;

import cn.blatter.network.domain.Connection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConnectionMapper {
    List<Connection> findAll();
    List<Connection> findByEid(Integer element_id);
    void setConnection(Integer id,Integer element_id,Double x,Double y,String name);
    void addConnection(Integer id,Integer element_id,Double x,Double y,String name);
    void deleteConnection(Integer id);
    Connection queryById(Integer id);
}
