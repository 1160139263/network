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
	public void addNode(Node node){
		nodeMapper.addBase(node);
		nodeMapper.addNode(node);
	}

	@Override
	public void setNode(Node node){
		nodeMapper.setBase(node);
		nodeMapper.setNode(node);
	}

	@Override
	public Node findById(Integer id){
		Node nodeList = nodeMapper.findById(id);
		return nodeList;
	}

	@Override
	public List<Base> findAllBase(Integer id) {
		List<Base> list = nodeMapper.findAllBase(id);
		return list;
	}

}
