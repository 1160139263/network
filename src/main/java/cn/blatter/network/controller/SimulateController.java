package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.service.SimulateService;
import cn.blatter.network.simulator.simulate.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanyao
 * @Date 2020/8/14 1:02
 */
@RestController
public class SimulateController {

	@Autowired
	private SimulateService simulateService;

	@GetMapping(value = "/simulate/{pid}")
	public ServiceResponse simulate(@PathVariable Integer pid) {
		Network simulate = simulateService.simulate(pid);
		if (simulate != null) {
			return ServiceResponse.createBySuccess(simulate);
		}
		return ServiceResponse.createByError();
	}
}
