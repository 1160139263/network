package cn.blatter.network.mapper;

import cn.blatter.network.domain.Base;
import cn.blatter.network.domain.Ejector;
import cn.blatter.network.domain.Element;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/13 15:37
 */
@Mapper
public interface EjectorMapper {
    List<Ejector> findAll(Integer id);
    Ejector getBaseById(Integer id);
    void setEjector(Ejector ejector);
    void setBase(Ejector ejector);

    void addEjector(Ejector ejector);
    void addBase(Ejector ejector);

    void deleteEjector(Integer id);
}
