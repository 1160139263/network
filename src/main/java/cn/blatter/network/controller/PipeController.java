package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.PageInfo;
import cn.blatter.network.domain.Pipe;
import cn.blatter.network.service.PipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/31 0:53
 */
@Slf4j
@RestController
public class PipeController {

	@Autowired
	private PipeService pipeService;

	@RequestMapping(value = "/project/pipes/{pid}", method = RequestMethod.GET)
	public ServiceResponse getPipes(@PathVariable Integer pid, PageInfo pageInfo) {
		List<Pipe> pipes = pipeService.pageQuery(pageInfo, pid);
		if (pipes == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(pipes);
	}

	@RequestMapping(value = "/project/pipes/count/{pid}", method = RequestMethod.GET)
	public ServiceResponse getCount(@PathVariable Integer pid) {
		List<Pipe> pipes = pipeService.queryByPid(pid);
		if (pipes == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(pipes.size());
	}


	@GetMapping(value = "/project/{id}/pipes")
	public ServiceResponse getPipes(@PathVariable Integer id) {
		List<Pipe> pipes = pipeService.queryByPid(id);
		if (pipes != null) {
			return ServiceResponse.createBySuccess(pipes);
		}
		return ServiceResponse.createByError();
	}

	@GetMapping(value = "/pipes/{id}")
	public ServiceResponse getPipe(@PathVariable Integer id) {
		Pipe pipe = pipeService.queryById(id);
		if (pipe == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(pipe);
	}

	@DeleteMapping(value = "/pipes/{id}")
	public ServiceResponse deletePipe(@PathVariable Integer id) {
		Integer result = pipeService.deletePipe(id);
		if (result == 1) {
			return ServiceResponse.createBySuccess();
		}
		return ServiceResponse.createByError();
	}

	@PostMapping(value = "/pipes")
	public ServiceResponse insertPipe(@RequestBody Pipe pipe) {
		long startTime =  System.currentTimeMillis();
		Integer result = pipeService.insertPipe(pipe);
		if (result == 1) {
			return ServiceResponse.createBySuccess(pipe);
		}
		long endTime =  System.currentTimeMillis();
		double usedTime = (endTime*1.0-startTime*1.0)/1000;
		log.info("生成管道所用时间(s)："+usedTime);
		return ServiceResponse.createByError();
	}

	@PutMapping(value = "/pipes")
	public ServiceResponse updatePipe(@RequestBody Pipe pipe) {
		long startTime =  System.currentTimeMillis();
		Integer result = pipeService.updatePipe(pipe);
		if (result == 1) {
			return ServiceResponse.createBySuccess();
		}
		long endTime =  System.currentTimeMillis();
		double usedTime = (endTime*1.0-startTime*1.0)/1000;
		log.info("生成管道所用时间(s)："+usedTime);
		return ServiceResponse.createByError();
	}
}
