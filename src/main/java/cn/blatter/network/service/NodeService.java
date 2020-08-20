package cn.blatter.network.service;

import cn.blatter.network.domain.Node;
import cn.blatter.network.domain.PageInfo;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/30 22:47
 */
public interface NodeService {
	List<Node> queryByPid(Integer pid);

	List<Node> pageQuery(PageInfo info, Integer pid);

	Node queryNodeById(Integer id);

	Integer deleteNode(Integer id);

	Integer insertNode(Node node);

	Integer updateNode(Node node);
}
