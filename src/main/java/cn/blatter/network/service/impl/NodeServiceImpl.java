package cn.blatter.network.service.impl;

import cn.blatter.network.domain.Node;
import cn.blatter.network.domain.PageInfo;
import cn.blatter.network.mapper.NodeMapper;
import cn.blatter.network.service.NodeService;
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
		Integer result = nodeMapper.insertNode(node);
		return result;
	}

	@Override
	public Integer updateNode(Node node) {
		Integer result = nodeMapper.updateById(node);
		return result;
	}
}
