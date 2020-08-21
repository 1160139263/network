package cn.blatter.network.service;

import cn.blatter.network.domain.Attribute;

import java.util.List;

public interface AttributeService {
    List<Attribute> findAll();
    Attribute findById(Integer element_id);
    void deleteAttribute(Integer element_id);
}
