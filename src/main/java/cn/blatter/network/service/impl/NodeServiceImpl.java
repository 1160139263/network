package cn.blatter.network.service.impl;

import cn.blatter.network.domain.*;
import cn.blatter.network.mapper.NodeMapper;
import cn.blatter.network.service.NodeService;
import cn.blatter.network.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("nodeService")
public class NodeServiceImpl implements NodeService {

	@Autowired
	private NodeMapper nodeMapper;

	@Autowired
	private ElementServiceImpl elementService;

	@Autowired
	private ConnectionServiceImpl connectionService;

	@Autowired
	private ProjectsServiceImpl projectsService;

	@Override
	public List<Node> findAll(Integer id) {
		List<Node> nodeList = nodeMapper.findAll(id);
		return nodeList;
	}

	@Override
	public void deleteNode(Integer id){
		try {
			Node node = findById(id);
			String path = "src/main/resources" + projectsService.queryOne(node.getProjectId()).getModel();
			List<Element> elements = elementService.findAll();
			List<Connection> connections = connectionService.findAll();
			XMLUtil xmlUtil = new XMLUtil(elements, connections);
			xmlUtil.deleteNode(path, node);
			nodeMapper.deleteNode(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addNode(Node node){
		try {
			String path = "src/main/resources" + projectsService.queryOne(node.getProjectId()).getModel();
			List<Element> elements = elementService.findAll();
			List<Connection> connections = connectionService.findAll();
			XMLUtil xmlUtil = new XMLUtil(elements, connections);
			xmlUtil.insertNode(path, node);
			nodeMapper.addBase(node);
			nodeMapper.addNode(node);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setNode(Node node){
		try {
			String path = "src/main/resources" + projectsService.queryOne(node.getProjectId()).getModel();
			List<Element> elements = elementService.findAll();
			List<Connection> connections = connectionService.findAll();
			XMLUtil xmlUtil = new XMLUtil(elements, connections);
			xmlUtil.updateNode(path, node);
			nodeMapper.setBase(node);
			nodeMapper.setNode(node);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Node findById(Integer id){
		Node nodeList = nodeMapper.getBaseById(id);
		return nodeList;
	}

	@Override
	public List<Base> findAllBase(Integer id) {
		List<Base> list = nodeMapper.findAllBase(id);
		return list;
	}

}
