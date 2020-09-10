package cn.blatter.network.service.impl;

import cn.blatter.network.domain.*;
import cn.blatter.network.mapper.StationMapper;
import cn.blatter.network.service.StationService;
import cn.blatter.network.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("stationService")
public class StationServiceImpl implements StationService {

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private ElementServiceImpl elementService;

    @Autowired
    private ConnectionServiceImpl connectionService;

    @Autowired
    private ProjectsServiceImpl projectsService;

    @Override
    public List<Station> findAll(Integer id) {
        List<Station> stationList = stationMapper.findAll(id);
        return stationList;
    }

    @Override
    public void deleteStation(Integer id){
        try {
            Station station = findById(id);
            String path = "src/main/resources" + projectsService.queryOne(station.getProjectId()).getModel();
            List<Element> elements = elementService.findAll();
            List<Connection> connections = connectionService.findAll();
            XMLUtil xmlUtil = new XMLUtil(elements, connections);
            xmlUtil.deleteNode(path, station);
            stationMapper.deleteStation(id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addStation(Station station){
        try {
            String path = "src/main/resources" + projectsService.queryOne(station.getProjectId()).getModel();
            List<Element> elements = elementService.findAll();
            List<Connection> connections = connectionService.findAll();
            XMLUtil xmlUtil = new XMLUtil(elements, connections);
            xmlUtil.insertNode(path, station);
            stationMapper.addBase(station);
            stationMapper.addStation(station);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setStation(Station station){
        try {
            String path = "src/main/resources" + projectsService.queryOne(station.getProjectId()).getModel();
            List<Element> elements = elementService.findAll();
            List<Connection> connections = connectionService.findAll();
            XMLUtil xmlUtil = new XMLUtil(elements, connections);
            xmlUtil.updateNode(path, station);
            stationMapper.setBase(station);
            stationMapper.setStation(station);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Station findById(Integer id){
        Station stationList = stationMapper.getBaseById(id);
        return stationList;
    }
}
