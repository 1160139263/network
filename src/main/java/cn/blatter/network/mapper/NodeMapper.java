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
	List<Node> findAll(Integer id);
	Node findById(Integer id);
	void setNode(Integer id,Double pressure,Double loads,boolean pressure_state,boolean load_state);
	void setBase(Integer id,String name,Double elevation,Double x,Double y);

	void addNode(Integer id,Double pressure,Double loads,boolean pressure_state,boolean load_state);
	void addBase(String name,Double elevation,Double x,Double y);

	void deleteNode(Integer id);

}
