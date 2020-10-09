package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Well;
import cn.blatter.network.service.WellService;
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
public class WellController {

	@Autowired
	private WellService wellService;

	@RequestMapping(value = "/getWell", method = RequestMethod.POST)
	public ServiceResponse list(@RequestBody Well well) {
		List<Well> wellList = wellService.findAll(well.getProjectId());
		return ServiceResponse.createBySuccess(wellList);
	}

	@RequestMapping(value = "/addWell", method = RequestMethod.POST)
	public ServiceResponse addWell(@RequestBody Well well) {
		long startTime =  System.currentTimeMillis();
		wellService.addWell(well);
		long endTime =  System.currentTimeMillis();
		double usedTime = (endTime*1.0-startTime*1.0)/1000;
		log.info("生成生产井所用时间："+usedTime);
		return ServiceResponse.createBySuccess();
	}

	@RequestMapping(value = "/findWellById", method = RequestMethod.POST)
	public ServiceResponse findById(@RequestBody Well well) {
		Well wellList = wellService.findById(well.getId());
		return ServiceResponse.createBySuccess(wellList);
	}

	@PostMapping(value = "/deleteWell")
	public ServiceResponse deleteWell(@RequestBody Well well) {
		wellService.deleteWell(well.getId());
		return ServiceResponse.createBySuccess();
	}

	@PostMapping(value = "/setWell")
	public ServiceResponse setWell(@RequestBody Well well) {
		wellService.setWell(well);
		return ServiceResponse.createBySuccess();
	}
}
