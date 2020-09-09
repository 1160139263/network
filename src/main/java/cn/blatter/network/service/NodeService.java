package cn.blatter.network.service;

import cn.blatter.network.domain.Element;
import cn.blatter.network.domain.Node;
import cn.blatter.network.domain.PageInfo;

import java.util.List;


public interface NodeService {
	List<Node> findAll(Integer id);
	Node findById(Integer id);
	void setNode(Integer id,String name,Double pressure,Double loads,boolean pressure_state,boolean load_state,Double elevation,Double x,Double y);
	void addNode(String name,Double pressure,Double loads,boolean pressure_state,boolean load_state,Double elevation,Double x,Double y);
	void deleteNode(Integer id);
}
