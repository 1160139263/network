package cn.blatter.network.mapper;

import cn.blatter.network.domain.Base;
import cn.blatter.network.domain.Compressor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/13 15:37
 */
@Mapper
public interface CompressorMapper {
    List<Compressor> findAll(Integer id);
    Compressor getBaseById(Integer id);
    void setCompressor(Compressor compressor);
    void setBase(Compressor compressor);

    void addCompressor(Compressor compressor);
    void addBase(Compressor compressor);

    void deleteCompressor(Integer id);
}
