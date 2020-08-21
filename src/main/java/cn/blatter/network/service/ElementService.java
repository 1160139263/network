package cn.blatter.network.service;

import cn.blatter.network.domain.Element;

import java.util.List;

public interface ElementService {
    List<Element> findAll();
    void setElement(Integer id,String name);
    void addElement(Integer id,String name,String path);
    void deleteElement(Integer id);
}
