package cn.blatter.network.mapper;

import cn.blatter.network.domain.Pipe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/13 13:34
 */
@Mapper
public interface PipeMapper {

	List<Pipe> findAll();

	Pipe queryById(Integer id);

	List<Pipe> queryByProject(Integer id);

	Integer updateById(Pipe pipe);

	Integer insertPipe(Pipe pipe);

	Integer deleteById(Integer id);

	List<Pipe> pageQuery(@Param("pageStart") Integer start, @Param("pageSize") Integer pageSize, @Param("pid") Integer pid);
}
