package cn.blatter.network.mapper;

import cn.blatter.network.domain.Element;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ElementMapper {
    List<Element> findAll();
    void setElement(Integer id,String name);
    void addElement(Integer id,String name,String path);
    void deleteElement(Integer id);
}
