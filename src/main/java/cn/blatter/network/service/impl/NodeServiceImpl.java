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
		Integer result = nodeMapper.deleteById(id);
		return result;
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

		Integer result = nodeMapper.insertNode(node);
		return result;
	}

	@Override
	public Integer updateNode(Node node) {
		if (node.getElementId() != null) {
			Element element = elementMapper.findById(node.getElementId());
			node.setElementName(element.getName());
		}
		String path = "src/main/resources" + projectsService.queryOne(node.getProjectId()).getModel();
		List<Element> elements = elementService.findAll();
		List<Connection> connections = connectionService.findAll();
		XMLUtil xmlUtil = new XMLUtil(elements, connections);
		Integer result = nodeMapper.updateById(node);
		return result;
	}
}
