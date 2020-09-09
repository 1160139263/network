package cn.blatter.network.service.impl;

import cn.blatter.network.domain.*;
import cn.blatter.network.mapper.ElementMapper;
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

	@Override
	public List<Node> findAll(Integer id) {
		List<Node> nodeList = nodeMapper.findAll(id);
		return nodeList;
	}

	@Override
	public void deleteNode(Integer id){
		nodeMapper.deleteNode(id);
	}

	@Override
	public void addNode(String name,Double pressure,Double loads,boolean pressure_state,boolean load_state,Double elevation,Double x,Double y){
		nodeMapper.setBase(id,name,elevation,x,y);
		nodeMapper.setNode(id,pressure,loads,pressure_state,load_state);
	}

	@Override
	public void setNode(Integer id,String name,Double pressure,Double loads,boolean pressure_state,boolean load_state,Double elevation,Double x,Double y){
		nodeMapper.setBase(id,name,elevation,x,y);
		nodeMapper.setNode(id,pressure,loads,pressure_state,load_state);
	}

	@Override
	public Node findById(Integer id){
		Node nodeList = nodeMapper.findById(id);
		return nodeList;
	}

}
