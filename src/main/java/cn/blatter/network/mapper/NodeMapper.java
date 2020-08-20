package cn.blatter.network.mapper;

import cn.blatter.network.domain.Node;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/13 15:37
 */
@Mapper
public interface NodeMapper {
	List<Node> findAll();

	Node queryById(Integer id);

	List<Node> queryByProject(Integer id);

	Integer deleteById(Integer id);

	Integer insertNode(Node node);

	Integer updateById(Node node);

	List<Node> pageQuery(@Param("pageStart") Integer start, @Param("pageSize") Integer pageSize, @Param("pid") Integer pid);
}
