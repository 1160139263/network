package cn.blatter.network.mapper;

import cn.blatter.network.domain.Base;
import cn.blatter.network.domain.Station;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/13 15:37
 */
@Mapper
public interface StationMapper {
    List<Station> findAll(Integer id);
    Station getBaseById(Integer id);
    void setStation(Station station);
    void setBase(Station station);

    void addStation(Station station);
    void addBase(Station station);

    void deleteStation(Integer id);
}
