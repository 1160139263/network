package cn.blatter.network.service;

import cn.blatter.network.domain.Base;
import cn.blatter.network.domain.Ejector;

import java.util.List;


public interface EjectorService {
    List<Ejector> findAll(Integer id);
    Ejector findById(Integer id);
    void setEjector(Ejector ejector);
    void addEjector(Ejector ejector);
    void deleteEjector(Integer id);
}
