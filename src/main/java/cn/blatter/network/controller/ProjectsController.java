package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.PageInfo;
import cn.blatter.network.domain.Projects;
import cn.blatter.network.service.ProjectsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/9 15:16
 */
@Slf4j
@RestController
public class ProjectsController {

	@Autowired
	private ProjectsService projectsService;

	@RequestMapping(value = "/projects/count", method = RequestMethod.GET)
	public ServiceResponse count() {
		List<Projects> projectsList = projectsService.findAll();
		if (projectsList == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(projectsList.size());
	}

	/**
	 * 分页查询
	 * @param pageInfo
	 * @return
	 */
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public ServiceResponse list(PageInfo pageInfo) {
		List<Projects> projectsList = projectsService.pageQuery(pageInfo);
		if (projectsList == null) {
			return ServiceResponse.createByErrorMessage("查询出错");
		}
		return ServiceResponse.createBySuccess(projectsList);
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public ServiceResponse queryOne(@PathVariable Integer id) {
		Projects projects = projectsService.queryOne(id);
		if (projects == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(projects);
	}

	/**
	 * 新增
	 * @param projects
	 * @return
	 */
	@PostMapping(value = "/projects")
	public ServiceResponse newProject(@RequestBody Projects projects) {
		Integer result = projectsService.insertOne(projects);
		if (result == null) {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(result);
	}

	@DeleteMapping(value = "/projects/{id}")
	public ServiceResponse deleteProject(@PathVariable Integer id) {
		Integer result = projectsService.deleteProject(id);
		if (result == 1) {
			return ServiceResponse.createBySuccess();
		}
		return ServiceResponse.createByError();
	}

	@GetMapping(value = "/projects/findAll")
	public ServiceResponse findAll() {
		List<Projects> projectsList = projectsService.findAll();
		return ServiceResponse.createBySuccess(projectsList);
	}
}
