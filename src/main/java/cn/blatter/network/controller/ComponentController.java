package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Component;
import cn.blatter.network.domain.GasProperty;
import cn.blatter.network.domain.PageInfo;
import cn.blatter.network.service.ComponentService;
import cn.blatter.network.service.ProjectsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/8/9 15:37
 */
@Slf4j
@RestController
public class ComponentController {

	@Autowired
	private ComponentService componentService;

	@Autowired
	private ProjectsService projectsService;

	/**
	 * 获取组分总数
	 * @return
	 */
	@GetMapping(value = "/components/count")
	public ServiceResponse getCount() {
		Integer result = componentService.countComponent();
		if (result == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(result);
	}

	/**
	 * 新增组分
	 * @param temp
	 * @return
	 */
	@PostMapping("/components")
	public ServiceResponse insertComponent(@RequestBody Component temp) {
		Component component = new Component();
		component.setName(temp.getName());
		Integer result = componentService.insertComponent(component);
		if (result == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(result);
	}

	/**
	 * 分页查询
	 * @param pageInfo
	 * @return
	 */
	@GetMapping(value = "/components")
	public ServiceResponse pageQuery(PageInfo pageInfo) {
		List<Component> components = componentService.pageQuery(pageInfo);
		if (components == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(components);
	}

	/**
	 * 删除组分
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/components/{id}")
	public ServiceResponse deleteComponent(@PathVariable Integer id) {
		Integer result = componentService.deleteComponent(id);
		return ServiceResponse.createBySuccess(result);
	}

	/**
	 * 根据id获取组分信息
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/components/{id}")
	public ServiceResponse queryOne(@PathVariable Integer id) {
		List<GasProperty> properties = componentService.queryComponentProperty(id);
		if (properties == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(properties);
	}

	/**
	 * 根据项目id获取项目气体组分信息
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/projects/gas/{id}")
	public ServiceResponse getProjectComponent(@PathVariable Integer id) {
		List<GasProperty> projectComponent = projectsService.getProjectComponent(id);
		return ServiceResponse.createBySuccess(projectComponent);
	}

	@PutMapping("/components")
	public ServiceResponse alterComponent(@RequestBody Component component) {
		Integer result = componentService.updateComponent(component);
		if (result == 1) {
			return ServiceResponse.createBySuccess();
		}
		return ServiceResponse.createByError();
	}

	@GetMapping(value = "/components/findAll")
	public ServiceResponse getAllComponent() {
		List<Component> components = componentService.findAll();
		if(components != null) {
			return ServiceResponse.createBySuccess(components);
		}
		return ServiceResponse.createByError();
	}

}
