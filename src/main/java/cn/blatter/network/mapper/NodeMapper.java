package cn.blatter.network.mapper;

import cn.blatter.network.domain.Base;
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
	void setNode(Node node);
	void setBase(Node node);

	void addNode(Node node);
	void addBase(Node node);

	void deleteNode(Integer id);

	List<Base> findAllBase(Integer id);
}
