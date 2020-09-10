package cn.blatter.network.service;

import cn.blatter.network.domain.Base;
import cn.blatter.network.domain.Compressor;

import java.util.List;


public interface CompressorService {
    List<Compressor> findAll(Integer id);
    Compressor findById(Integer id);
    void setCompressor(Compressor compressor);
    void addCompressor(Compressor compressor);
    void deleteCompressor(Integer id);
}
