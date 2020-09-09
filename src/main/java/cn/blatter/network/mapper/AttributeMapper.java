package cn.blatter.network.mapper;

import cn.blatter.network.domain.Attribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttributeMapper {
    List<Attribute> findAll();
    List<Attribute> findById(Integer element_id);
    void deleteAttribute(Integer element_id);
    void addAttribute(Integer element_id,Double pressure,Boolean pressure_state,Double loads,Boolean load_state,Double elevation);
    void setAttribute(Integer element_id,Double pressure,Boolean pressure_state,Double loads,Boolean load_state,Double elevation);
}
