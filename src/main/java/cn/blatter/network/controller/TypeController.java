package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Type;
import cn.blatter.network.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/8/5 16:38
 */
@Slf4j
@RestController
public class TypeController {

	@Autowired
	private TypeService typeService;

	@GetMapping("/types")
	public ServiceResponse getTypes() {
		List<Type> types = typeService.findAll();
		if (types == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(types);
	}
}
