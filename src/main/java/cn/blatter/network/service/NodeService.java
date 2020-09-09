package cn.blatter.network.service;

import cn.blatter.network.domain.Base;
import cn.blatter.network.domain.Element;
import cn.blatter.network.domain.Node;
import cn.blatter.network.domain.PageInfo;

import java.util.List;


public interface NodeService {
	List<Node> findAll(Integer id);
	Node findById(Integer id);
	void setNode(Node node);
	void addNode(Node node);
	void deleteNode(Integer id);
	List<Base> findAllBase(Integer id);
}
