package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.*;
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
		long startTime =  System.currentTimeMillis();
		nodeService.addNode(node);
		long endTime =  System.currentTimeMillis();
		double usedTime = (endTime*1.0-startTime*1.0)/1000;

		log.info("生成普通节点所用时间："+usedTime);
		return ServiceResponse.createBySuccess();
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
		nodeService.setNode(node);
		return ServiceResponse.createBySuccess();
	}

	@GetMapping(value = "/project/{id}/bases")
	public ServiceResponse getPipes(@PathVariable Integer id) {
		List<Base> bases = nodeService.findAllBase(id);
		if (bases != null) {
			return ServiceResponse.createBySuccess(bases);
		}
		return ServiceResponse.createByError();
	}
}
