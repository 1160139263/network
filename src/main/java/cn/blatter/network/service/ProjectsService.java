package cn.blatter.network.service;

import cn.blatter.network.domain.Component;
import cn.blatter.network.domain.GasProperty;
import cn.blatter.network.domain.PageInfo;
import cn.blatter.network.domain.Projects;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/9 15:13
 */
public interface ProjectsService {

	List<Projects> pageQuery(PageInfo info);

	Projects queryOne(Integer id);

	List<Projects> findAll();

	Integer insertOne(Projects projects);

	Integer saveProjectModel(Projects projects);

	Integer deleteProject(Integer id);

	List<GasProperty> getProjectComponent(Integer id);
}
