package cn.blatter.network.mapper;

import cn.blatter.network.domain.Component;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/9 18:14
 */
@Mapper
public interface ComponentMapper {

	/**
	 * 根据id查询组分信息
	 * @param id
	 * @return
	 */
	Component queryById(Integer id);

	/**
	 * 查询所有组分
	 * @return
	 */
	Integer count();

	/**
	 * 根据id修改组分信息
	 * @param component
	 */
	Integer updateById(Component component);

	/**
	 * 插入新组分
	 * @param component
	 */
	Integer insertComponent(Component component);

	Integer deleteById(Integer id);

	List<Component> findAll();

	List<Component> pageQuery(@Param("pageStart") Integer start, @Param("pageSize") Integer pageSize);
}
