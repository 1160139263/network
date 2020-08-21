package cn.blatter.network.mapper;

import cn.blatter.network.domain.Attribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttributeMapper {
    List<Attribute> findAll();
    void deleteAttribute(Integer element_id);
}
