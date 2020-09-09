package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Connection;
import cn.blatter.network.domain.Element;
import cn.blatter.network.domain.Node;
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

	@RequestMapping(value = "/getNode", method = RequestMethod.POST)
	public ServiceResponse list(@RequestBody Node node) {
		List<Node> nodeList = nodeService.findAll(node.getProjectId());
		return ServiceResponse.createBySuccess(nodeList);
	}

	@RequestMapping(value = "/addNode", method = RequestMethod.POST)
	public ServiceResponse addNode(@RequestBody Node node) {
		List<Node> nodeList = nodeService.findAll(node.getProjectId());
		return ServiceResponse.createBySuccess(nodeList);
	}

	@RequestMapping(value = "/findNodeById", method = RequestMethod.POST)
	public ServiceResponse findById(@RequestBody Node node) {
		Node nodeList = nodeService.findById(node.getId());
		return ServiceResponse.createBySuccess(nodeList);
	}

	@PostMapping(value = "/deleteNode")
	public ServiceResponse deleteNode(@RequestBody Node node) {
		nodeService.deleteNode(node.getId());
		return ServiceResponse.createBySuccess();
	}

	@PostMapping(value = "/setNode")
	public ServiceResponse setNode(@RequestBody Node node) {
		nodeService.setNode(node.getId(),node.getName(),node.getPressure(),node.getLoads(),node.isPressureState(),node.isLoadState(),node.getElevation(),node.getX(),node.getY());
		return ServiceResponse.createBySuccess();
	}
}
