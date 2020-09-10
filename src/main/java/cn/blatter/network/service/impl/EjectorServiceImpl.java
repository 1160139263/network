package cn.blatter.network.service.impl;

import cn.blatter.network.domain.*;
import cn.blatter.network.mapper.EjectorMapper;
import cn.blatter.network.service.EjectorService;
import cn.blatter.network.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("ejectorService")
public class EjectorServiceImpl implements EjectorService {

    @Autowired
    private EjectorMapper ejectorMapper;

    @Autowired
    private ElementServiceImpl elementService;

    @Autowired
    private ConnectionServiceImpl connectionService;

    @Autowired
    private ProjectsServiceImpl projectsService;

    @Override
    public List<Ejector> findAll(Integer id) {
        List<Ejector> ejectorList = ejectorMapper.findAll(id);
        return ejectorList;
    }

    @Override
    public void deleteEjector(Integer id){
        try {
            Ejector ejector = findById(id);
            String path = "src/main/resources" + projectsService.queryOne(ejector.getProjectId()).getModel();
            List<Element> elements = elementService.findAll();
            List<Connection> connections = connectionService.findAll();
            XMLUtil xmlUtil = new XMLUtil(elements, connections);
            xmlUtil.deleteNode(path, ejector);
            ejectorMapper.deleteEjector(id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEjector(Ejector ejector){
        try {
            String path = "src/main/resources" + projectsService.queryOne(ejector.getProjectId()).getModel();
            List<Element> elements = elementService.findAll();
            List<Connection> connections = connectionService.findAll();
            XMLUtil xmlUtil = new XMLUtil(elements, connections);
            xmlUtil.insertNode(path, ejector);
            ejectorMapper.addBase(ejector);
            ejectorMapper.addEjector(ejector);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setEjector(Ejector ejector){
        try {
            String path = "src/main/resources" + projectsService.queryOne(ejector.getProjectId()).getModel();
            List<Element> elements = elementService.findAll();
            List<Connection> connections = connectionService.findAll();
            XMLUtil xmlUtil = new XMLUtil(elements, connections);
            xmlUtil.updateNode(path, ejector);
            ejectorMapper.setBase(ejector);
            ejectorMapper.setEjector(ejector);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ejector findById(Integer id){
        Ejector ejectorList = ejectorMapper.getBaseById(id);
        return ejectorList;
    }
}
