package cn.blatter.network.service;

import cn.blatter.network.domain.Base;
import cn.blatter.network.domain.Station;

import java.util.List;


public interface StationService {
    List<Station> findAll(Integer id);
    Station findById(Integer id);
    void setStation(Station station);
    void addStation(Station station);
    void deleteStation(Integer id);
}
