package cn.blatter.network.service.impl;

import cn.blatter.network.domain.*;
import cn.blatter.network.mapper.ElementMapper;
import cn.blatter.network.mapper.NodeMapper;
import cn.blatter.network.service.NodeService;
import cn.blatter.network.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/30 22:48
 */
@Service("nodeService")
public class NodeServiceImpl implements NodeService {

	@Autowired
	private NodeMapper nodeMapper;

	@Autowired
	private ElementMapper elementMapper;

	@Autowired
	private ProjectsServiceImpl projectsService;

	@Autowired
	private ElementServiceImpl elementService;

	@Autowired
	private ConnectionServiceImpl connectionService;

	@Autowired
	private PipeServiceImpl pipeService;

	/**
	 * 根据项目id查询所有节点数据
	 * @param pid
	 * @return
	 */
	@Override
	public List<Node> queryByPid(Integer pid) {
		List<Node> nodes = nodeMapper.queryByProject(pid);
		return nodes;
	}

	@Override
	public List<Node> pageQuery(PageInfo info, Integer pid) {
		List<Node> nodes = nodeMapper.pageQuery(info.getPageSize() * (info.getPageNumber()-1), info.getPageSize(), pid);
		return nodes;
	}

	/**
	 * 根据节点id查询节点数据
	 * @param id
	 * @return
	 */
	@Override
	public Node queryNodeById(Integer id) {
		Node node = nodeMapper.queryById(id);
		return node;
	}

	/**
	 * 根据节点id删除节点
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteNode(Integer id) {
		try {
			Node node = nodeMapper.queryById(id);
			String path = "src/main/resources" + projectsService.queryOne(node.getProjectId()).getModel();
			List<Element> elements = elementService.findAll();
			List<Connection> connections = connectionService.findAll();
			XMLUtil xmlUtil = new XMLUtil(elements, connections);
			xmlUtil.deleteNode(path,node);
			return nodeMapper.deleteById(id);
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 插入新节点
	 * @param node
	 * @return
	 */
	@Override
	public Integer insertNode(Node node) {
		if (node.getElementId() != null) {
			Element element = elementMapper.findById(node.getElementId());
			node.setElementName(element.getName());
		}
		try {
			String path = "src/main/resources" + projectsService.queryOne(node.getProjectId()).getModel();
			List<Element> elements = elementService.findAll();
			List<Connection> connections = connectionService.findAll();
			XMLUtil xmlUtil = new XMLUtil(elements, connections);
			Node newer = xmlUtil.insertNode(path, node);
			return nodeMapper.insertNode(newer);
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Integer updateNode(Node node) {
		if (node.getElementId() != null) {
			Element element = elementMapper.findById(node.getElementId());
			node.setElementName(element.getName());
		}
		try {
			String path = "src/main/resources" + projectsService.queryOne(node.getProjectId()).getModel();
			List<Element> elements = elementService.findAll();
			List<Connection> connections = connectionService.findAll();
			XMLUtil xmlUtil = new XMLUtil(elements, connections);
			xmlUtil.updateNode(path, node);
			return nodeMapper.updateById(node);
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
