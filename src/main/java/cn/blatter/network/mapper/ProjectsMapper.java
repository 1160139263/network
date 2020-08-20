package cn.blatter.network.mapper;

import cn.blatter.network.domain.Projects;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ProjectsMapper {
	/**
	 * 查询所有项目
	 * @return
	 */
	List<Projects> findAll();

	/**
	 * 根据项目id更新项目信息
	 * @param projects
	 */
	void updateById(Projects projects);

	/**
	 * 根据项目id删除项目
	 * @param pid
	 */
	Integer deleteById(Integer pid);

	/**
	 * 新建项目信息
	 * @param projects
	 */
	Integer insertProjects(Projects projects);

	/**
	 * 根据项目id查询项目信息
	 * @param pid
	 * @return
	 */
	Projects queryById(Integer pid);

	List<Projects> pageQuery(@Param("pageStart") Integer start, @Param("pageSize") Integer pageSize);
}
