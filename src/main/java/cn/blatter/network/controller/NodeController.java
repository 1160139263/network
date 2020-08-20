package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Node;
import cn.blatter.network.domain.PageInfo;
import cn.blatter.network.service.NodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/30 22:46
 */
@Slf4j
@RestController
public class NodeController {

	@Autowired
	private NodeService nodeService;

	/**
	 * 分页查询
	 * @param pid
	 * @param pageInfo
	 * @return
	 */
	@RequestMapping(value = "/project/nodes/{pid}", method = RequestMethod.GET)
	public ServiceResponse getNode(@PathVariable Integer pid, PageInfo pageInfo) {
		List<Node> nodes = nodeService.pageQuery(pageInfo, pid);
		if (nodes == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(nodes);
	}

	@GetMapping(value = "/project/{pid}/nodes")
	public ServiceResponse getAllNodes(@PathVariable Integer pid) {
		List<Node> nodes = nodeService.queryByPid(pid);
		if (nodes != null) {
			return ServiceResponse.createBySuccess(nodes);
		}
		return ServiceResponse.createByError();
	}

	/**
	 * 根据节点id查询信息
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/nodes/{id}")
	public ServiceResponse getNodeInfo(@PathVariable Integer id) {
		Node node = nodeService.queryNodeById(id);
		if (node != null) {
			return ServiceResponse.createBySuccess(node);
		}
		return ServiceResponse.createByError();
	}

	/**
	 * 根据工程id查询总数
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/project/nodes/count/{pid}", method = RequestMethod.GET)
	public ServiceResponse getCount(@PathVariable Integer pid) {
		List<Node> nodes = nodeService.queryByPid(pid);
		if (nodes == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(nodes.size());
	}

	@DeleteMapping("/nodes/{id}")
	public ServiceResponse deleteNode(@PathVariable Integer id) {
		Integer result = nodeService.deleteNode(id);
		if (result == 1) {
			return ServiceResponse.createBySuccess();
		}
		return ServiceResponse.createByError();
	}

	@PostMapping("/nodes")
	public ServiceResponse insertNode(@RequestBody Node node) {
		Integer result = nodeService.insertNode(node);
		if (result == 1) {
			return ServiceResponse.createBySuccess(node);
		}
		return ServiceResponse.createByError();
	}

	@PutMapping("/nodes")
	public ServiceResponse alterNode(@RequestBody Node node) {
		Integer result = nodeService.updateNode(node);
		if (result == 1) {
			return ServiceResponse.createBySuccess();
		}
		return ServiceResponse.createByError();
	}
}
