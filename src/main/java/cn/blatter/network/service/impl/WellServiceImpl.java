package cn.blatter.network.service.impl;

import cn.blatter.network.domain.*;
import cn.blatter.network.mapper.PipeMapper;
import cn.blatter.network.mapper.WellMapper;
import cn.blatter.network.service.WellService;
import cn.blatter.network.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("wellService")
public class WellServiceImpl implements WellService {

	@Autowired
	private WellMapper wellMapper;

	@Autowired
	private ElementServiceImpl elementService;

	@Autowired
	private ConnectionServiceImpl connectionService;

	@Autowired
	private ProjectsServiceImpl projectsService;

	@Autowired
	private PipeMapper pipeMapper;

	@Override
	public List<Well> findAll(Integer id) {
		List<Well> wellList = wellMapper.findAll(id);
		return wellList;
	}

	@Override
	public void deleteWell(Integer id){
		try {
			Well well = findById(id);
			String path = "src/main/resources" + projectsService.queryOne(well.getProjectId()).getModel();
			List<Element> elements = elementService.findAll();
			List<Connection> connections = connectionService.findAll();
			XMLUtil xmlUtil = new XMLUtil(elements, connections);
			List<Pipe> pipeList = xmlUtil.deleteNode(path, well);
			for (Pipe pipe : pipeList) {
				Pipe temp = pipeMapper.queryByModelId(pipe);
				pipeMapper.deleteById(temp.getId());
			}
			wellMapper.deleteWell(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addWell(Well well){
		try {
			String path = "src/main/resources" + projectsService.queryOne(well.getProjectId()).getModel();
			List<Element> elements = elementService.findAll();
			List<Connection> connections = connectionService.findAll();
			XMLUtil xmlUtil = new XMLUtil(elements, connections);
			xmlUtil.insertNode(path, well);
			wellMapper.addBase(well);
			wellMapper.addWell(well);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setWell(Well well){
		try {
			String path = "src/main/resources" + projectsService.queryOne(well.getProjectId()).getModel();
			List<Element> elements = elementService.findAll();
			List<Connection> connections = connectionService.findAll();
			XMLUtil xmlUtil = new XMLUtil(elements, connections);
			xmlUtil.updateNode(path, well);
			wellMapper.setBase(well);
			wellMapper.setWell(well);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Well findById(Integer id){
		Well wellList = wellMapper.getBaseById(id);
		return wellList;
	}
}
