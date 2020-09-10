package cn.blatter.network.service.impl;

import cn.blatter.network.domain.*;
import cn.blatter.network.mapper.CompressorMapper;
import cn.blatter.network.service.CompressorService;
import cn.blatter.network.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("compressorService")
public class CompressorServiceImpl implements CompressorService {

    @Autowired
    private CompressorMapper compressorMapper;

    @Autowired
    private ElementServiceImpl elementService;

    @Autowired
    private ConnectionServiceImpl connectionService;

    @Autowired
    private ProjectsServiceImpl projectsService;

    @Override
    public List<Compressor> findAll(Integer id) {
        List<Compressor> compressorList = compressorMapper.findAll(id);
        return compressorList;
    }

    @Override
    public void deleteCompressor(Integer id){
        try {
            Compressor compressor = findById(id);
            String path = "src/main/resources" + projectsService.queryOne(compressor.getProjectId()).getModel();
            List<Element> elements = elementService.findAll();
            List<Connection> connections = connectionService.findAll();
            XMLUtil xmlUtil = new XMLUtil(elements, connections);
            xmlUtil.deleteNode(path, compressor);
            compressorMapper.deleteCompressor(id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCompressor(Compressor compressor){
        try {
            String path = "src/main/resources" + projectsService.queryOne(compressor.getProjectId()).getModel();
            List<Element> elements = elementService.findAll();
            List<Connection> connections = connectionService.findAll();
            XMLUtil xmlUtil = new XMLUtil(elements, connections);
            xmlUtil.insertNode(path, compressor);
            compressorMapper.addBase(compressor);
            compressorMapper.addCompressor(compressor);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCompressor(Compressor compressor){
        try {
            String path = "src/main/resources" + projectsService.queryOne(compressor.getProjectId()).getModel();
            List<Element> elements = elementService.findAll();
            List<Connection> connections = connectionService.findAll();
            XMLUtil xmlUtil = new XMLUtil(elements, connections);
            xmlUtil.updateNode(path, compressor);
            compressorMapper.setBase(compressor);
            compressorMapper.setCompressor(compressor);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Compressor findById(Integer id){
        Compressor compressorList = compressorMapper.getBaseById(id);
        return compressorList;
    }
}
