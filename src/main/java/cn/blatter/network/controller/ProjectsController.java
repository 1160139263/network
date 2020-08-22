package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.PageInfo;
import cn.blatter.network.domain.Projects;
import cn.blatter.network.service.ProjectsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

	private static String path = "src/main/resources";

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
	 * 根据id查询,用于graph获取模型文件【POST】
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/model/{id}", method = RequestMethod.POST)
	public ServiceResponse queryModel(@PathVariable Integer id) throws IOException {
		Projects projects = projectsService.queryOne(id);
		StringBuilder s = new StringBuilder();
		File file = new File(path + projects.getModel());
		InputStreamReader in = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(in);
		String line = "";
		while ((line=br.readLine())!=null){
			s.append(line);
		}
		projects.setModel(s.toString());
		if (projects.getModel() == "") {
			return ServiceResponse.createByError();
		}
		return ServiceResponse.createBySuccess(projects);
	}

	/**
	 * 根据id查询,用于graph更新模型文件【PUT】
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/model", method = RequestMethod.PUT)
	public ServiceResponse saveModel(@RequestBody Projects p) throws IOException {
		Projects projects = projectsService.queryOne(p.getPid());
		File file = new File(path + projects.getModel());
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file,false), StandardCharsets.UTF_8);
		BufferedWriter br = new BufferedWriter(out);
		br.write(p.getModel());
		br.flush();
		br.close();
		if (projects.getModel() == "") {
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
