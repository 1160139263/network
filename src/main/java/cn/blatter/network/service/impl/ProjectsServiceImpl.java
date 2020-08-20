package cn.blatter.network.service.impl;

import cn.blatter.network.domain.Component;
import cn.blatter.network.domain.GasProperty;
import cn.blatter.network.domain.PageInfo;
import cn.blatter.network.domain.Projects;
import cn.blatter.network.mapper.ComponentMapper;
import cn.blatter.network.mapper.ProjectsMapper;
import cn.blatter.network.service.ComponentService;
import cn.blatter.network.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/9 15:13
 */
@Transactional
@Service("projectsService")
public class ProjectsServiceImpl implements ProjectsService {

	@Autowired
	private ProjectsMapper projectsMapper;

	@Autowired
	private ComponentService componentService;

	/**
	 * 分页查询
	 * @param info
	 * @return
	 */
	@Override
	public List<Projects> pageQuery(PageInfo info) {
		List<Projects> projectsList = projectsMapper.pageQuery(info.getPageSize() * (info.getPageNumber()-1), info.getPageSize());
		return projectsList;
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@Override
	public Projects queryOne(Integer id) {
		Projects projects = projectsMapper.queryById(id);
		return projects;
	}

	/**
	 * 查询所有
	 * @return
	 */
	@Override
	public List<Projects> findAll() {
		return projectsMapper.findAll();
	}

	@Override
	public Integer insertOne(Projects projects) {
		projectsMapper.insertProjects(projects);
		return projects.getPid();
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteProject(Integer id) {
		Integer result = projectsMapper.deleteById(id);
		return result;
	}

	/**
	 * 根据项目id获取项目气体信息
	 * @param id
	 * @return
	 */
	@Override
	public List<GasProperty> getProjectComponent(Integer id) {
		Projects projects = projectsMapper.queryById(id);
		Integer comId = projects.getComId();
		List<GasProperty> properties = componentService.queryComponentProperty(comId);
		return properties;
	}
}
